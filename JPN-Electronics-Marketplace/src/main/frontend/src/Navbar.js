import 'bootstrap/dist/css/bootstrap.min.css';
import './Navbar.css';
import {Container, Form, Nav, Navbar, NavDropdown} from 'react-bootstrap';
import app_logo from './app_logo.png';


function CustomNavbar() {
    // handling code goes here

    // return the actual element to be rendered on the page
    return(
        <>
          <Navbar fixed="top" className="justify-content-left" bg="dark" data-bs-theme="dark">
            <Container>
            <Navbar.Brand>
            <img
              src={app_logo}
              width="50"
              height="50"
              className="d-inline-block align-top"
              alt="React Bootstrap logo"
            />
          </Navbar.Brand>
              <Navbar.Brand>JPN-Electronics</Navbar.Brand>
              <Nav className="me-auto">
                <Nav.Link href="home">Home</Nav.Link>
              </Nav>
              <Navbar.Collapse className="justify-content-end">
          <Navbar.Text id="login-text">
            Welcome: <a href="login">blah blah</a>
          </Navbar.Text>
        </Navbar.Collapse>

            </Container>
          </Navbar>
      </>
    )
}


export default CustomNavbar