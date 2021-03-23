import { configureStore, combineReducers } from '@reduxjs/toolkit';
import drawerReducer from './slices/drawerSlice';
import userReducer from './slices/userSlice';

export const rootReducer = combineReducers({
	drawerReducer: drawerReducer,
	userReducer: userReducer,
});

export default configureStore({
	reducer: rootReducer,
});
