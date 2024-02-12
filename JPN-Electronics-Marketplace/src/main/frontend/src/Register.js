import React, {useState} from "react";
import {Link} from "react-router-dom";
import { Container, Form, Button } from "react-bootstrap";
import app_logo from "./app_logo.png";


function Register() {
    // Set the initial state of the variable, so we can update the state variable later on
    const [email, setEmail] = useState("");
    const [pwd, setPwd] = useState("");
    const [confirmPwd, setConfirmPwd] = useState("");
    const[showPwd, setShowPwd] = useState(false);
    const togglePwd = () => {
        setShowPwd(!showPwd);
    }
    // Create a function to handle the user registration
    // async will allow the code to run while waiting for other tasks
    const handleRegister = async (e) => {
        // For testing purposes
        console.log(email, pwd);
        e.preventDefault()

        // Validate the password & confirm password fields to ensure they match
        if (pwd !== confirmPwd) {
            alert("Password and confirm password do not match");
        }

        // Validate the email field to ensure it contains "@" symbol
        else if (/[@]/.test(email) === false) {
            alert("Email must contain @ symbol");
        }

        else {
            // Validate that the password is at least 8 characters long
            if (pwd.length < 8) {
                alert("Password needs to be at least 8 characters long");
            }
            // Validate that the password contains at least 1 upper case character
            else if (/[A-Z]/.test(pwd) === false) {
                alert("Password must contain at least 1 capital letter");
            }
            // Validate that the password contains at least 1 number
            else if (/\d/.test(pwd) === false) {
                alert("Password must contain at least 1 number");
            }
            // Validate that the password contains at least 1 symbol or special character
            else if (/[@!#$%^&*]/.test(pwd) === false) {
                alert("Password must contain at least 1 symbol");
            } else {
                window.location.href = "/login";
            }
        }
    }

    return (

        <Container className="mt-5">
            {/*App logo*/}

            <img src={app_logo} alt="App_logo" className='app_logo'/>

            <Form className="register_form">

                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label style={{color: "antiquewhite"}}>Email address</Form.Label>
                    <Form.Control
                        type="text"
                        name="email"
                        placeholder="Must contain @ symbol"
                        onChange={(e) => setEmail(e.target.value)}/>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label  style={{color: "antiquewhite"}}>Password</Form.Label>
                    <Form.Control
                        type={showPwd ? "text":"password"}
                        name="password"
                        placeholder="AT LEAST: 8 characters long, 1 capital letter, 1 number and 1 symbol"
                        // This will capture the user's input when they type in the form
                        onChange={(e) => setPwd(e.target.value)}/>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label  style={{color: "antiquewhite"}}>Confirm Password</Form.Label>
                    <Form.Control
                        type={showPwd ? "text":"password"}
                        name="confirm_password"
                        placeholder="Must match with Password above"
                        onChange={(e) => setConfirmPwd(e.target.value)}/>
                </Form.Group>
                <Button variant="outline-secondary" onClick={togglePwd} className="btn_showPwd">
                        {showPwd ? "Hide" : "Show Password"}
                </Button>
                <hr style={{ backgroundColor: "white"}} />
                <Link to="/login">
                    <div>
                        <Button className="btn_submit" variant="outline-primary" type="submit" onClick={handleRegister}>
                            Submit!
                        </Button>
                    </div>
                    <Button className="btn_back" variant="secondary">
                        Return to Login page
                    </Button>
                </Link>
            </Form>
        </Container>


);
}

export default Register;