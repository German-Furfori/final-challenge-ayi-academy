// This file will have all the thunks related to the employees (GET, PUT, etc)

import { setEmployees, startLoadingEmployees } from "./employeeSlice";
import { getAllEmployeePages } from "../../../api/employeeApi";

export function findAllEmployeePages(page = 1) {
    return async function(dispatch, getState) {
        dispatch(startLoadingEmployees());

        const data = await getAllEmployeePages(page);

        console.log(data);

        dispatch(setEmployees({
            employees: data.employeeFullDataResponseDTOList,
            page: page + 1
        }));
    }
}