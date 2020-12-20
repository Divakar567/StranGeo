import React from 'react';
import { ReactKeycloakProvider } from '@react-keycloak/web';
import keycloak from './app/auth';
import { Provider } from 'react-redux';
import store from './app/store';
import AppRouter from './app/AppRouter';
import CssBaseline from '@material-ui/core/CssBaseline';

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
      <Provider store={store}>
        <CssBaseline />
        <AppRouter />
      </Provider>
    </ReactKeycloakProvider>
  );
}

export default App;
