import React from 'react'
import { Container, Typography, Card, CardContent } from '@mui/material';

export default function Home() {
  return (
    <>
      <Container fixed sx={{ padding: 13 }}>
        <Card sx={{ minWidth: 275 }}>
          <CardContent>
            <Typography variant="h4" component="div">
              Welcome to the Employee Management webpage!
            </Typography>
            <Typography sx={{ fontSize: 25 }} color="text.secondary" gutterBottom>
              Employee Details
            </Typography>
            <Typography sx={{ mb: 1.5, fontSize: 19 }} color="text.secondary">
              
            </Typography>
            <Typography variant="body2">
              well meaning and kindly.
              <br />
              {'"a benevolent smile"'}
            </Typography>
          </CardContent>
        </Card>
      </Container>
    </>
  )
}
