import React from 'react'; import {createRoot} from 'react-dom/client'; import './styles.css'; import './auth.css'; import './theme.css'; import './actions.css'; import App from './App';
createRoot(document.getElementById('root')!).render(<React.StrictMode><App/></React.StrictMode>);
