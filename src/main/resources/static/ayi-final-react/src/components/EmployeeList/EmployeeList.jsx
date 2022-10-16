import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Pagination from '@mui/material/Pagination';
import Paper from '@mui/material/Paper';
import Button from '@mui/material/Button';
import OutlinedInput from '@mui/material/OutlinedInput';
import { Link } from 'react-router-dom';
import { Container, Typography } from '@mui/material';
import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { findAllEmployeePages } from '../../store/slices/employee';
import { incrementSalaries } from '../../api/employeeApi';

export default function EmployeeList() {
  const { isLoading, employees = {} } = useSelector(state => state.employeePages); // I had to especify that employees is an object with = {}
  const dispatch = useDispatch();

  const handleChangePage = (event, value) => {
    event.preventDefault();
    dispatch(findAllEmployeePages(value));
  }

  useEffect(() => {
    dispatch(findAllEmployeePages());
  }, [dispatch]);

  const handleIncrementSalariesButton = async () => {
    let percentage = document.getElementById('percentageInput').value;
    if(percentage && percentage <= 100) {
      let response = await incrementSalaries(percentage);
      alert(response.Message);
    }
  }

  const handleUpdateSalaryButton = async () => {

  }
  
  return (
    <Container fixed sx={{ padding: 5 }}>

      { isLoading? (
        <>
          <Typography variant="h4" color='white' sx={{ paddingBottom: 2, paddingTop: 8 }}> Loading... </Typography>
        </>
      ) : (
        <>
          <Typography variant="h4" color='white' sx={{ paddingBottom: 2, paddingTop: 8 }}>List of employees</Typography>
          <Button 
            sx={{ marginBottom: 3, marginRight: 3, color: '#fff' }}
            onClick={handleIncrementSalariesButton}
          >
            Increment Salaries
          </Button>
          <OutlinedInput 
            placeholder='Percentage'
            type='number'
            id='percentageInput'
          />
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
                  {employees.employeeFullDataResponseDTOList?.map((employee) => ( // The ? was because the DOM start to render before getting the array
                    <TableRow key={employee.idEmployee}>
                      <TableCell align="center">{employee.idEmployee}</TableCell>
                      <TableCell align="left">{employee.firstName}</TableCell>
                      <TableCell align="left">{employee.lastName}</TableCell>
                      <TableCell align="left">{employee.dni}</TableCell>
                      <TableCell align="left">{employee.email}</TableCell>
                      <TableCell align="center">
                        <Button sx={{ color: '#fff', alignContent: 'center' }}>
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
        </>
      ) }

        <Pagination
         sx={{
           padding: 2
         }}
         count={employees.totalPages}
         defaultPage={1}
         onChange={handleChangePage}
         className="setBackground"
         color="primary"
         variant="outlined"
         shape="rounded"
        />

    </Container>
  );
}