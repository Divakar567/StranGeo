import React from 'react';
import { useKeycloak } from '@react-keycloak/web';
import Button from '@material-ui/core/Button';
import { Typography } from '@material-ui/core';

export default function ProfileMenu(props) {
    const { keycloak, initialized } = useKeycloak();
    return (<Typography align={props.align} variant="h6">
        {(!initialized || !keycloak.authenticated) ?
            <Button color="inherit" variant="outlined" onClick={() => keycloak.init({ onLoad: "login-required" })}>LogIn</Button>
            :
            <Button color="inherit" variant="outlined" onClick={() => keycloak.logout()}>LogOut</Button>
        }
    </Typography>);
}