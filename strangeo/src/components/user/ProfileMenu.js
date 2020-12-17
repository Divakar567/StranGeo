import React from 'react';
import { useKeycloak } from '@react-keycloak/web';
import Button from '@material-ui/core/Button';

export default function ProfileMenu() {
    const { keycloak, initialized } = useKeycloak();
    if (!initialized || !keycloak.authenticated) {
        return (<Button color="inherit" variant="outlined" onClick={() => keycloak.init({ onLoad: "login-required" })}>LogIn</Button>);
    } else {
        return (<Button color="inherit" variant="outlined" onClick={() => keycloak.logout()}>LogOut</Button>);
    }
}