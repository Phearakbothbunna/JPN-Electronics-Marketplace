import React, {useState} from "react";
import {Link} from "react-router-dom";
import { Container, Form, Button } from "react-bootstrap";
import app_logo from "./app_logo.png";



function Register() {
    const[showPwd, setShowPwd] = useState(false)
    const togglePwd = () => {
        setShowPwd(!showPwd);
    }

     const pwdClicked = async(e) => {
        alert("Passwords requirements: \n-At least 8 characters long \n-At least one capital letter \n-At least one number 0-9 \n-At least one symbol");
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
                        placeholder="Enter Password"
                    onClick={pwdClicked}/>
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
                <hr style={{ backgroundColor: "white"}} />
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