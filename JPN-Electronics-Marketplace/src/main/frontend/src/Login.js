import React from "react";
import {Link} from "react-router-dom";
import { Container, Form, Button } from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css'
import './Login.css'


function Login() {
    return (

        <Container className="mt-5">
            <Form className="login_form" >

                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label style={{color: "white"}}>Email address</Form.Label>
                    <Form.Control type="text" name="email" placeholder="Enter Email" />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label  style={{color: "white"}}>Password</Form.Label>
                    <Form.Control type="password" name="password" placeholder="Enter Password" />
                </Form.Group>

                <Button className="btn_login" variant="primary" type="submit">
                    Login
                </Button>
            </Form>

            <div className="d-flex justify-content-center mt-3">
                <Link to="/register" className="btn btn-secondary mr-2">Register</Link>
            </div>
        </Container>

    );
}

export default Login;