import React, { useEffect } from 'react';
import { useKeycloak } from '@react-keycloak/web';
import { useSelector, useDispatch } from 'react-redux';
import { makeStyles } from '@material-ui/core/styles';
import { Typography, Paper, Grid } from '@material-ui/core';
import { addUser } from '../../app/slices/userSlice';
import { getUserProfile } from '../../app/requests';


const useStyles = makeStyles({
    root: {
        maxHeight: "100%",
        maxWidth: "100%",
        overflowX: "auto",
        overflowY: "auto",
    }
});

export default function Greeting() {
    const classes = useStyles();
    const { keycloak } = useKeycloak();
    const logInUser = useSelector(state => state.userReducer.users[keycloak.tokenParsed.sub] || {});
    const dispatch = useDispatch();

    useEffect(() => {
        if (logInUser && Object.keys(logInUser).length === 0) {
            getUserProfile(keycloak.tokenParsed.sub, keycloak)
                .then(response => {
                    dispatch(addUser(response.data));
                })
                .catch(error => {
                    console.log(error);
                    dispatch(addUser({ userId: keycloak.tokenParsed.sub }));
                });
        } else {

        }
    }, [keycloak, dispatch, logInUser]);

    return (
        <Grid xs={12}>
            <Paper className={classes.root}>
                <Typography variant='h6'>Hello {keycloak.idTokenParsed.name}!</Typography>
                <Typography display='block' variant='body2' component="pre">
                    <code>{JSON.stringify(logInUser, null, 4)}</code><br />
                    <code>{JSON.stringify(keycloak, null, 4)}</code>
                </Typography>
            </Paper>
        </Grid >
    );
}