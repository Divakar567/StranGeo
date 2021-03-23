import React from 'react'
import { BrowserRouter, Switch, Route, useLocation, useRouteMatch } from 'react-router-dom';
import SecuredRoute from '../components/utils/SecuredRoute';

import Welcome from '../components/Welcome';
import DevelopmentPage from '../components/utils/DevelopmentPage';
import Home from '../components/home/Home';
import Greeting from '../components/home/Greeting';
import ConversationListView from '../components/home/conversation/ConversationListView';
import ConversationView from '../components/home/conversation/ConversationView';

const routes = [
    {
        path: "/",
        exact: true,
        component: Welcome,
        isSecured: false,
        roles: [],
    },
    {
        path: "/**",
        exact: true,
        component: Home,
        isSecured: true,
        roles: ["USER"],
    },
]

const home = [
    {
        path: "/home",
        exact: true,
        component: Greeting,
        isSecured: true,
        roles: ["USER"],
    },
    {
        path: "/dashboards",
        exact: true,
        component: DevelopmentPage,
        isSecured: true,
        roles: ["USER"],
    },
    {
        path: "/actionables",
        exact: true,
        component: DevelopmentPage,
        isSecured: true,
        roles: ["USER"],
    },
    {
        path: "/conversations",
        exact: true,
        component: ConversationListView,
        isSecured: true,
        roles: ["USER"],
    },
    {
        path: "/assets",
        exact: true,
        component: DevelopmentPage,
        isSecured: true,
        roles: ["USER"],
    },
    {
        path: "/kbases",
        exact: true,
        component: DevelopmentPage,
        isSecured: true,
        roles: ["USER"],
    },
    {
        path: "/conversations/**",
        exact: true,
        component: ConversationRouter,
        isSecured: true,
        roles: ["USER"],
    },
    {
        path: "",
        exact: true,
        component: Greeting,
        isSecured: true,
        roles: ["USER"],
    },
]

const conversation = [
    {
        path: "/conversations/:conversationId",
        exact: true,
        component: ConversationView,
        isSecured: true,
        roles: ["USER"],
    },
]

export default function AppRouter() {
    console.log("Rendering AppRouter...");
    return (
        <BrowserRouter>
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

export function HomeRouter() {
    let location = useLocation();
    console.log("Location: ", location);
    return (
        // <BrowserRouter>
        <Switch>
            {
                home.map(
                    (route, index) => (
                        route.isSecured ?
                            <SecuredRoute key={index} exact={route.exact} path={route.path} roles={route.roles} component={route.component} /> :
                            <Route key={index} exact={route.exact} path={route.path} children={<route.component />} />
                    )
                )
            }
        </Switch>
        // </BrowserRouter>
    )
}

export function ConversationRouter() {
    let { path, url } = useRouteMatch();
    console.log("Path: ", path);
    console.log("URL: ", url);
    return (
        <Switch>
            {
                conversation.map(
                    (route, index) => (
                        route.isSecured ?
                            <SecuredRoute key={index} exact={route.exact} path={route.path} roles={route.roles} component={route.component} /> :
                            <Route key={index} exact={route.exact} path={route.path} children={<route.component />} />
                    )
                )
            }
        </Switch>
    )
}