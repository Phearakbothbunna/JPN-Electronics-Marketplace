import 'bootstrap/dist/css/bootstrap.min.css';
import './Navbar.css';
import {Container, Form, Nav, Navbar, NavDropdown, Button, Modal, ModalHeader, ModalTitle} from 'react-bootstrap';
import app_logo from './app_logo.png';
import {useState} from "react";


function CustomNavbar() {

    const [show, setShow] = useState(false);
    const [productName, setProductName] = useState("");
    const [productPrice, setProductPrice] = useState("")
    const [productDescription, setProductDescription] = useState("");

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const handleUpload = (e) => {
        e.preventDefault();
        // We can send the input data to our database to store them
        console.log(productName, productDescription);
        handleClose();
    };

    const handleImageUpload = (e) => {
        const image = e.target.files[0];
        // console.log(image);
    }

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
                      alt="React Bootstrap logo"/>
                </Navbar.Brand>

                <Navbar.Brand>
                  JPN-Electronics
                </Navbar.Brand>

                <Nav className="me-auto">
                    <Nav.Link href="home">Home</Nav.Link>
                </Nav>
                <Navbar.Collapse className="justify-content-end">
                    <Navbar.Text id="login-text">
                        Welcome: <a href="login">blah blah</a>
                    </Navbar.Text>

                    <Button className="btn-upload" style={{marginLeft: '30px'}} onClick={handleShow}>Upload Product</Button>

                    {/* Display the Modal if show state is True or call the handleClose function when the modal is
                    about to be hidden*/}
                    <Modal show={show} onHide={handleClose}>
                        <ModalHeader closeButton>
                            <ModalTitle>Upload Product</ModalTitle>
                        </ModalHeader>

                        <Modal.Body>
                            <Form onSubmit={handleUpload}>
                                <Form.Group className="mb-3">
                                    <Form.Label> <strong> Product Name </strong></Form.Label>
                                    <Form.Control type="text" placeholder="Enter product name" value={productName} onChange={e => setProductName(e.target.value)}/>
                                </Form.Group>

                                <Form.Group className={"mb-3"}>
                                    <Form.Label> <strong> Price </strong></Form.Label>
                                    <Form.Control type="text" placeholder="Enter product price in $" value={productPrice} onChange={e => setProductPrice(e.target.value)} />
                                </Form.Group>

                                <Form.Group className="mb-3">
                                    <Form.Label> <strong> Product Description </strong> </Form.Label>
                                    <Form.Control as="textarea" rows={4} placeholder="Enter product description" value={productDescription} onChange={e => setProductDescription(e.target.value)} />
                                </Form.Group>

                                 <Form.Group className="mb-3">
                                    <Form.Label> <strong> Image </strong> </Form.Label>
                                    <Form.Control type={"file"}/>
                                 </Form.Group>
                                <Button variant="primary" type="submit">
                                    Submit
                                </Button>
                            </Form>
                        </Modal.Body>
                    </Modal>

                </Navbar.Collapse>
            </Container>
          </Navbar>
      </>
    )
}


export default CustomNavbar