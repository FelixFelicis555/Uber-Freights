import React, { useState } from "react";
import { 
    BrowserRouter as Router, 
    Routes, 
    Route 
} from "react-router-dom";
import Header from "./components/Header/Header";
import LoginForm from "./components/LoginForm/LoginForm"
import RegistrationForm from "./components/RegistrationForm/RegistrationForm";
import Home from "./components/Home/Home";
import PrivateRoute from "./utils/privateroute";
import "bootstrap/dist/css/bootstrap.min.css";

import ViewPackage from "./components/Home/Packages";
import './App.css';

function App() {
    const [title, updateTitle] = useState(null);
    const [errorMessage, updateErrorMessage] = useState(null);
    return (
        <div>
          <Router>
            <div className="App">
              <Header title={title} />
              <div className="container d-flex align-items-center flex-column">
                <Routes>
                  <Route path="/" exact={true}>
                    <RegistrationForm
                      showError={updateErrorMessage}
                      updateTitle={updateTitle}
                    />
                  </Route>
                  <Route path="/register">
                    <RegistrationForm
                      showError={updateErrorMessage}
                      updateTitle={updateTitle}
                    />
                  </Route>
                  <Route path="/login">
                    <LoginForm
                      showError={updateErrorMessage}
                      updateTitle={updateTitle}
                    />
                  </Route>
                  <PrivateRoute path="/home">
                    <Home />
                  </PrivateRoute>
                  <PrivateRoute path="/packages">
                    <ViewPackage />
                  </PrivateRoute>
                </Routes>
              </div>
            </div>
          </Router>
        </div>
      );
}

export default App;