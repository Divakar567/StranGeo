import React, { useState, useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { useKeycloak } from '@react-keycloak/web';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import { getConversation } from '../../../app/requests';

const useStyles = makeStyles({
    root: {
        borderRight: '5px solid #e0e0e0'
    },
});

export default function Conversation(props) {
    const classes = useStyles();
    const { keycloak } = useKeycloak();
    const [conversation, setConversation] = useState({});

    useEffect(() => {
        console.log("ConversationId: ", props.conversationId);
        getConversation(props.conversationId, keycloak).then((response) => {
            console.log("Conversation ", response.data);
            setConversation(response.data);
        })
    }, [props, keycloak]);

    return (
        <Grid item xs={5} component={Paper} className={classes.root}>
            <Typography variant="h6" className={classes.root}>Subject: {conversation.subject}</Typography>
            <Typography variant="h6" className={classes.root}>Priority: {conversation.priority}</Typography>
            <Typography variant="h6" className={classes.root}>Status: {conversation.status}</Typography>
        </Grid>
    );
}
