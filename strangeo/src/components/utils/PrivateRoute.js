import React from 'react';
import { useKeycloak } from '@react-keycloak/web';
import { Redirect, Route } from 'react-router-dom';


export default function PrivateRoute({ component, roles, ...rest }) {
    const { keycloak } = useKeycloak();

    const isAuthenticated = (roles) => {
        if (keycloak && roles) {
            return roles.some(
                r => {
                    const realm = keycloak.hasRealmRole(r);
                    const resource = keycloak.hasResourceRole(r);
                    return realm || resource;
                }
            )
        }
    }

    return (
        <Route
            {...rest}
            render={
                props => {
                    return isAuthenticated(roles) ?
                        { component } : <Redirect to="/signin" />
                }
            } />
    );
}