import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import PersonAddAltOutlinedIcon from '@mui/icons-material/PersonAddAltOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { createUser, setMessage } from '../../store/slices/user/userSlice';


export default function Register() {

  const dispatch = useDispatch();
  const { users, message } = useSelector(state => state.user);
  const navigate = useNavigate();

  const isExistingUser = (username) => {
    return users.find(user => user.username === username);
  }

  const verifyData = (data) => {
    const username = data.get('username').trim();
    const password = data.get('password').trim();
    const confirmPassword = data.get('confirmPassword').trim();

    if (!username) {
      dispatch(setMessage({ message: 'Complete the username' }));
      return false;
    } 

    if (!password) {
      dispatch(setMessage({ message: 'Complete the password' }));
      return false;
    } 

    if (!confirmPassword) {
      dispatch(setMessage({ message: 'Verify your password' }));
      return false;
    } 

     if (password !== confirmPassword) {
      dispatch(setMessage({ message: 'Passwords do not match'}));
      return false;
    } 
    
    if (isExistingUser(username)) {
      dispatch(setMessage({ message: 'User already exists'}));
      return false;
    } 

      return true;
    
  }

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);

    if (verifyData(data)) {
      const user = {
        username: data.get('username'),
        password: data.get('password')
      }
      dispatch(createUser(user));
      navigate('/');
      dispatch(setMessage({ message: ''}));
    } 

  };

  return (
    <div className='container'>
      <Container component="main" maxWidth="xs" sx={{backgroundColor: '#3F3E3E', borderRadius: '10px'}}>
        <CssBaseline />
        <Box
          sx={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            marginTop: 20
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: 'secondary.light', marginTop: 3, marginBottom: 2 }}>
            <PersonAddAltOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Register
          </Typography>
          <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="username"
                  label="User"
                  name="username"
                  autoComplete="username"
                  autoFocus
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="password"
                  label="Password"
                  type="password"
                  id="password"
                  autoComplete="new-password"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="confirmPassword"
                  label="Repeat password"
                  type="password"
                  id="confirmPassword"
                  autoComplete="repeat-password"
                />
              </Grid>
            </Grid>
            <Typography component="h6" variant="h6" sx={{ paddingTop: 2 }}>
              {message && message}
            </Typography>
            <Button
              type="submit"
              fullWidth
              sx={{ mt: 3, mb: 2, marginTop: 2, color: '#fff', fontSize: 20 }}
            >
              Sign In
            </Button>
          </Box>
        </Box>
      </Container>
    </div>
  );
}