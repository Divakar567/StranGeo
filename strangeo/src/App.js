import React from 'react';
import { ReactKeycloakProvider } from '@react-keycloak/web';
import keycloak from './app/auth';
import AppRouter from './app/AppRouter';

const eventLogger = (event, error) => {
  console.log('onKeycloakEvent', event, error)
}

const tokenLogger = (tokens) => {
  console.log('onKeycloakTokens', tokens)
}

function App() {
  return (
    <ReactKeycloakProvider
      authClient={keycloak}
      initOptions={{
        onLoad: 'check-sso',
      }}
      onEvent={eventLogger}
      onTokens={tokenLogger}>
      <AppRouter />
    </ReactKeycloakProvider>
  );
}

export default App;
