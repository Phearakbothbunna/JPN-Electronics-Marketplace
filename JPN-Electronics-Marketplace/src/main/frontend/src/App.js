import logo from './logo.svg';
import './App.css';
import React from 'react';
import {Route, Routes} from "react-router-dom";
import Login from './Login';
import Register from "./Register";

function App() {
  return (
      <div className="App">
        <header className="App-header">
          <Routes>
            {/* Render Login within a Route */}
            <Route path="/" element={<Login />} />
            <Route path="/register" element={<Register />} />

          </Routes>
        </header>

      </div>
  );
}

export default App;
