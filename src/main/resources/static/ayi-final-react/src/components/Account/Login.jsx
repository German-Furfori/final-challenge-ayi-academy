import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import PersonOutlineOutlinedIcon from '@mui/icons-material/PersonOutlineOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { loginUser, setMessage } from '../../store/slices/user/userSlice';

export default function SignIn() {
    const dispatch = useDispatch();
    const { users, message } = useSelector(state => state.user);
    const navigate = useNavigate();

    const getUser = (username, password) => {
        return users.find(user => user.username === username && user.password === password);
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        const user = getUser(data.get('username'), data.get('password'));

        if (user) {
            dispatch(loginUser({
                userLogged: user.username
            }));
            window.localStorage.setItem('userLogged', user.username);
            navigate('/home');
        } else {
            dispatch(setMessage({
                message: 'Username or password are incorrect'
            }));
        }

    };

    return (
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
                    <PersonOutlineOutlinedIcon />
                </Avatar>
                <Typography component="h1" variant="h5">
                    Log In
                </Typography>
                <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
                    <TextField
                        margin="normal"
                        required
                        fullWidth
                        id="username"
                        label="Username"
                        name="username"
                        autoComplete="username"
                        autoFocus
                    />
                    <TextField
                        margin="normal"
                        required
                        fullWidth
                        name="password"
                        label="Password"
                        type="password"
                        id="password"
                        autoComplete="current-password"
                    />
                    <Typography component="h6" variant="h6">
                        { message && message }
                    </Typography>
                    <Button
                        type="submit"
                        fullWidth
                        sx={{ mt: 3, mb: 2, marginTop: 2, color: '#fff', fontSize: 20 }}
                    >
                        Go
                    </Button>
                </Box>
            </Box>
        </Container>
    );
}