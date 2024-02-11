import React, {useState} from "react";
import {Link} from "react-router-dom";
import { Container, Form, Button } from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css'
import './Login.css'
import app_logo from './app_logo.png'


function Login() {
    const [pwd, setPwd] = useState("")
    // We set the initial state to toggle the password visibility
    // The setShowPwd will update the state variable
    const [showPwd, setShowPwd] = useState(false)

    // Function to toggle the pwd visibility
    const togglePwd = () => {
        setShowPwd(!showPwd);
    }

    return (

        <Container className="mt-5">
            {/*App logo*/}

            <img src={app_logo} alt="App_logo" className='app_logo'/>

            <Form className="login_form" >

                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label style={{color: "white"}}>Email address</Form.Label>
                    <Form.Control type="text" name="email" placeholder="Enter Email" />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label  style={{color: "white"}}>Password</Form.Label>
                    {/* If showPwd is false, the type is just password*/}
                    {/* But if it's true, then the type will be text (this will display the pwd*/}
                    <Form.Control
                        type={showPwd ? "text":"password"}
                        name="password"
                        placeholder="Enter Password" />
                    <Button variant="outline-secondary" onClick={togglePwd}>
                        {showPwd ? "Hide" : "Show Password"}
                    </Button>
                </Form.Group>
            </Form>
            <hr style={{ backgroundColor: "white"}} />
            <Button className="btn_login" variant="primary" type="submit">
                Login
            </Button>

            <div className="d-flex justify-content-center mt-3">
                <Link to="/register" className="btn btn-secondary">Register</Link>
            </div>

        </Container>

    );
}

export default Login;