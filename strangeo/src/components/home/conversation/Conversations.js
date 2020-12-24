import React, { useState, useEffect } from 'react';
import { useKeycloak } from '@react-keycloak/web';
import requests from '../../../app/requests';

export default function Conversations() {
    console.log("Rendering conversations...")
    const [conversations, setConversations] = useState([]);
    const [page, setPage] = useState(0);
    const [size, setSize] = useState(20);
    const { keycloak } = useKeycloak();
    useEffect(() => {
        requests.conversation.list(page, size, keycloak)
            .then(response => setConversations(response.data.content))
            .catch(error => console.log("Request failed: ", JSON.stringify(error)));
    }, [page, size, keycloak]);


    console.log("Conversations state: ", page, size, conversations);
    return (
        <React.Fragment>
            {conversations.map(conversation => <li key={conversation.id}>{conversation.subject}</li>)}
        </React.Fragment>
    );
}