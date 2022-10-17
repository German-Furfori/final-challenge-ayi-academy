import React from 'react'
import { Container, Typography, Card, CardContent } from '@mui/material';

export default function Home() {
  return (
    <>
      <Container fixed sx={{ paddingTop: 13, paddingBottom: 4 }}>
        <Card sx={{ minWidth: 200 }}>
          <CardContent>
            <Typography variant="h4" component="div" align='center'>
              Welcome!
            </Typography>
            <Typography sx={{ mb: 1.5, fontSize: 17, paddingTop: 3 }} color="text.secondary">
              This is a web page to manage the salaries and project assignment of the enterprise employees
            </Typography>
          </CardContent>
        </Card>
      </Container>
    </>
  )
}
