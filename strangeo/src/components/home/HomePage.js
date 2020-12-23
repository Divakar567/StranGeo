import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { useKeycloak } from '@react-keycloak/web';
import AppHeadBar from './AppHeadBar';
import LeftDrawer from './LeftDrawer';
import { Container } from '@material-ui/core';
import { HomeRouter } from '../../app/routes';
import { Redirect, useLocation } from 'react-router-dom';

const useStyles = makeStyles((theme) => ({
    root: {
        paddingLeft: theme.spacing(0.5),
        marginLeft: theme.spacing(0),
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
    const location = useLocation();
    console.log("Rendering home page...", location);
    const classes = useStyles();
    const { keycloak, initialized } = useKeycloak();
    if (!initialized || !keycloak.authenticated) {
        return <Redirect to='/' />;
    }

    return (
        <Container variant="fluid" maxWidth="lg" className={classes.root}>
            <AppHeadBar />
            <LeftDrawer />
            <div className={classes.content}>
                <div className={classes.toolbar} />
                <HomeRouter />
            </div>
        </Container>
    );
}