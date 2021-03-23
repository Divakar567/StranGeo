import React from 'react';
import { useKeycloak } from '@react-keycloak/web';
import { Typography, Button, Grid } from '@material-ui/core';
import { Redirect, useLocation, Link } from 'react-router-dom';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import IconButton from '@material-ui/core/IconButton';
import AppsIcon from '@material-ui/icons/Apps';
import ProfileMenu from './user/ProfileMenu';
import AppHeadBar from './home/AppHeadBar';

export default function Welcome(props) {
    console.log("Rendering Welcome...", useLocation());
    const { keycloak, initialized } = useKeycloak();

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
        return (<Redirect to={props.pathname || "/home"} />);
    }

    return (
        <Grid container direction="column" justify="flex-start" alignItems="stretch">
            <Grid item>
                <AppBar position="static">
                    <Toolbar>
                        <Grid container direction="row" justify="space-between" alignItems="baseline">
                            <Grid item>
                                <IconButton edge="start" color="inherit">
                                    <AppsIcon />
                                </IconButton>
                                <Button color="inherit" size="medium" variant="text" component={Link} to="/home">
                                    <Typography align="left" variant="h6">
                                        StranGeo
                                    </Typography>
                                </Button>
                            </Grid>
                            <Grid item>
                                <ProfileMenu />
                            </Grid>
                        </Grid>
                    </Toolbar>
                </AppBar>
            </Grid>
            <Grid item>
                <Typography variant="h2">Welcome to StranGeo!</Typography>
                {initStatus}
                {authStatu}
                <Typography align="center" variant="h6">
                    <Button color="inherit" variant="outlined" onClick={handleClick}>Home</Button>
                </Typography>
            </Grid>
        </Grid>
    );
}