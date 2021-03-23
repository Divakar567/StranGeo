import React, { useEffect } from 'react';
import { useKeycloak } from '@react-keycloak/web';
import { useSelector, useDispatch } from 'react-redux';
import Avatar from '@material-ui/core/Avatar';
import Fab from '@material-ui/core/Fab';
import { addUser } from '../../app/slices/userSlice';
import { getUserProfile } from '../../app/requests';

export default function UserProfile(props) {
    const { keycloak } = useKeycloak();
    const userProfile = useSelector(state => {
        return state.userReducer.users[props.userId];
    }) || {};
    const dispatch = useDispatch();

    useEffect(() => {
        if (!userProfile || Object.keys(userProfile).length === 0) {
            getUserProfile(props.userId, keycloak)
                .then(response => {
                    dispatch(addUser(response.data));
                })
                .catch(error => {
                    console.log(error);
                    // if(error.st)
                });
        } else {

        }
    });

    return (
        <Fab className={props.classes}
            size={props.size || "medium"}
            color={props.color || "primary"}>
            {
                (!userProfile || Object.keys(userProfile).length === 0) ?
                    "GU" :
                    <Avatar alt={userProfile.userName || "User"}
                        src={userProfile.imageLink}
                        onClick={props.onClick} />
            }
        </Fab>
    );
}