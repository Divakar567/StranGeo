import { createSlice } from '@reduxjs/toolkit';

export const userSlice = createSlice({
    name: 'user',
    initialState: {
        users: {},
    },
    reducers: {
        addUser: (state, action) => {
            console.log(action);
            state.users[action.payload.userId] = action.payload;
        },
    }
});

export const { addUser } = userSlice.actions;

export default userSlice.reducer;