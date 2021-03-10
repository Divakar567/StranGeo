import React, { useState } from 'react';
import SockJsClient from 'react-stomp';
import { useKeycloak } from '@react-keycloak/web';
import { useFormik } from 'formik';
import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Fab from '@material-ui/core/Fab';
import SendIcon from '@material-ui/icons/Send';
import { messagingWSURL } from '../../../app/requests';

const useStyles = makeStyles({
    root: {
        borderUp: '5px solid #e0e0e0'
    },
});

export default function MessageSender(props) {

    let wsRef = null;
    const classes = useStyles();
    const { keycloak } = useKeycloak();
    const [clientConnected, setClientConnected] = useState(false);

    const formik = useFormik({
        initialValues: {
            type: 'TEXT',
            payload: '',
            conversationId: props.conversationId,
            sentBy: keycloak.tokenParsed.sub,
        },
        onSubmit: (values, { resetForm }) => {
            if (clientConnected) {
                wsRef.sendMessage('/app/conversations/' + props.conversationId + '/messages', JSON.stringify(values, null, 2));
                resetForm({
                    values: {
                        type: 'TEXT',
                        payload: '',
                        conversationId: props.conversationId,
                        sentBy: keycloak.tokenParsed.sub,
                    }
                });
            } else {
                alert("Message sending failed. Please refresh...");
            }
        },
    });

    return (
        <Grid item className={classes.root}>
            <SockJsClient url={messagingWSURL + "?access_token=" + keycloak.token}
                headers={{ 'WWW-Authorization': (keycloak.tokenParsed.typ + " " + keycloak.token) }}
                subscribeHeaders={{ 'WWW-Authorization': (keycloak.tokenParsed.typ + " " + keycloak.token) }}
                topics={["/topic/conversations/" + props.conversationId + "/messages"]}
                onMessage={props.onMessageReceive} ref={(client) => { wsRef = client }}
                onConnect={() => { setClientConnected(true) }}
                onDisconnect={() => { setClientConnected(false) }}
                debug={false} />

            <Grid container style={{ padding: '10px' }} justify="flex-end" alignItems="flex-end">
                <Grid component="form" item xs={11} onSubmit={formik.handleSubmit}>
                    <TextField
                        id="standard-full-width"
                        placeholder="Type message here..."
                        fullWidth
                        margin="normal"
                        InputLabelProps={{
                            shrink: true,
                        }}
                        name="payload"
                        type="text"
                        onChange={formik.handleChange}
                        value={formik.values.payload}
                    />
                </Grid>
                <Grid item xs={1}>
                    <Fab component={Button} color="primary" onClick={formik.handleSubmit}>
                        <SendIcon />
                    </Fab>
                </Grid>
            </Grid>
        </Grid>
    );
}
