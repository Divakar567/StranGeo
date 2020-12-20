import { configureStore, combineReducers } from '@reduxjs/toolkit';
import drawerReducer from './slices/drawerSlice';

export const rootReducer = combineReducers({
	drawerReducer: drawerReducer,
});

export default configureStore({
	reducer: rootReducer,
});
