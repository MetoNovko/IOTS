import { combineReducers } from "redux";
import authReducer from "./authReducer";
import deviceReducer from "./deviceReducer";
import errorReducer from "./errorReducer"

export default combineReducers({
    // the authReducer will work only with authState
    authState: authReducer,
    // the deviceReducer will work only with deviceState
    deviceState: deviceReducer,
    // errorState
    errorState: errorReducer
});
