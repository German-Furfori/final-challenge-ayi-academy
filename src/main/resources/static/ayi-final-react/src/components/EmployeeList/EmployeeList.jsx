import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Pagination from '@mui/material/Pagination';
import Paper from '@mui/material/Paper';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';
import CircularProgress from "@mui/material/CircularProgress";
import { Link } from 'react-router-dom';
import { Container, Typography } from '@mui/material';
import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { findAllEmployeePages } from '../../store/slices/employee';
import ModalSalaries from '../Modal/ModalSalaries';
import './styles.css';

export default function EmployeeList() {
  const { isLoading, page, employees = {} } = useSelector(state => state.employeePages); // I had to especify that employees is an object with = {}
  const dispatch = useDispatch();

  const handleChangePage = (event, value) => {
    event.preventDefault();
    dispatch(findAllEmployeePages(value));
  }

  useEffect(() => {
    dispatch(findAllEmployeePages());
  }, [dispatch]);
  
  return (
    <Container fixed sx={{ padding: 5 }}>

      { isLoading? (
        <Container fixed>
          <Box className="container-loading">
            <CircularProgress sx={{
                color: 'white',
            }} />
          </Box>
        </Container>
      ) : (
        <>
          <Typography align='center' variant="h4" color='white' sx={{ paddingTop: 6 }}> List of Employees </Typography>
          <ModalSalaries/>
            <TableContainer component={Paper}>
              <Table sx={{ minWidth: 650 }} aria-labelledby="simple table" className='setBackground'>
                <TableHead>
                  <TableRow>
                    <TableCell align='center'>ID Employee</TableCell>
                    <TableCell align="left">First Name</TableCell>
                    <TableCell align="left">LastName</TableCell>
                    <TableCell align="left">DNI</TableCell>
                    <TableCell align="left">Email</TableCell>
                    <TableCell align="center">Details</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {employees.employeeOnlyResponseDTOList?.map((employee) => ( 
                    <TableRow key={employee.idEmployee}>
                      <TableCell align="center">{employee.idEmployee}</TableCell>
                      <TableCell align="left">{employee.firstName}</TableCell>
                      <TableCell align="left">{employee.lastName}</TableCell>
                      <TableCell align="left">{employee.dni}</TableCell>
                      <TableCell align="left">{employee.email}</TableCell>
                      <TableCell align="center">
                        <Button sx={{ color: '#fff', alignContent: 'center', fontSize: 15 }}>
                          <Link style={{ textDecoration: 'none', color: 'white' }} to={`../employee/${employee.idEmployee}`}>
                            Details
                          </Link>
                        </Button>
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          <Pagination
            sx={{
              padding: 2
            }}
            count={employees.totalPages}
            defaultPage={page - 1}
            onChange={handleChangePage}
            shape="rounded"
          />
        </>
      ) }
    </Container>
  );
}