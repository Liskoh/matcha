import CssBaseline from "@mui/material/CssBaseline";
import Grid from "@mui/material/Grid";
import * as React from "react";
import Paper from "@mui/material/Paper";
import Box from "@mui/material/Box";
import { Typography } from "@mui/material";

export default function AuthBackgroundComponent({ title, errorMessage, children }) {
    return (
        <Grid container component="main" sx={{ height: "100vh" }}>
            <CssBaseline />
            <Grid
                item
                xs={false}
                sm={4}
                md={7}
                sx={{
                    backgroundImage: "url(http://127.0.0.1:8000/background3.jpg)",
                    backgroundRepeat: "no-repeat",
                    backgroundSize: "cover",
                    backgroundPosition: "center",
                }}
            />
            <Grid item xs={12} sm={8} md={5}>
                <Paper square sx={{ height: "100%" }}>
                    <Box
                        sx={{
                            mx: 4,
                            display: "flex",
                            flexDirection: "column",
                            alignItems: "center",
                            justifyContent: "center",
                            height: "100%",
                        }}
                    >
                        <Box
                            sx={{
                                mx: 4,
                                display: "flex",
                                flexDirection: "column",
                                alignItems: "center",
                                my: "1rem",
                            }}
                        >
                            <Typography variant="h4" gutterBottom>{title}</Typography>
                            {errorMessage && (
                                <Typography color="error" gutterBottom>{errorMessage}</Typography>
                            )}
                            {children}
                        </Box>
                    </Box>
                </Paper>
            </Grid>
        </Grid>
    );
}
