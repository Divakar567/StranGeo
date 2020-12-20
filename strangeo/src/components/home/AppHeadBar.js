import React from 'react';
import { IconButton, Button, Typography, Toolbar, AppBar } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import AppsIcon from '@material-ui/icons/Apps';
import { Link } from 'react-router-dom';
import ProfileMenu from '../user/ProfileMenu';
import GlobalSearch from './GlobalSearch';

const useStyles = makeStyles((theme) => ({
    grow: {
        flexGrow: 1,
    },
    appBar: {
        height: theme.spacing(8),
        zIndex: theme.zIndex.drawer + 1,
        transition: theme.transitions.create(['width', 'margin'], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.leavingScreen,
        }),
    },
    menuButton: {
        marignLeft: 5,
        marginRight: 5,
    },
}));


export default function AppHeadBar(props) {
    const classes = useStyles();

    return (
        <AppBar className={classes.appBar} position="fixed">
            <Toolbar className={classes.appBar}>
                <IconButton
                    edge="start"
                    color="inherit"
                    aria-label="open drawer"
                    className={classes.menuButton}
                >
                    <AppsIcon />
                </IconButton>

                <Button color="inherit" size="medium" variant="text" component={Link} to="/home">
                    <Typography align="left" variant="h6">
                        StranGeo
                    </Typography>
                </Button>
                <GlobalSearch />
                <div className={classes.grow}>
                    <ProfileMenu align="right" />
                </div>
            </Toolbar>
        </AppBar>
    );
}