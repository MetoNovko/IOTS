import { createStore, applyMiddleware, compose } from "redux";
import reduxThunk from "redux-thunk";
import rootReducer from "reducers/rootReducer";
function configureStore(
    state = { authState: {}, deviceState: {}, errorState: {} }) {
    const ReduxDevTools = window.__REDUX_DEVTOOLS_EXTENSION__ &&
        window.__REDUX_DEVTOOLS_EXTENSION__();
    if (window.navigator.userAgent.includes("Chrome") && ReduxDevTools) {
        return createStore(rootReducer, state, compose(applyMiddleware(reduxThunk), ReduxDevTools));
    }else{
        return createStore(rootReducer, state, applyMiddleware(reduxThunk));
    }
}

export default configureStore;
