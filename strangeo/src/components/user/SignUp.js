import React from 'react';
import { Card, CardContent, CardActions, Typography, TextField, InputAdornment, FormControl, FormLabel, RadioGroup, FormControlLabel, Radio, Button } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import { VisibilityOffSharp } from '@material-ui/icons';
import { Formik } from 'formik';
import * as Yup from 'yup';
import axios from 'axios';
import { SIGNUP_URL } from '../../app/constants';

const validationSchema = Yup.object({
    userName: Yup.string("Enter username")
        .required("Username is required")
        .min(8, 'Username should be of minimum 8 characters length'),
    email: Yup.string("Enter email")
        .required("Email is required")
        .email('Enter valid email'),
    firstName: Yup.string("Enter firstname")
        .required("Firstname is required"),
    lastName: Yup.string("Enter lastname")
        .required("Lastname is required"),
    middleName: Yup.mixed()
        .notRequired(),
    dateOfBirth: Yup.date("Enter date of birth").required("Date of birth is required"),
    gender: Yup.mixed().oneOf(['MALE', 'FEMALE'], "Invalid gender"),
    password: Yup.string("Enter password").required("Password is required").min(8, "Password should be of minimum 8 characters length"),
    passwordConfirmation: Yup.string("Confirm password").oneOf([Yup.ref('password'), null], "Password must match")
});

const useStyles = makeStyles(theme => ({
    root: {
        flexGrow: 1,
    },
    card: {
        padding: theme.spacing(2),
        margin: 'auto',
        maxWidth: 500
    },
    radio: {
        paddingTop: theme.spacing(2)
    }
}));

export default function SignUp() {
    const classes = useStyles();
    return (
        <Formik className={classes.root}
            initialValues={{
                userName: '',
                email: '',
                firstName: '',
                lastName: '',
                middleName: '',
                dateOfBirth: new Date(),
                gender: 'MALE',
                password: '',
                passwordConfirmation: '',
            }}
            validationSchema={validationSchema}
            onSubmit={(values, actions) => {
                axios({
                    url: SIGNUP_URL,
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                        "Accept": "application/json"
                    },
                    data: values,
                }).then(response => {
                    alert(JSON.stringify(response, 0, 2));
                    actions.setSubmitting(false);
                }).catch(error => {
                    alert(JSON.stringify(error, 0, 2))
                    actions.setSubmitting(false);
                })
            }}
        >
            {
                formik => (
                    <Card className={classes.card} component="form" variant="outlined">
                        <CardContent>
                            <Typography component="h2" variant="h5" gutterBottom>
                                SignUp
                            </Typography>

                            <TextField
                                fullWidth
                                id="userName"
                                name="userName"
                                label="Username"
                                value={formik.values.userName}
                                onChange={formik.handleChange}
                                error={formik.touched.userName && Boolean(formik.errors.userName)}
                                helperText={formik.touched.userName && formik.error.userName}
                            />
                            <TextField
                                fullWidth
                                id="email"
                                name="email"
                                label="Email"
                                value={formik.values.email}
                                onChange={formik.handleChange}
                                error={formik.touched.email && Boolean(formik.errors.email)}
                                helperText={formik.touched.email && formik.errors.email}
                            />
                            <TextField
                                fullWidth
                                id="firstname"
                                name="firstName"
                                label="FirstName"
                                value={formik.values.firstName}
                                onChange={formik.handleChange}
                                error={formik.touched.firstName && Boolean(formik.errors.firstName)}
                                helperText={formik.touched.firstName && formik.errors.firstName}
                            />
                            <TextField
                                fullWidth
                                id="lastname"
                                name="lastName"
                                label="LastName"
                                value={formik.values.lastName}
                                onChange={formik.handleChange}
                                error={formik.touched.lastName && Boolean(formik.errors.lastName)}
                                helperText={formik.touched.lastName && formik.errors.lastName}
                            />
                            <TextField
                                fullWidth
                                id="middlename"
                                name="middleName"
                                label="MiddleName"
                                value={formik.values.middleName}
                                onChange={formik.handleChange}
                                error={formik.touched.middleName && Boolean(formik.errors.middleName)}
                                helperText={formik.touched.middleName && formik.errors.middleName}
                            />
                            <FormControl component="fieldset">
                                <FormLabel className={classes.radio} component="legend">Gender</FormLabel>
                                <RadioGroup row aria-label="Gender" name="gender" value={formik.values.gender} onChange={formik.handleChange}>
                                    <FormControlLabel value="MALE" control={<Radio />} label="Male" />
                                    <FormControlLabel value="FEMALE" control={<Radio />} label="Female" />
                                </RadioGroup>
                                <FormLabel className={classes.radio} component="legend">Date of Birth</FormLabel>
                                <TextField
                                    id="dateOfBirth"
                                    type="date"
                                    defaultValue={formik.values.dateOfBirth}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                />
                            </FormControl>

                            <TextField
                                fullWidth
                                id="password"
                                name="password"
                                label="Password"
                                value={formik.values.password}
                                onChange={formik.handleChange}
                                error={formik.touched.password && Boolean(formik.errors.password)}
                                helperText={formik.touched.password && formik.errors.formik}
                                InputProps={{
                                    endAdornment: (
                                        <InputAdornment position="end">
                                            <VisibilityOffSharp />
                                        </InputAdornment>
                                    ),
                                }}
                            />

                            <TextField
                                fullWidth
                                id="EmailConfirmation"
                                name="emailConfirmation"
                                label="Confirmation"
                                value={formik.values.emailConfirmation}
                                onChange={formik.handleChange}
                                error={formik.touched.password && Boolean(formik.errors.password)}
                                helperText={formik.touched.password && formik.errors.password}
                                InputProps={{
                                    endAdornment: (
                                        <InputAdornment position="end">
                                            <VisibilityOffSharp />
                                        </InputAdornment>
                                    ),
                                }}
                            />
                        </CardContent>
                        <CardActions>
                            <Button size="medium" variant='outlined' href="/">Cancel</Button>
                            <Button size="medium" variant='outlined' onClick={formik.handleSubmit}>Submit</Button>
                        </CardActions>
                    </Card>
                )
            }
        </Formik>
    );
}