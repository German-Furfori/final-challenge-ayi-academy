// This file will have all the thunks related to the employees (GET, PUT, etc)

import { setEmployees, startLoadingEmployees } from "./employeePagesSlice";
import { getAllEmployeePages, getEmployeeById } from "../../../api/employeeApi";
import { startLoadingEmployee, setEmployeeDetails } from "./employeeDetailsSlice";

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
        dispatch(startLoadingEmployee());

        const data = await getEmployeeById(idEmployee);

        dispatch(setEmployeeDetails({
            firstName: data.firstName,
            lastName: data.lastName,
            employeeDetails: data.employeeDetails,
            project: data.project
        }))
    }
}