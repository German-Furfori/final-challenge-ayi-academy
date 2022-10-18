import React from 'react';
import { Container, Typography, Card, CardActions, CardContent, Box, CircularProgress } from '@mui/material';
import { useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect } from 'react';
import { findEmployeeDetails } from '../../store/slices/employee';
import ModalProject from '../Modal/ModalProject';
import ModalSalary from '../Modal/ModalSalary';
import Link from '../../img/Link.webp';
import Arcor from '../../img/Arcor.webp';
import Zurich from '../../img/Zurich.webp';
import Cordoba from '../../img/Cordoba.webp';

export default function EmployeeDetails() {
  const dispatch = useDispatch();
  const { isLoading, firstName, lastName, employeeDetails = {}, project = {} } = useSelector(state => state.employeeDetails);
  const { idEmployee } = useParams();

  useEffect( () => {
    dispatch(findEmployeeDetails(idEmployee));
  }, [dispatch, idEmployee]);

  return (
    <>
        { isLoading? (
          <Container fixed>
            <Box className="container-loading">
              <CircularProgress sx={{
                  color: 'white',
              }} />
            </Box>
          </Container>
          ) : (
          <Container fixed sx={{ paddingTop: 13, paddingBottom: 4, marginRight: 8 }}>
            <Card sx={{ maxWidth: 810 }} >
              <CardContent>
                <Typography variant="h3" component="div" align='center' sx={{ paddingBottom: 5, paddingTop: 2 }} >
                  {firstName} {lastName}
                </Typography>
                <Typography sx={{ mb: 1.5, fontSize: 19 }} color="text.secondary">
                  {employeeDetails.role} {employeeDetails.seniority}
                </Typography>
                <Typography sx={{ mb: 1.5, fontSize: 19 }} color="text.secondary">
                  Salary: ${employeeDetails.salary}
                </Typography>
                <Typography variant="h4" component="div">
                  Project Details
                </Typography>
                <Typography variant="body2">
                  <ul>
                    <li>
                      <Typography sx={{ mb: 1.5, fontSize: 16 }} color="text.secondary">
                        Customer:
                      </Typography> 
                    </li>
                    { project.customer === 'Link' ? 
                      <img src={Link} alt=''/> : project.customer === 'Arcor' ? 
                      <img src={Arcor} alt=''/> : project.customer === 'Municipalidad de Córdoba' ? 
                      <img src={Cordoba} alt=''/> : <img src={Zurich} alt=''/>
                    }
                    <li>
                      <Typography sx={{ mb: 1.5, fontSize: 16 }} color="text.secondary">
                        Technologies: {project.technologies} 
                      </Typography> 
                    </li>
                    <li>
                      <Typography sx={{ mb: 1.5, fontSize: 16 }} color="text.secondary">
                        Limit date: {project.limitDate}
                      </Typography>
                    </li>
                  </ul>  
                </Typography>
              </CardContent>
              <CardActions>
                <ModalProject idEmployee = {idEmployee} />
                <ModalSalary idEmployee = {idEmployee} />
              </CardActions>
            </Card>
          </Container>
        ) }
    </>
  )
}

// ../img/${project.customer}.webp

// https://ayi.group/wp-content/uploads/2021/10/logo-arcor.webp
// https://ayi.group/wp-content/uploads/2021/10/logo-link.webp
// https://ayi.group/wp-content/uploads/2021/10/logo-zurich.webp
// https://ayi.group/wp-content/uploads/2021/10/logo-farmalink.webp
// https://ayi.group/wp-content/uploads/2021/10/logo-.webp