import React from 'react';
import { useKeycloak } from '@react-keycloak/web';
import { Typography } from '@material-ui/core';

export default function Greeting() {
    const { keycloak } = useKeycloak();
    return (
        <React.Fragment>
            <Typography variant='h6'>Hello {keycloak.idTokenParsed.name}!</Typography>
            <Typography display='block' variant='body2' component="pre">
                <code>{JSON.stringify(keycloak, null, 4)}</code>
            </Typography>
        </React.Fragment>
    );
}