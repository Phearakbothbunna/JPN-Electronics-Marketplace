import 'bootstrap/dist/css/bootstrap.min.css'
import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';


function CustomNavbar() {
    // handling code goes here

    // return the actual element to be rendered on the page
    return(
        <>
        <Navbar className="bg-body-tertiary">
          <Container>
            <Navbar.Brand href="home">Brand link</Navbar.Brand>
          </Container>
        </Navbar>
      </>
    )
}


export default CustomNavbar