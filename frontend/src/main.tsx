import React from 'react'
import ReactDOM from 'react-dom/client'
import {RouterProvider} from "react-router-dom";
import {router} from "./routes/routes.tsx";
import {APP_THEME} from "./themes/theme.ts";
import {ThemeProvider} from "@mui/material/styles";

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
    <React.StrictMode>
        {/*<App />*/}
        <ThemeProvider theme={APP_THEME}>
            <RouterProvider router={router}/>
        </ThemeProvider>
    </React.StrictMode>,
)
