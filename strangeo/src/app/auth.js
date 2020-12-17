import Keycloak from 'keycloak-js';

export const KEYCLOAK_BASE_URI = "http://localhost:8080/";
export const KEYCLOAK_CLIENT_ID = "strangeo-react-app";
export const KEYCLOAK_CONFIG = {
    "url": "http://localhost:8080/auth/",
    "realm": "StranGeoKeyCloak",
    "clientId": "strangeo-react-app",
    "auth-server-url": "http://localhost:8080/auth/",
    "ssl-required": "external",
    "resource": "strangeo-react-app",
    "public-client": true,
    "confidential-port": 0
};

const keycloak = new Keycloak(KEYCLOAK_CONFIG);
export default keycloak;