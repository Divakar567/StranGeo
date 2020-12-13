import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import {BrowserRouter as Router,
  Switch,
  Route,Link as RoterLink} from 'react-router-dom';
import SignIn from './components/signin/SignIn';

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
  },
}));

function App() {
  const classes = useStyles();

  return (
    <Router className={classes.root}>
      
      <AppBar position="static">
        <Toolbar>
          <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu">
            <MenuIcon />
          </IconButton>
          <Typography variant="h6" className={classes.title}>
            Synthia
          </Typography>
          <Button color="primary" variant = "contained" component = {RoterLink} to="/signup">SignUp</Button>
          <Button color="inherit" variant = "outlined" component = {RoterLink} to="/signin">SignIn</Button>
        </Toolbar>
      </AppBar>
      
      <Switch>
        <Route path="/signin">
          <SignIn />
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
