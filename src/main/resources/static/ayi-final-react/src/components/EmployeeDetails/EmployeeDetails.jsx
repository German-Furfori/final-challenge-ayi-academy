import React from 'react';
import { Container, Typography, Button, Card, CardActions, CardContent } from '@mui/material';
import { useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect } from 'react';
import { findEmployeeDetails } from '../../store/slices/employee';

export default function EmployeeDetails() {
  const dispatch = useDispatch();
  const { firstName, lastName, employeeDetails = {}, project = {} } = useSelector(state => state.employeeDetails);
  const { idEmployee } = useParams();

  useEffect(() => {
    dispatch(findEmployeeDetails(idEmployee));
  }, [dispatch, idEmployee]);

  return (
    <>
      <Container fixed sx={{ padding: 13 }}>
        <Card sx={{ minWidth: 275 }}>
          <CardContent>
            <Typography sx={{ fontSize: 25 }} color="text.secondary" gutterBottom>
              Employee Details
            </Typography>
            <Typography variant="h3" component="div">
              {firstName} {lastName}
            </Typography>
            <Typography sx={{ mb: 1.5, fontSize: 19 }} color="text.secondary">
              {employeeDetails.role} {employeeDetails.seniority}
            </Typography>
            <Typography variant="h4" component="div">
              Project Details
            </Typography>
            <Typography variant="body2">
              <ul>
                <li>Customer: </li>
                <img src={`https://ayi.group/wp-content/uploads/2021/10/logo-farmalink.webp`} />
                <li>Technologies: {project.technologies} </li>  
                <li>Limit date: {project.limitDate} </li>
              </ul>  
            </Typography>
            
          </CardContent>
          <CardActions>
            <Button sx={{ color: '#fff' }}>
          	  Assign other project
            </Button>
            <Button sx={{ color: '#fff' }}>
          	  Update salary
            </Button>
          </CardActions>
        </Card>
      </Container>
    </>
  )
}

// ../img/${project.customer}.webp

// https://ayi.group/wp-content/uploads/2021/10/logo-arcor.webp
// https://ayi.group/wp-content/uploads/2021/10/logo-link.webp
// https://ayi.group/wp-content/uploads/2021/10/logo-zurich.webp
// https://ayi.group/wp-content/uploads/2021/10/logo-farmalink.webp