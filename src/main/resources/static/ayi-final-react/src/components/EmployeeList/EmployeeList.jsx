import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import TablePagination from '@mui/material/TablePagination';
import Paper from '@mui/material/Paper';
import { Container, Typography } from '@mui/material';
import { useEffect, useState } from 'react';
import './styles.css';
import { useDispatch, useSelector } from 'react-redux';
import { findAllEmployeePages } from '../../store/slices/employee';

export default function EmployeeList() {
  // const [employees, setEmployees] = useState([]);
  // const [page, setPage] = useState(1);
  const [rowsPerPage, setRowsPerPage] = useState(10);

  const dispatch = useDispatch();
  const { isLoading, page, employees = [] } = useSelector(state => state.employee); // I had to especify that employees is an array with = []

  // const handleChangePage = (event, newPage) => {
  //   setPage(newPage);
  // };

  // const handleChangeRowsPerPage = (event) => {
  //   setRowsPerPage(parseInt(event.target.value, 10));
  //   setPage(1);
  // };

  // useEffect(() => {
  //   async function fecthData() {
  //     const result = await getAllEmployeePages(page);
  //     console.log(result);
  //     setEmployees(result.employeeFullDataResponseDTOList);
  //   }

  //   fecthData();
  // }, [page]);

  useEffect(() => {
    dispatch(findAllEmployeePages());
  }, [])
  

  return (
    <Container fixed sx={{ padding: 5 }}>
      <Typography variant="h4" sx={{ paddingBottom: 2 }}>List of employees</Typography>
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }} aria-labelledby="simple table" className='darkBackground'>
          <TableHead>
            <TableRow>
              <TableCell>ID Employee</TableCell>
              <TableCell align="left">First Name</TableCell>
              <TableCell align="left">LastName</TableCell>
              <TableCell align="left">DNI</TableCell>
              <TableCell align="left">Email</TableCell>
              <TableCell align="left">Details</TableCell>
              <TableCell align="left">Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {employees.map((employee) => (
              <TableRow key={employee.idEmployee}>
                <TableCell align="left">{employee.idEmployee}</TableCell>
                <TableCell align="left">{employee.firstName}</TableCell>
                <TableCell align="left">{employee.lastName}</TableCell>
                <TableCell align="left">{employee.dni}</TableCell>
                <TableCell align="left">{employee.email}</TableCell>
                <TableCell align="left">Details</TableCell>
                <TableCell align="left">Actions</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
        {/* <TablePagination
          component="div"
          count={100}
          page={page}
          onPageChange={handleChangePage}
          rowsPerPage={rowsPerPage}
          onRowsPerPageChange={handleChangeRowsPerPage}
          className='darkBackground'
        /> */}
      </TableContainer>
    </Container>
  );
}