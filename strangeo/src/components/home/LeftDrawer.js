import React from 'react';
import clsx from 'clsx';
import { connect } from 'react-redux';
import { makeStyles } from '@material-ui/core/styles';
import Drawer from '@material-ui/core/Drawer';
import List from '@material-ui/core/List';
import Divider from '@material-ui/core/Divider';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import ChevronRightIcon from '@material-ui/icons/ChevronRight';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import WorkIcon from '@material-ui/icons/Work';
import DashboardIcon from '@material-ui/icons/Dashboard';
import ForumIcon from '@material-ui/icons/Forum';
import BusinessIcon from '@material-ui/icons/Business';
import LibraryBooksIcon from '@material-ui/icons/LibraryBooks';
import { Link } from 'react-router-dom';
import { drawerOpen, drawerClose } from '../../app/slices/drawerSlice';

const drawerWidth = 200;

const useStyles = makeStyles((theme) => ({
    drawer: {
        width: drawerWidth,
        flexShrink: 0,
        whiteSpace: 'nowrap',
    },
    drawerOpen: {
        width: drawerWidth,
        transition: theme.transitions.create('width', {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.enteringScreen,
        }),
    },
    drawerClose: {
        transition: theme.transitions.create('width', {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.leavingScreen,
        }),
        overflowX: 'hidden',
        width: theme.spacing(6) + 1,
        [theme.breakpoints.up('md')]: {
            width: theme.spacing(8) + 1,
        },
    },
    toolbar: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'flex-end',
        padding: theme.spacing(0, 1),
        // necessary for content to be below app bar
        ...theme.mixins.toolbar,
    }
}));


function LeftDrawer(props) {
    const classes = useStyles();
    return (
        <Drawer
            variant="permanent"
            className={clsx(classes.drawer, {
                [classes.drawerOpen]: props.drawer,
                [classes.drawerClose]: !props.drawer,
            })}
            classes={{
                paper: clsx({
                    [classes.drawerOpen]: props.drawer,
                    [classes.drawerClose]: !props.drawer,
                }),
            }}
        >
            <div className={classes.toolbar} />
            <List>
                <ListItem button key={"Collapse"} onClick={props.drawer ? () => props.drawerClose() : () => props.drawerOpen()}>
                    <ListItemIcon>
                        {props.display ? <ChevronLeftIcon /> : <ChevronRightIcon />}
                    </ListItemIcon>
                    <ListItemText primary={"Collapse"} />
                </ListItem>
                <Divider />
                <ListItem button key={"Dashboards"} component={Link} to="/dashboards">
                    <ListItemIcon>
                        <DashboardIcon />
                    </ListItemIcon>
                    <ListItemText primary={"Dashboards"} />
                </ListItem>
                <ListItem button key={"Actionables"} component={Link} to="/actionables">
                    <ListItemIcon>
                        <WorkIcon />
                    </ListItemIcon>
                    <ListItemText primary={"Actionables"} />
                </ListItem>
                <ListItem button key={"Conversations"} component={Link} to="/conversations">
                    <ListItemIcon>
                        <ForumIcon />
                    </ListItemIcon>
                    <ListItemText primary={"Conversations"} />
                </ListItem>
                <Divider />
                <ListItem button key={"Inventory"} component={Link} to="/assets">
                    <ListItemIcon>
                        <BusinessIcon />
                    </ListItemIcon>
                    <ListItemText primary={"Inventory"} />
                </ListItem>
                <ListItem button key={"Knowledge"} component={Link} to="/kbases">
                    <ListItemIcon>
                        <LibraryBooksIcon />
                    </ListItemIcon>
                    <ListItemText primary={"Knowledge"} />
                </ListItem>
            </List>
        </Drawer >
    );
};

const mapState = state => ({ drawer: state.drawerReducer });
const actionCreators = {
    drawerOpen, drawerClose,
};
export default connect(mapState, actionCreators)(LeftDrawer);