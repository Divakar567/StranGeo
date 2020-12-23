import axios from 'axios';
import { generatePath } from "react-router";

export const baseURL = "http://localhost:8080";
export const conversationGetURL = "/api/conversations/{conversationId}";

export const axiosConfig = {
    baseURL: baseURL,
    timeout: 60000,
}
export const axiosInstance = axios.create(axiosConfig);

export function getConversation(conversationId, authorization) {
    let config = {
        url: generatePath(conversationGetURL, { conversationId: conversationId }),
        method: 'get',
        headers: {
            "Authorization": authorization,
        },

    };

    return axiosInstance.request(config);
}

export function postConversation(data, token) {

}

export default requests = {
    conversation: {
        get: getConversation,
        post: postConversation,
    }
}