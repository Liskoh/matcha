import {Path} from "./path.enum.ts";
import {createBrowserRouter} from "react-router-dom";
import Login from "../pages/auth/Login.tsx";
import Register from "../pages/auth/Register.tsx";
import ConfirmEmail from "../pages/auth/ConfirmEmail.tsx";
import { createBrowserHistory, BrowserHistory } from "history";


export const REACT_HISTORY: BrowserHistory = createBrowserHistory();
export const router = createBrowserRouter([
    {
        path: Path.HOME,
        element: <h1>Home</h1>,
    },
    {
        path: Path.LOGIN,
        element: <Login/>
    },
    {
        path: Path.REGISTER,
        element: <Register/>,
    },
    {
        path: "/test",
        element: <Register/>
    },
    {
        path: Path.CONFIRMATION,
        element: <ConfirmEmail/>,
    }
]);

