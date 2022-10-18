import React from 'react'
import { Container, Typography, Card, CardContent, Grid } from '@mui/material';
import {
  SiReact,
  SiRedux,
  SiReactrouter,
  SiMaterialui,
  SiHtml5,
  SiCss3,
  SiJavascript,
  SiMysql,
  SiJava,
  SiSpringboot,
  SiSpring
} from "react-icons/si";

export default function Home() {
  const technologies = [
    { name: 'React', icon: <SiReact/> },
    { name: 'Redux', icon: <SiRedux/> },
    { name: 'React Router', icon: <SiReactrouter/> },
    { name: 'Material UI', icon: <SiMaterialui/> },
    { name: 'HTML5', icon: <SiHtml5/> },
    { name: 'CSS3', icon: <SiCss3/> },
    { name: 'JavaScript', icon: <SiJavascript/> },
    { name: 'MySQL', icon: <SiMysql/> },
    { name: 'Java', icon: <SiJava/> },
    { name: 'Spring', icon: <SiSpring/> },
    { name: 'SpringBoot', icon: <SiSpringboot/> }
  ]

  console.log(technologies);
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
      <Container fixed sx={{ paddingTop: 5, paddingBottom: 4 }}>
        <Card sx={{ minWidth: 200 }}>
          <CardContent>
            <Typography sx={{ paddingBottom: 4 }} variant="h4" component="div" align='center'>
              Technologies used
            </Typography>
            <Grid container spacing={{ xs: 1, md: 2 }} columns={{ xs: 4, sm: 8, md: 12 }}>
              { technologies.map( (technology) => (
                <Grid sx={{paddingBottom: 2}} item xs={12} sm={4} md={3}>
                  <Typography variant="h1" align='center'>
                    {technology.icon} <br/>
                    <Typography variant="h5" align='center'>{technology.name}</Typography>
                  </Typography>
                </Grid>
              ))}
            </Grid>
          </CardContent>
        </Card>
      </Container>
    </>
  )
}
