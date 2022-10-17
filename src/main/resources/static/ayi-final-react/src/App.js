import './App.css';
import AppRoutes from './routes/AppRoutes';
import NavBar from './components/NavBar/NavBar';
import { store } from './store/store';
import { Provider } from 'react-redux';
import { ThemeProvider, createTheme } from '@mui/material/styles';

function App() {

  const darkTheme = createTheme({
    palette: {
      mode: 'dark',
    },
  });

  return (
    <>
      <Provider store = {store}>
        <ThemeProvider theme={darkTheme}>
          <AppRoutes />
        </ThemeProvider>
      </Provider>
    </>
  );
}

export default App;
