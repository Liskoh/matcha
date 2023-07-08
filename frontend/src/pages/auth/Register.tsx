import CssBaseline from "@mui/material/CssBaseline";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import AuthBackgroundComponent from "../../components/auth/AuthBackgroundComponent.tsx";
import TextField from "@mui/material/TextField";
import * as React from "react";
import Button from "@mui/material/Button";
import Link from "@mui/material/Link";
import {RegisterDto} from "../../dtos/register-dto.interface.ts";
import {AXIOS_INSTANCE, getJwtToken, setJwtToken} from "../../utils/requests/axios-utils.ts";
import {RequestPath} from "../../utils/requests/request-path.enum.ts";

export const TITLE = "Register";

export default function Register() {
    const [errorMessage, setErrorMessage] = React.useState<string | null>(null);
    const [formData, setFormData] = React.useState({
        username: '',
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        bornDate: '',
    });

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setFormData({
            ...formData,
            [event.target.name]: event.target.value,
        });
    };
    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        if (!event.currentTarget.checkValidity()) {
            setErrorMessage('Please fill out all fields');
            return;
        }

        const dto: RegisterDto = {
            username: formData.username,
            firstName: formData.firstName,
            lastName: formData.lastName,
            email: formData.email,
            password: formData.password,
            bornDateString: formData.bornDate,
        }
        console.log("date " + formData.bornDate);
        console.log(dto);

        try {
            const response = await AXIOS_INSTANCE.post(RequestPath.REGISTER, dto);

            const token: string = response.data.token;

            if (token) {
                setJwtToken(token);
                //TODO: redirect to mail verification page
                console.log("current token= " + getJwtToken());
            }
        } catch (error: unknown) {
            if (error.response) {
                setErrorMessage(error.response.data.message);
            }
        }
    };

    return (
        <Grid container component="main" sx={{height: '100vh'}}>
            <CssBaseline/>
            <AuthBackgroundComponent
                title="Register"
                errorMessage={errorMessage}
            >
                <Box component="form" noValidate onSubmit={handleSubmit}>
                    <Grid container spacing={2}>
                        <Grid item xs={12}>
                            <TextField
                                name="username"
                                required
                                fullWidth
                                id="username"
                                label="User Name"
                                value={formData.username}
                                onChange={handleChange}
                            />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField
                                name="firstName"
                                required
                                fullWidth
                                id="firstName"
                                label="First Name"
                                value={formData.firstName}
                                onChange={handleChange}
                            />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField
                                required
                                fullWidth
                                id="lastName"
                                label="Last Name"
                                name="lastName"
                                value={formData.lastName}
                                onChange={handleChange}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                required
                                fullWidth
                                name="email"
                                label="Email"
                                type="email"
                                id="email"
                                value={formData.email}
                                onChange={handleChange}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                required
                                fullWidth
                                name="bornDate"
                                type="date"
                                id="bornDate"
                                value={formData.bornDate}
                                onChange={handleChange}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                required
                                fullWidth
                                name="password"
                                label="Password"
                                type="password"
                                id="password"
                                value={formData.password}
                                onChange={handleChange}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                            >
                                {TITLE}
                            </Button>
                        </Grid>
                        <Grid item xs>
                            <Link href="#" variant="body2">
                                Forgot password?
                            </Link>
                        </Grid>
                        <Grid item>
                            <Link href="#" variant="body2">
                                {"Have an account? Login"}
                            </Link>
                        </Grid>
                    </Grid>
                </Box>
            </AuthBackgroundComponent>
        </Grid>
    )
}