import {
    GET_DEVICES,
    GET_DEVICE,
    DELETE_DEVICE,
    ADD_DEVICE,
    GET_MEASUREMENTS,
    GET_LIVE_MEASUREMENT
} from "../actions/types";

const initialState = {
    devices: [],
    device: {}
};

export default function (state = initialState, action) {
    switch (action.type) {
        case GET_DEVICES:
            return {
                ...state,
                devices: action.payload
            };

        case GET_DEVICE:
            return {
                ...state,
                device: action.payload
            };

        case DELETE_DEVICE:
            return {
                ...state,
                devices: state.devices.filter(
                    device => device.deviceId !== action.payload
                )
            };
        case ADD_DEVICE:
            return {
                ...state,
                devices: state.devices.concat(action.payload)
            };
        case GET_MEASUREMENTS:
            console.log(action.payload);
            return {
                ...state,
                measurements: action.payload.data,
                deviceName: action.payload.deviceName
            };
        case GET_LIVE_MEASUREMENT: {
            return {
                ...state,
                devices: state.devices.map(d => {
                    if (d.deviceId === action.payload.deviceId) {
                        d.lastMeasurement = action.payload.data;
                    }
                    return d;
                })
            }
        }
        default:
            return state;
    }
}
