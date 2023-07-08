export enum RequestPath {
    API = '/api',
    AUTH = '/auth',
    LOGIN = AUTH + '/login',
    REGISTER = AUTH + '/register',
    IS_AUTHENTICATED = AUTH + '/is-authenticated',
    CONFIRMATION = AUTH + '/confirmation',
}