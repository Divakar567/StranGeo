import React from 'react';
import { IconButton, Button, Typography, Toolbar, AppBar } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import AppsIcon from '@material-ui/icons/Apps';
import { Link } from 'react-router-dom';
import ProfileMenu from '../user/ProfileMenu';

const useStyles = makeStyles((theme) => ({
    grow: {
        flexGrow: 1,
    },
    appBar: {
        height: theme.spacing(7),
        zIndex: theme.zIndex.drawer + 1,
    },
    menuButton: {
        height: theme.spacing(5),
        marignLeft: 5,
        marginRight: 5,
        marginTop: 0,
        marginBottom: 8,
        textTransform: 'none',
    },
}));


export default function AppHeadBar(props) {
    const classes = useStyles();

    return (
        <AppBar className={classes.appBar} position="fixed">
            <Toolbar>
                <IconButton
                    edge="start"
                    color="inherit"
                    className={classes.menuButton}
                >
                    <AppsIcon />
                </IconButton>

                <Button className={classes.menuButton} color="inherit" size="medium" variant="text" component={Link} to="/home">
                    <Typography align="left" variant="h6">
                        StranGeo
                    </Typography>
                </Button>
                <div className={classes.grow} />
                <ProfileMenu />
            </Toolbar>
        </AppBar >
    );
}