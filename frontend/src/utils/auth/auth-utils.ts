import {AXIOS_INSTANCE} from "../requests/axios-utils.ts";
import {RequestPath} from "../requests/request-path.enum.ts";

export async function isAuthentified() : Promise<boolean> {
    try {
        await AXIOS_INSTANCE.get(RequestPath.IS_AUTHENTICATED);
    } catch (error) {
        return false;
    }
    return true;
}