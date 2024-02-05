import React from "react";
import {Link} from "react-router-dom";
import { Container, Form, Button } from "react-bootstrap";


function Login() {
    return (
        <div>
            <p>LOGIN PAGE</p>
            <Link to="/register">Click here to register!</Link>
        </div>
    );
}

export default Login;