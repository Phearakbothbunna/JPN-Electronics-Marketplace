import 'bootstrap/dist/css/bootstrap.min.css';
// import Container from 'react-bootstrap/Container';
// import Navbar from 'react-bootstrap/Navbar';
import {Container, Form, Nav, Navbar, NavDropdown} from 'react-bootstrap';


function CustomNavbar() {
    // handling code goes here

    // return the actual element to be rendered on the page
    return(
        <>
          <Navbar bg="primary" data-bs-theme="dark">
            <Container>
              <Navbar.Brand href="#home">Navbar</Navbar.Brand>
              <Nav className="me-auto">
                <Nav.Link href="#home">Home</Nav.Link>
                <Nav.Link href="#features">Features</Nav.Link>
                <Nav.Link href="#pricing">Pricing</Nav.Link>
              </Nav>
            </Container>
          </Navbar>
      </>
    )
}


export default CustomNavbar