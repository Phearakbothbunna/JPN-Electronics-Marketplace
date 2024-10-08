import logo from './logo.svg';
import './App.css';
import React from 'react';
import {Route, Routes} from "react-router-dom";
import Login from './Login';
import Register from "./Register";
import LogoPage from "./LogoPage";
import Home from "./Home"
import ProtectedRoute from "./ProtectedRoute";
import MyListing from "./MyListing";

function App() {
  return (
      <div className="App">
        <header className="App-header">
          <Routes>
              {/* Routing of the website */}
              <Route path="/" element={<LogoPage />} />
              <Route path = "/login" element = {<Login/>} />
              <Route path="/register" element={<Register />} />
              <Route path="/home" element={
                  <ProtectedRoute>
                      <Home />
                  </ProtectedRoute>
              } />
              <Route path = "/myListing" element = {
                  <ProtectedRoute>
                    <MyListing/>
                  </ProtectedRoute>
              } />
          </Routes>
        </header>

      </div>
  );
}

export default App;
