import React from 'react';
import { useKeycloak } from '@react-keycloak/web';
import AppHeadBar from './AppHeadBar';

export default function Home() {

    const { keycloak, initialized } = useKeycloak();
    return (
        <React.Fragment>
            <AppHeadBar />
            <h1>Home Page</h1>
            <div>{`User is ${!keycloak.authenticated ? 'NOT ' : ''
                }authenticated`}</div>

            {initialized ? keycloak.authenticated && <pre>{JSON.stringify(keycloak, 0, 2)}</pre> :
                <h2>keycloak is initializing...</h2>}

        </React.Fragment>
    );
}