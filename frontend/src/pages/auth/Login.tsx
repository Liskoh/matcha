import CssBaseline from "@mui/material/CssBaseline";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import AuthBackgroundComponent from "../../components/auth/AuthBackgroundComponent.tsx";
import Paper from "@mui/material/Paper";
import TextField from "@mui/material/TextField";
import * as React from "react";
import Button from "@mui/material/Button";
import Link from "@mui/material/Link";
import {RegisterDto} from "../../dtos/register-dto.interface.ts";
import {AXIOS_INSTANCE, getJwtToken, setJwtToken} from "../../utils/requests/axios-utils.ts";
import {RequestPath} from "../../utils/requests/request-path.enum.ts";
import {LoginDto} from "../../dtos/login-dto.interface.ts";
import {REACT_HISTORY} from "../../routes/routes.tsx";
import {Path} from "../../routes/path.enum.ts";

export default function Login() {
    const [errorMessage, setErrorMessage] = React.useState<string | null>(null);

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        if (!event.currentTarget.checkValidity()) {
            setErrorMessage('Please fill out all fields');
            return;
        }

        const data: FormData = new FormData(event.currentTarget);

        const dto: LoginDto = {
            username: data.get('username') as string,
            password: data.get('password') as string,
        }

        try {
            const response = await AXIOS_INSTANCE.post(RequestPath.LOGIN, dto);

            const token: string = response.data.token;

            if (token) {
                setJwtToken(token);
                //TODO: redirect to mail verification page
                console.log("current token= " + getJwtToken());

                REACT_HISTORY.push(Path.CONFIRMATION);
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
                title="Log in"
                errorMessage="Invalid username or password"
            >
                    <Box component="form" noValidate onSubmit={handleSubmit}>
                        <Grid container spacing={2}>
                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    name="username"
                                    id="username"
                                    label="User Name"
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    name="password"
                                    id="password"
                                    label="Password"
                                    type="password"
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <Button
                                    type="submit"
                                    fullWidth
                                    variant="contained"
                                >
                                    Log in
                                </Button>
                            </Grid>
                            <Grid item xs>
                                <Link href="#" variant="body2">
                                    Forgot password?
                                </Link>
                            </Grid>
                            <Grid item>
                                <Link href="#" variant="body2">
                                    {"Don't have an account? Sign Up"}
                                </Link>
                            </Grid>
                        </Grid>
                    </Box>
            </AuthBackgroundComponent>
        </Grid>
    )
}