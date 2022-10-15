// This file will have all the thunks related to the employees (GET, PUT, etc)

import { setEmployees, startLoadingEmployees } from "./employeePagesSlice";
import { getAllEmployeePages, getEmployeeDetails } from "../../../api/employeeApi";
import { setEmployeeDetails } from "./employeeDetailsSlice";

export function findAllEmployeePages(page = 1) {
    return async function(dispatch) {
        dispatch(startLoadingEmployees());

        const data = await getAllEmployeePages(page);

        console.log(data);

        dispatch(setEmployees({
            employees: data,
            page: page + 1
        }));
    }
}

export function findEmployeeDetails(id) {
    return async function(dispatch) {
        const data = await getEmployeeDetails(id);

        console.log(data);

        dispatch(setEmployeeDetails({
            firstName: data.fistName,
            lastName: data.lastName,
            employeeDetails: data.employeeDetails
        }))
    }
}