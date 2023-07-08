import CssBaseline from "@mui/material/CssBaseline";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import AuthBackgroundComponent from "../../components/auth/AuthBackgroundComponent.tsx";
import Paper from "@mui/material/Paper";
import * as React from "react";
import {AXIOS_INSTANCE} from "../../utils/requests/axios-utils.ts";
import {RequestPath} from "../../utils/requests/request-path.enum.ts";
import {AxiosResponse, HttpStatusCode} from "axios";
import {useEffect} from "react";

export default function Login() {
    const [confirmed, setConfirmed] = React.useState<boolean | null>(null);
    const isKeyValid = async (): Promise<boolean> => {
        try {
            const url: URL = new URL(window.location.href);
            const params: URLSearchParams = new URLSearchParams(url.search.slice(1));
            const key: string | null = params.get("key");
            const response = await AXIOS_INSTANCE.get(RequestPath.CONFIRMATION, {
                params: {
                    key: key,
                }
            });
            return response.status === HttpStatusCode.Ok;
        } catch (error: unknown) {
            return false;
        }
    };

    // React.useEffect(async () => {
    //     setConfirmed(await isKeyValid());
    // }, []);
    useEffect(() => {
        console.log("useEffect");
    }),[];

    return (
        <Grid container component="main" sx={{ height: "100vh" }}>
            <CssBaseline />
            <AuthBackgroundComponent />
            <Grid
                item
                xs={12}
                sm={8}
                md={5}
                component={Paper}
                elevation={6}
                square
            >
                <Box
                    sx={{
                        my: 8,
                        mx: 4,
                        display: "flex",
                        flexDirection: "column",
                        alignItems: "center",
                    }}
                >
                    <h1>Log in</h1>
                </Box>
            </Grid>
        </Grid>
    );
}
