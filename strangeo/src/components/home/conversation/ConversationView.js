import React from 'react';
import { useParams } from 'react-router-dom';
import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import Conversation from './Conversation';
import MessageContainer from './MessageContainer';

const useStyles = makeStyles({
    root: {
        width: '100%',
        height: '87vh'
    },
});

export default function ConversationView(props) {
    const classes = useStyles();
    let { conversationId } = useParams();
    return (
        <Grid container xs={12} className={classes.root}>
            <Conversation conversationId={conversationId} />
            <MessageContainer conversationId={conversationId} />
        </Grid>
    );
}