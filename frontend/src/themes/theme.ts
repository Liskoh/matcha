import {createTheme} from "@mui/material/styles";
import {Theme} from "@emotion/react";

// const PREFERS_DARK_THEME: boolean = useMediaQuery('(prefers-color-scheme: dark)');

export const APP_THEME: Theme = createTheme({
       palette: {
           // mode: PREFERS_DARK_THEME ? 'dark' : 'light',
           // mode: 'light',
           mode: 'dark',
       }
})