import axios from 'axios';
import { generatePath } from "react-router";

export const baseURL = "http://localhost:9091/api";
export const conversationsGetURL = "/conversations";
export const conversationPostURL = "/conversations";
export const conversationGetURL = "/conversations/:conversationId";

export const axiosConfig = {
    baseURL: baseURL,
    timeout: 60000,
}
export const axiosInstance = axios.create(axiosConfig);

export const getAuthorization = (keycloak) => {
    return (keycloak.tokenParsed.typ + " " + keycloak.token);
}

export function getConversation(conversationId, keycloak) {
    let config = {
        url: generatePath(conversationGetURL, { conversationId: conversationId }),
        method: 'get',
        headers: {
            "Authorization": getAuthorization(keycloak),
        },

    };

    return axiosInstance.request(config);
}

export function getConversations(page, size, keycloak) {
    let config = {
        url: conversationsGetURL,
        method: 'get',
        headers: {
            "Authorization": getAuthorization(keycloak),
            "Content-Type": 'application/json',
        },
        params: {
            page: page,
            size: size,
        }
    };
    console.log("Request config: ", JSON.stringify(config));
    return axiosInstance.request(config);
}

export function postConversation(data, keycloak) {
    let config = {
        url: conversationPostURL,
        method: 'post',
        headers: {
            "Authorization": getAuthorization(keycloak),
        },
        data: data,
    };

    return axiosInstance.request(config);
}

const requests = {
    conversation: {
        get: getConversation,
        list: getConversations,
        post: postConversation,
    }
}

export default requests;