import React from 'react';
import { useKeycloak } from '@react-keycloak/web';
import { Typography, Button } from '@material-ui/core';
import { Redirect } from 'react-router-dom';

export default function Welcome() {
    const { keycloak, initialized } = useKeycloak()

    const initStatus = (
        <Typography variant="h6">
            {"Keycloak initialization status: " + (initialized ? "UP" : "DOWN")}
        </Typography>
    );

    const authStatu = (
        <Typography variant="h6">
            {"User authentication status: " + (!!keycloak.authenticated ? "Authenticated" : "Unauthenticated")}
        </Typography>
    );

    const handleClick = () => {
        if (!keycloak.authenticated) {
            keycloak.init({ onLoad: "login-required" });
        }
    };

    if (keycloak.authenticated) {
        return (<Redirect to="/home" />);
    }

    return (
        <React.Fragment>
            <Typography variant="h2">Welcome to StranGeo!</Typography>
            {initStatus}
            {authStatu}
            <Typography align="center" variant="h6">
                <Button color="inherit" variant="outlined" onClick={handleClick}>Home</Button>
            </Typography>
        </React.Fragment>
    );
}