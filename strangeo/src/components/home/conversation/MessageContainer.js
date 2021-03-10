import React, { useState } from 'react';
import { useKeycloak } from '@react-keycloak/web';
import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import List from '@material-ui/core/List';
import MessageSender from './MessageSender';
import MessageView from './MessageView';

const useStyles = makeStyles({
    root: {
        overflowY: 'auto',
    },
    messageArea: {
        height: '75vh',
        overflow: 'auto',
    }
});

export default function MessageContainer(props) {
    const classes = useStyles();
    const { keycloak } = useKeycloak();
    const [messages, setMessages] = useState([]);
    const onMessageReceive = (msg) => {
        setMessages([...messages, msg]);
    }
    return (
        <Grid item component={Paper} xs={7}>
            <List className={classes.messageArea}>
                {messages.map(msg => <MessageView message={msg} user={keycloak.tokenParsed.sub} />)}
            </List>
            <MessageSender conversationId={props.conversationId} onMessageReceive={onMessageReceive} />
        </Grid>
    );
}