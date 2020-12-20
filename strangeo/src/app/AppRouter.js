import React from 'react'
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import { makeStyles } from '@material-ui/core/styles';
import SecuredRoute from '../components/utils/SecuredRoute';
import Home from '../components/home/HomePage';
import Welcome from '../components/Welcome';

const routes = [
    {
        path: "/",
        exact: true,
        component: Welcome,
        isSecured: false,
        roles: [],
    },
    {
        path: "/home",
        exact: true,
        component: Home,
        isSecured: true,
        roles: ["USER"],
    },
    {
        path: "/dashboards",
        exact: true,
        component: Home,
        isSecured: true,
        roles: ["USER"],
    },
    {
        path: "/actionables",
        exact: true,
        component: Home,
        isSecured: true,
        roles: ["USER"],
    },
    {
        path: "/conversations",
        exact: true,
        component: Home,
        isSecured: true,
        roles: ["USER"],
    },
    {
        path: "/assets",
        exact: true,
        component: Home,
        isSecured: true,
        roles: ["USER"],
    },
    {
        path: "/kbases",
        exact: true,
        component: Home,
        isSecured: true,
        roles: ["USER"],
    },
]

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
                {
                    routes.map(
                        (route, index) => (
                            route.isSecured ?
                                <SecuredRoute key={index} exact={route.exact} path={route.path} roles={route.roles} component={route.component} /> :
                                <Route key={index} exact={route.exact} path={route.path} children={<route.component />} />
                        )
                    )
                }
            </Switch>
        </BrowserRouter>
    )
}

export default AppRouter;