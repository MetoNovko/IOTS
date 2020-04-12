package com.mps.controllers;

import com.mps.models.Device;
import com.mps.models.Measurement;
import com.mps.services.DeviceService;
import com.mps.services.MeasurementService;
import com.mps.services.UserService;
import com.mps.transferables.DTOs.Device.*;
import com.mps.transferables.GetMeasurementResponse;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://skopje.ml")
@RequestMapping("/device")
public class DeviceController {

    private final MeasurementService measurementService;
    private final DeviceService deviceService;
    private final ApplicationEventPublisher eventPublisher;
    private final ConcurrentHashMap<Integer, List<SseEmitter>> liveListeners;

    public DeviceController(MeasurementService measurementService, DeviceService deviceService, ApplicationEventPublisher eventPublisher) {
        this.measurementService = measurementService;
        this.deviceService = deviceService;
        this.eventPublisher = eventPublisher;
        this.liveListeners = new ConcurrentHashMap<>();
    }

    @PostMapping("/sendData")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendData(@RequestParam int deviceId, @RequestParam String deviceSecret, @RequestParam int measurement) {
        this.eventPublisher
                .publishEvent(this.measurementService.saveCount(deviceId, deviceSecret, measurement));
    }

    @GetMapping("/getLiveCount")
    public SseEmitter getLiveCount(@RequestParam int deviceId, HttpServletResponse response) {

        // 1 hour before timeout (set to 0L for no timeout at all)
        SseEmitter emitter = new SseEmitter(60000L);

        //send last measure data
        try {
            emitter.send(this.deviceService.getLastCount(deviceId)
                    .map(GetMeasurementResponse::new));
        } catch (IOException e) {
            // do nth
        }

        this.liveListeners.putIfAbsent(deviceId, new ArrayList<>());

        this.liveListeners.get(deviceId).add(emitter);

        emitter.onCompletion(() -> this.liveListeners.get(deviceId).remove(emitter));
        emitter.onTimeout(() -> this.liveListeners.get(deviceId).remove(emitter));
        emitter.onError(e -> this.liveListeners.get(deviceId).remove(emitter));

        // headers needed to avoid issues with balancer (nginx)
        response.setHeader("Content-Type", "text/event-stream");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("X-Accel-Buffering", "no");

        return emitter;
    }

    @EventListener
    public void onMeasurementReceived(Measurement count) {
        this.liveListeners.computeIfPresent(count.getDevice().getId(), (k, v) -> {
            v.forEach(listener -> {
                try {
                    listener.send(new GetMeasurementResponse(count));
                } catch (IOException e) {
                    // already handled
                }
            });
            return v;
        });
    }

    @GetMapping("/getMeasurements")
    public List<GetMeasurementResponse> getMeasurements(@RequestParam Integer deviceId) {
        return this.deviceService.getMeasurements(deviceId)
                .stream()
                .map(GetMeasurementResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/getDevices/{ownerUsername}")
    public List<GetDeviceResponse> getDevicesByOwner(@PathVariable String ownerUsername) {
        return this.deviceService.getDevices(ownerUsername)
                .stream()
                .map(GetDeviceResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("getDevice/{deviceId}")
    public GetSingleDeviceResponse getSingleDevice(Principal principal, @PathVariable int deviceId) {
        final Device device = this.deviceService.getDevice(deviceId);

        if (!device.getDeviceOwner().getUsername().equals(principal.getName()))
            device.setSecretKey(null); //only the owner has access to the secret

        return new GetSingleDeviceResponse(device);
    }

    @PostMapping("/addDevice")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateDeviceResponse addDevice(Principal principal, @RequestBody CreateDeviceRequest deviceRequest) {
        return new CreateDeviceResponse(this.deviceService.addDevice(principal.getName(), deviceRequest));
    }

    @PutMapping("/editDevice")
    public EditDeviceResponse editDevice(@RequestBody EditDeviceRequest editDeviceRequest) {
        return new EditDeviceResponse(this.deviceService.editDevice(editDeviceRequest));
    }

    @PostMapping("/deleteDevice")
    public void deleteDevice(@RequestParam Integer deviceId) {
        this.deviceService.deleteDevice(deviceId);
    }


}
