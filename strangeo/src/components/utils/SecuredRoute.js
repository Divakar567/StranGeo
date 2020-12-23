import { useKeycloak } from '@react-keycloak/web';
import React from 'react';
import { Route, useLocation } from 'react-router-dom';
import Welcome from '../Welcome';


export default function SecuredRoute({ component: Component, roles, ...rest }) {
    console.log("Rendering SecuredRoute...");
    const { keycloak } = useKeycloak();
    const location = useLocation();

    const isAutherized = (roles) => {
        if (keycloak && roles) {
            return roles.some(r => {
                const realm = keycloak.hasRealmRole(r);
                const resource = keycloak.hasResourceRole(r);
                return realm || resource;
            });
        }
        return false;
    }

    return (
        <Route
            {...rest}
            render={props => {
                console.log("Secured routing...", props);
                return (isAutherized(roles)
                    ? <Component {...props} />
                    : <Welcome redirect={location.pathname} />);
            }}
        />
    )
}