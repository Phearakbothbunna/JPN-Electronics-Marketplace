import React, {useState} from "react";
import {Link} from "react-router-dom";
import { Container, Form, Button } from "react-bootstrap";
import app_logo from "./app_logo.png";



function Register() {
    const[showPwd, setShowPwd] = useState(false)
    const togglePwd = () => {
        setShowPwd(!showPwd);
    }
    return (

        <Container className="mt-5">
            {/*App logo*/}

            <img src={app_logo} alt="App_logo" className='app_logo'/>

            <Form className="login_form" >

                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label style={{color: "antiquewhite"}}>Email address</Form.Label>
                    <Form.Control type="text" name="email" placeholder="Enter Email" />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label  style={{color: "antiquewhite"}}>Password</Form.Label>
                    <Form.Control
                        type={showPwd ? "text":"password"}
                        name="password"
                        placeholder="Enter Password" />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label  style={{color: "antiquewhite"}}>Confirm Password</Form.Label>
                    <Form.Control
                        type={showPwd ? "text":"password"}
                        name="confirm_password"
                        placeholder="Confirm Password" />
                </Form.Group>
                <Button variant="outline-secondary" onClick={togglePwd}>
                        {showPwd ? "Hide" : "Show Password"}
                </Button>

                <Link to="/login">
                    <div>
                        <Button className="btn_register" variant="outline-primary" type="submit">
                            Submit!
                        </Button>
                    </div>
                    <Button className="btn_back" variant="secondary" type="submit" style={{background: ''}}>
                        Return to Login page
                    </Button>
                </Link>
            </Form>
        </Container>


);
}

export default Register;