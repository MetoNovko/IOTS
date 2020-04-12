import axios from "axios";
import {
    GET_ERRORS,
    GET_DEVICES,
    DELETE_DEVICE,
    ADD_DEVICE,
    GET_MEASUREMENTS,
    GET_LIVE_MEASUREMENT
} from "./types";


export const addDevice = (device) => async dispatch => {
    try {
        await axios.post("/api/device/addDevice", device);
        dispatch({
            type: ADD_DEVICE,
            payload: {}
        });
    } catch (err) {
        dispatch({
            type: GET_ERRORS,
            payload: {}
        });
    }
};

export const getDevices = (ownerUsername) => async dispatch => {
    const res = await axios.get(`/api/device/getDevices/${ownerUsername}`);
    let send = false;
    res.data.forEach((device) => {
        const eventSource =
            new EventSource(`http://skopje.ml/api/device/getLiveCount?deviceId=${device.deviceId}&token=Bearer ${localStorage.getItem('jwtToken')}`);
        if (!send) {
            dispatch(getMeasurements(device.deviceId, device.model));
            send = true;
        }
        eventSource.onopen = e => {
            console.log(e);
        };
        eventSource.onmessage = e => {
            const data = JSON.parse(e.data);
            dispatch({
                type: GET_LIVE_MEASUREMENT,
                payload: {deviceId: device.deviceId, data}
            });
        };
    });
    dispatch({
        type: GET_DEVICES,
        payload: res.data
    });
};


export const deleteDevice = deviceId => async dispatch => {
    await axios.post(
        `/api/device/deleteDevice?deviceId=${deviceId}`
    );
    dispatch({
        type: DELETE_DEVICE,
        payload: deviceId
    });

};

export const getMeasurements = (deviceId, deviceName) => async dispatch => {
    try {
        const res = await axios.get(
            `/api/device/getMeasurements?deviceId=${deviceId}`
        );
        dispatch({
            type: GET_MEASUREMENTS,
            payload: {deviceName, data: res.data}
        });
    } catch (err) {
        dispatch({
            type: GET_ERRORS,
            payload: {}//err.response.data
        });
    }
};
