import { startLoadingProjects, setProjects } from "./projectListSlice";
import { getAllProjects } from "../../../api/projectApi";

export function findAllProjects() {
    return async function (dispatch) {
        dispatch(startLoadingProjects());

        const data = await getAllProjects();

        dispatch(setProjects({
            projectList: data
        }))
    }
}