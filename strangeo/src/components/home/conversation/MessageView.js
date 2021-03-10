import React from 'react';
import Grid from '@material-ui/core/Grid';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';

const MessageView = props => {
    return (
        <React.Fragment>
            <ListItem key={props.message.id}>
                <Grid container>
                    <Grid item xs={12}>
                        <ListItemText align={props.user === props.message.sentBy ? "right" : "left"} primary={props.message.payload}></ListItemText>
                    </Grid>
                    <Grid item xs={12}>
                        <ListItemText align={props.user === props.message.sentBy ? "right" : "left"} secondary={props.message.createdDate}></ListItemText>
                    </Grid>
                </Grid>
            </ListItem>
        </React.Fragment>
    );
}

export default MessageView;