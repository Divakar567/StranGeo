import React from 'react';
import { useKeycloak } from '@react-keycloak/web';
import { Card, CardContent, CardActions, TextField, InputAdornment, Typography, Button } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import { AccountCircleSharp, VisibilityOffSharp } from '@material-ui/icons';
import { Formik } from 'formik';
import * as Yup from 'yup';
import axios from 'axios';
import { SIGNIN_URL } from '../../app/constants';

const useStyles = makeStyles((theme) => ({
	root: {
		flexGrow: 1,
	},
	card: {
		padding: theme.spacing(2),
		margin: 'auto',
		maxWidth: 500,
	},
}));

const validationSchema = Yup.object({
	username: Yup
		.string('Enter your email/username')
		.required('Email/Username is required')
		.email('Enter a valid email/username'),
	password: Yup
		.string('Enter your password')
		.required('Password is required'),
	//   .min(8, 'Password should be of minimum 8 characters length'),
});

export default function SingIn() {
	const { keycloak, initialized } = useKeycloak();
	if (initialized) {
		if (!keycloak.authenticated) {
			keycloak.init({ onLoad: "login-required" });
		}
	}

	const classes = useStyles();

	return (
		<Formik className={classes.root}

			initialValues={{
				username: "",
				password: "",
				rememberMe: false,
			}}

			validationSchema={validationSchema}

			onSubmit={(data, actions) => {
				axios({
					url: SIGNIN_URL,
					method: 'POST',
					headers: {
						'Accept': 'application/json',
						'Content-Type': 'application/json;charset=UTF-8'
					},
					data: data,
				}).then(response => {
					console.log(response.status);
					actions.setSubmitting(false);
				})
			}}
		>
			{
				formik => (
					<Card className={classes.card} component="form" variant="outlined">
						<CardContent>
							<Typography variant="h5" component="h2" gutterBottom>
								SignIn
            				</Typography>

							<TextField
								fullWidth
								id="username"
								name="username"
								label="Email/Username"
								value={formik.values.username}
								onChange={formik.handleChange}
								error={formik.touched.username && Boolean(formik.errors.username)}
								helperText={formik.touched.username && formik.errors.username}
								InputProps={{
									endAdornment: (
										<InputAdornment position="end">
											<AccountCircleSharp />
										</InputAdornment>
									),
								}}
							/>
							<TextField
								fullWidth
								id="password"
								name="password"
								label="Password"
								type="password"
								value={formik.values.password}
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
							<Button size="medium" variant="contained" href="/">Cancel</Button>
							<Button size="medium" variant="contained" onClick={formik.handleSubmit}>Submit</Button>
						</CardActions>
					</Card>
				)
			}
		</Formik>
	);
}