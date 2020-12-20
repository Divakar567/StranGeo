import React from 'react';
import { useKeycloak } from '@react-keycloak/web';
import { makeStyles } from '@material-ui/core/styles';
import AppHeadBar from './AppHeadBar';
import LeftDrawer from './LeftDrawer';
import { Container, Typography } from '@material-ui/core';

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

export default function Home() {
    const { keycloak } = useKeycloak();
    const classes = useStyles();
    return (
        <Container variant="fluid" maxWidth="lg" className={classes.root}>
            <AppHeadBar />
            <LeftDrawer />
            <div className={classes.content}>
                <div className={classes.toolbar} />
                <Typography variant='h6'>Home Page</Typography>
                <Typography display='block' variant='body2' component="pre">
                    <code>{JSON.stringify(keycloak, null, 4)}</code>
                </Typography>
            </div>
        </Container>
    );
}