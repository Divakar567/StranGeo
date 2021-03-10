import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { useKeycloak } from '@react-keycloak/web';
import AppHeadBar from './AppHeadBar';
import LeftDrawer from './LeftDrawer';
import { Container, Typography, Button } from '@material-ui/core';
import { HomeRouter } from '../../app/routes';

const useStyles = makeStyles((theme) => ({
    root: {
        paddingLeft: theme.spacing(0.5),
        paddingRight: theme.spacing(0.5),
        marginLeft: theme.spacing(0),
        marginRight: theme.spacing(0),
        display: 'flex',
    },
    toolbar: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'flex-end',
        padding: theme.spacing(0, 1),
        // necessary for content to be below app bar
        ...theme.mixins.toolbar,
    },
    content: {
        flexGrow: 1,
        padding: theme.spacing(1),
    },
}));

export default function Home(props) {
    const classes = useStyles();
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

    if (!keycloak.authenticated) {
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

    return (
        <Container variant="fluid" maxWidth="xl" className={classes.root}>
            <AppHeadBar />
            <LeftDrawer />
            <div className={classes.content}>
                <div className={classes.toolbar} />
                <HomeRouter />
            </div>
        </Container>
    );
}