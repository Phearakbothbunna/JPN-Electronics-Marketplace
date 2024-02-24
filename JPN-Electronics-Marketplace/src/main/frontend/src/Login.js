import React, {useState} from "react";
import {Link} from "react-router-dom";
import { Container, Form, Button } from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import './Login.css';
import app_logo from './app_logo.png';
import CustomNavbar from "./Navbar";
import {login} from "./api/user";


function Login() {
    const [password, setPassword] = useState("")
    const [userEmail, setUserEmail] = useState("")
    // We set the initial state to toggle the password visibility
    // The setShowPwd will update the state variable
    const [showPwd, setShowPwd] = useState(false)

    // Function to toggle the pwd visibility
    const togglePwd = () => {
        setShowPwd(!showPwd);
    }

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await login({userEmail, password});
            if (response.message === "Login succeed!") {
                console.log(response)
                window.location.href = "/home";
            }
            else {
                console.log("Unable to login...")
            }
        } catch (error) {
            console.error("Something went wrong... ", error);
        }
    }

    return (
        <>

        <CustomNavbar />
        <br/>
        <Container className="mt-5">
            {/*App logo*/}

            <img src={app_logo} alt="App_logo" className='app_logo'/>

            <Form className="login_form">

                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label style={{color: "white"}}>Email address</Form.Label>
                    <Form.Control
                        type="text"
                        name="email"
                        placeholder="Enter Email"
                        value={userEmail}
                        onChange={(e) => setUserEmail(e.target.value)}
                        required/>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label  style={{color: "white"}}>Password</Form.Label>
                    {/* If showPwd is false, the type is just password*/}
                    {/* But if it's true, then the type will be text (this will display the pwd*/}
                    <Form.Control
                        type={showPwd ? "text":"password"}
                        name="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        placeholder="Enter Password" required/>
                    <Button variant="outline-secondary" onClick={togglePwd} className="btn_showPwd">
                        {showPwd ? "Hide" : "Show Password"}
                    </Button>
                </Form.Group>
            </Form>
            <hr style={{ backgroundColor: "white"}} />
            <Button className="btn_login" variant="primary" type="submit" onClick={handleLogin}>
                Login
            </Button>

            <div className="d-flex justify-content-center mt-3">
                <Link to="/register" className="btn btn-secondary btn_register">Register</Link>
            </div>

        </Container>
        </>

    );
}

export default Login;