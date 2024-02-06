import React from "react";
import {Link} from "react-router-dom";
import { Container, Form, Button } from "react-bootstrap";


function Register() {
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

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label  style={{color: "white"}}>Confirm Password</Form.Label>
                    <Form.Control type="password" name="password" placeholder="Confirm Password" />
                </Form.Group>

                <Link to="/">
                    <Button className="btn_register" variant="outline-primary" type="submit">
                        Submit!
                    </Button>
                </Link>
            </Form>
        </Container>


);
}

export default Register;