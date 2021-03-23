import React from 'react';
import { useKeycloak } from '@react-keycloak/web';
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import UserProfile from './UserProfile';

const useStyles = makeStyles((theme) => ({
    menuButton: {
        height: theme.spacing(5),
        width: theme.spacing(5),
        marginTop: 0,
        marginBottom: 8,
    },
}));
export default function ProfileMenu(props) {
    const classes = useStyles();
    const { keycloak, initialized } = useKeycloak();
    const logIn = () => keycloak.init({ onLoad: "login-required" });
    const logOut = () => keycloak.logout();

    return (
        <React.Fragment>
            {
                (!initialized || !keycloak.authenticated) ?
                    <Button color="inherit" variant="outlined" onClick={logIn}>LogIn</Button>
                    : <UserProfile userId={keycloak.tokenParsed.sub} classes={classes.menuButton} size="medium" color="default" onClick={logOut} />
                // <Fab className={classes.menuButton} size="medium" color="primary"><Avatar alt="Divakar Babu Budumuru" src="https://lh3.googleusercontent.com/ogw/ADGmqu_BvbtnR5tcu3usGGS00jm4WJ36oe56Oqv-UJDEwQ=s32-c-mo" onClick={logOut} /></Fab>
            }
        </React.Fragment>
    );
}