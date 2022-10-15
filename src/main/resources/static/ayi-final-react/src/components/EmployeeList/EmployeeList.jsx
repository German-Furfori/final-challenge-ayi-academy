import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Pagination from '@mui/material/Pagination';
import Paper from '@mui/material/Paper';
import Button from '@mui/material/Button';
import Modal from '@mui/material/Modal';
import { Container, Typography } from '@mui/material';
import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { findAllEmployeePages } from '../../store/slices/employee';
import EmployeeDetails from '../EmployeeDetails/EmployeeDetails';

export default function EmployeeList() {
  const [open, setOpen] = useState(false);
  const { isLoading, employees = {} } = useSelector(state => state.employeePages); // I had to especify that employees is an object with = {}
  const { employeeDetails = {} } = useSelector(state => state.employeeDetails);
  const dispatch = useDispatch();

  const handleChangePage = (event, value) => {
    event.preventDefault();
    dispatch(findAllEmployeePages(value));
  }

  const handleOpen = () => {
    setOpen(true);
  }
  const handleClose = () => setOpen(false);

  useEffect(() => {
    dispatch(findAllEmployeePages());
  }, [dispatch]);
  
  return (
    <Container fixed sx={{ padding: 5 }}>

      { isLoading? (
        <>
          <Typography variant="h4" color='white' sx={{ paddingBottom: 2, paddingTop: 8 }}> Loading... </Typography>
        </>
      ) : (
        <>
          <Typography variant="h4" color='white' sx={{ paddingBottom: 2, paddingTop: 8 }}>List of employees</Typography>
            <TableContainer component={Paper}>
              <Table sx={{ minWidth: 650 }} aria-labelledby="simple table" className='setBackground'>
                <TableHead>
                  <TableRow>
                    <TableCell align='center'>ID Employee</TableCell>
                    <TableCell align="left">First Name</TableCell>
                    <TableCell align="left">LastName</TableCell>
                    <TableCell align="left">DNI</TableCell>
                    <TableCell align="left">Email</TableCell>
                    <TableCell align="left">Details</TableCell>
                    <TableCell align="left">Actions</TableCell>
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
                      <TableCell align="left">
                        <Button onClick={handleOpen}>Details</Button>
                        <Modal
                          open={open}
                          onClose={handleClose}
                          aria-labelledby="modal-modal-title"
                          aria-describedby="modal-modal-description"
                        >
                          <EmployeeDetails
                            firstName = {employee.firstName}
                            lastName = {employee.lastName}
                            employeeDetails = {employee.employeeDetails}
                          />
                        </Modal>
                      </TableCell>
                      <TableCell align="left">Actions</TableCell>
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