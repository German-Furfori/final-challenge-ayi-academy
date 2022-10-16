// This file will have all the thunks related to the employees (GET, PUT, etc)

import { setEmployees, startLoadingEmployees } from "./employeePagesSlice";
import { getAllEmployeePages, getEmployeeDetails } from "../../../api/employeeApi";
import { setEmployeeDetails } from "./employeeDetailsSlice";

export function findAllEmployeePages(page = 1) {
    return async function(dispatch) {
        dispatch(startLoadingEmployees());

        const data = await getAllEmployeePages(page);

        dispatch(setEmployees({
            employees: data,
            page: page + 1
        }));
    }
}

export function findEmployeeDetails(idEmployee) {
    return async function(dispatch) {
        const data = await getEmployeeDetails(idEmployee);

        console.log(data);

        dispatch(setEmployeeDetails({
            firstName: data.firstName,
            lastName: data.lastName,
            employeeDetails: data.employeeDetails,
            project: data.project
        }))
    }
}