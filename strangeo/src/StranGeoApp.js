import React from 'react';
import { ReactKeycloakProvider } from '@react-keycloak/web';
import keycloak from './app/keycloak';
import { Provider } from 'react-redux';
import store from './app/store';
import AppRouter from './app/routes';
import CssBaseline from '@material-ui/core/CssBaseline';

const eventLogger = (event, error) => {
  console.debug('onKeycloakEvent', event, error)
}

const tokenLogger = (tokens) => {
  console.debug('onKeycloakTokens', tokens)
}

function StranGeoApp() {
  return (
    <ReactKeycloakProvider
      authClient={keycloak}
      initOptions={{
        onLoad: 'check-sso',
      }}
      onEvent={eventLogger}
      onTokens={tokenLogger}>
      <Provider store={store}>
        <CssBaseline />
        <AppRouter />
      </Provider>
    </ReactKeycloakProvider>
  );
}

export default StranGeoApp;
