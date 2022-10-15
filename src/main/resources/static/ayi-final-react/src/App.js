import './App.css';
import AppRoutes from './components/AppRoutes/AppRoutes';
import { store } from './store/store'
import { Provider } from 'react-redux'

function App() {
  return (
    <>
      <Provider store = {store}>
        <AppRoutes />
      </Provider>
    </>
  );
}

export default App;