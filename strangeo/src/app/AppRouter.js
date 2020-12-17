import React from 'react'
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import { makeStyles } from '@material-ui/core/styles';
import PrivateRoute from '../components/utils/PrivateRoute';
import Home from '../components/home/Home';
import SignIn from '../components/user/SignIn';
import SignUp from '../components/user/SignUp';
import ProtectedPage from '../components/home/ProtectedPage';

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
    }
}));

const AppRouter = () => {
    const classes = useStyles();
    return (
        <BrowserRouter
            className={classes.root}>
            <Switch>
                <PrivateRoute path="/protected" roles={["PROTECTED"]} component={ProtectedPage} />
                <Route path="/signin">
                    <SignIn />
                </Route>
                <Route path="/signup">
                    <SignUp />
                </Route>
                <Route path="/">
                    <Home />
                </Route>
            </Switch>
        </BrowserRouter>
    )
}

export default AppRouter;