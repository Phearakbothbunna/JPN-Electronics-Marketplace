import React, {useState} from "react";
import {Link} from "react-router-dom";
import { Container, Form, Button } from "react-bootstrap";
import app_logo from "./app_logo.png";
import {register} from "./api/user";


function Register() {
    // Set the initial state of the variable, so we can update the state variable later on
    const [userEmail, setUserEmail] = useState("");
    const [userName, setUserName] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPwd, setConfirmPwd] = useState("");
    const [userContactInfo, setUserContactInfo] = useState("");
    const [showPwd, setShowPwd] = useState(false);

    const [emailValid, setEmailValid] = useState(false);
    const [pwdValid, setPwdValid] = useState(false);


    const validateEmail = (email) => {
        const isValid = /@/.test(email);
        setEmailValid(isValid);
    };

    const validatePwd = (password) => {
        const isValid = password.length >= 8 && /[A-Z]/.test(password) && /\d/.test(password) && /[@!#$%^&*]/.test(password);
        setPwdValid(isValid)
    }

    const togglePwd = () => {
        setShowPwd(!showPwd);
    }
    // Create a function to handle the user registration
    // async will allow the code to run while waiting for other tasks
    const handleRegister = async (e) => {
        // For testing purposes
        console.log(userEmail, password);
        e.preventDefault();
        if (emailValid && pwdValid && password === confirmPwd) {
            try {
                const response = await register({userEmail, password, userContactInfo, userName})
                if (response.data === "Register succeed!") {
                    console.log("Registered!")
                    window.location.href = "/login";
                }
                else {
                    console.error("Registration failed:", response);
                }
            } catch (error) {
                console.log(error);
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
                        name="userEmail"
                        value={userEmail}
                        onChange={(e) => {
                            setUserEmail(e.target.value);
                            validateEmail(e.target.value);
                        }}
                        placeholder="Must contain @ symbol"
                        className={emailValid ? '' : 'is-invalid'}
                    />

                </Form.Group>

                <Form.Group className="mb-3" controlId="formUsername">
                    <Form.Label style={{color: "antiquewhite"}}>Username</Form.Label>
                    <Form.Control
                        type="text"
                        name="userName"
                        value={userName}
                        onChange={(e) => setUserName(e.target.value)}
                        placeholder="Come up with your favorite username"
                    />

                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label style={{color: "antiquewhite"}}>Password</Form.Label>
                    <Form.Control
                        type={showPwd ? "text" : "password"}
                        name="password"
                        placeholder="AT LEAST: 8 characters long, 1 capital letter, 1 number and 1 symbol"
                        // This will capture the user's input when they type in the form
                        onChange={(e) => {
                            setPassword(e.target.value);
                            validatePwd(e.target.value);
                        }}
                        className={pwdValid ? '' : 'is-invalid'}
                        />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label style={{color: "antiquewhite"}}>Confirm Password</Form.Label>
                    <Form.Control
                        type={showPwd ? "text" : "password"}
                        name="confirm_password"
                        placeholder="Must match with Password above"
                        onChange={(e) => setConfirmPwd(e.target.value)}
                        className= {confirmPwd === password ? '' : "is-invalid"}
                    />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formUserContactInfo">
                    <Form.Label style={{color: "antiquewhite"}}>Contact Info</Form.Label>
                    <Form.Control
                        type="text"
                        name="userContactInfo"
                        value={userContactInfo}
                        onChange={(e) => setUserContactInfo(e.target.value)}
                        placeholder="PhoneNumber/Email/IG/Snapchat..."
                    />
                </Form.Group>

                <Button variant="outline-secondary" onClick={togglePwd} className="btn_showPwd">
                    {showPwd ? "Hide" : "Show Password"}
                </Button>
                <hr style={{backgroundColor: "white"}}/>
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