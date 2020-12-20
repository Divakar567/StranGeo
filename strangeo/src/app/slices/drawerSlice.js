import { createSlice } from '@reduxjs/toolkit';

export const drawerSlice = createSlice({
    name: 'drawer',
    initialState: false,
    reducers: {
        drawerOpen: state => true,
        drawerClose: state => false,
    }
});

export const { drawerOpen, drawerClose } = drawerSlice.actions;

export default drawerSlice.reducer;