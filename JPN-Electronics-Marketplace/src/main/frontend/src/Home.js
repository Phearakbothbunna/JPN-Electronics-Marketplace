import './Home.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import CustomNavbar from "./Navbar";
import { Container, Row, Col, Card, Button } from 'react-bootstrap';


function Home() {
    // handling code goes here

    // return the actual element to be rendered on the page
    return(
        <>
        <CustomNavbar />
        <br></br>
        <br></br>
        <Container fluid id='listing-container'>
            <Row>
                <Col>
                    <Card style={{ width: '18rem', marginBottom: '10px'}} data-bs-theme="dark">
                        <Card.Img variant="top" src="https://media.istockphoto.com/id/178716575/photo/mobile-devices.jpg?s=612x612&w=0&k=20&c=9YyINgAbcmjfY_HZe-i8FrLUS43-qZh6Sx6raIc_9vQ=" alt='Image Not Found' />
                        <Card.Body>
                        <Card.Title id='card-title'>Imac Pro</Card.Title>
                        <Card.Text id='card-text'>
                            Lightly used, 2008, 1tb ssd
                        </Card.Text>
                        <Button variant="light">View Contact Info</Button>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
        </>
    )
}


export default Home