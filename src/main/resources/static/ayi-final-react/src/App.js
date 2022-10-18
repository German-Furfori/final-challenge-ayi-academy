import './App.css';
import './index.css';
import AppRoutes from './routes/AppRoutes';
import { store } from './store/store';
import { Provider } from 'react-redux';
import { ThemeProvider, createTheme } from '@mui/material/styles';

function App() {

  const customTheme = createTheme({
    palette: {
      mode: 'dark',
    },
    typography: {
      fontFamily: 'Lucida Console'
    }
  });

  return (
    <>
      <Provider store = {store}>
        <ThemeProvider theme={customTheme}>
          <AppRoutes />
        </ThemeProvider>
      </Provider>
    </>
  );
}

export default App;
