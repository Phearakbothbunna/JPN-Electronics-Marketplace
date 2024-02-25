import 'bootstrap/dist/css/bootstrap.min.css';
import './Navbar.css';
import {Container, Form, Nav, Navbar, NavDropdown, Button, Modal, ModalHeader, ModalTitle} from 'react-bootstrap';
import app_logo from './app_logo.png';
import {useState} from "react";
import {Link} from "react-router-dom";
import {postNewProduct} from "./api/product";


function CustomNavbar() {

    const [show, setShow] = useState(false);
    const [productName, setProductName] = useState("");
    const [productPrice, setProductPrice] = useState("")
    const [productImgUrl, setProductImgURL] = useState("");
    const [contactInfo, setContactInfo] = useState("");
    const [productDescription, setProductDescription] = useState("");

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const requiredFields = ['productName', 'productPrice', 'productDescription', 'contactInfo'];

    const handleUpload = async (e) => {
        // We prevent user from submitting empty forms

        if (!productName || !productPrice || !productDescription || !contactInfo) {
            e.preventDefault();
            alert('Please fill in all the required fields');
            return;
        } else {
            try {
                await postNewProduct({productName, productPrice, productDescription,  contactInfo,productImgUrl});
                // console.log({ productName, productPrice, productDescription })
                console.log("Product uploaded successfully!");
                handleClose();
            } catch (error) {
                console.error("Error uploading product", error);
            }
        }
        // We can send the input data to our database to store them
        // console.log(productName, productDescription);
        // handleClose();
    }
    function handleLogout() {
        sessionStorage.removeItem('user');
        window.location.href = '/login';
    }

    const userData = JSON.parse(sessionStorage.getItem("user"))

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

                <Navbar.Brand as={Link} to="/home">
                    JPN-Electronics
                </Navbar.Brand>

                <Navbar.Collapse className="justify-content-end">
                    <Navbar.Text id="login-text">
                        {userData ? (
                            <>
                                Welcome <Link to="/myListing"> {userData.userName}</Link>
                                <Button className="btn-upload" style={{marginLeft: '30px'}} onClick={handleShow}>Upload Product</Button>
                                <Button className="btn_logout" style={{marginLeft: '30px'}} variant="outline-secondary" onClick={handleLogout}>Logout</Button>
                            </>
                        ) : (
                            <p>Login to start browsing!</p>
                        )}
                    </Navbar.Text>

                    {/* Display the Modal if show state is True or call the handleClose function when the modal is
                    about to be hidden*/}
                    <Modal show={show} onHide={handleClose}>
                        <ModalHeader closeButton>
                            <ModalTitle>Upload Product</ModalTitle>
                        </ModalHeader>

                        <Modal.Body>
                            <Form>
                                <Form.Group className="mb-3">
                                    <Form.Label> <strong> Product Name </strong></Form.Label>
                                    <Form.Control
                                        type="text"
                                        name="productName"
                                        placeholder="Enter product name"
                                        value={productName}
                                        onChange={(e )=> setProductName(e.target.value)} required/>
                                </Form.Group>

                                <Form.Group className={"mb-3"}>
                                    <Form.Label> <strong> Price </strong></Form.Label>
                                    <Form.Control
                                        type="text"
                                        name="productPrice"
                                        placeholder="Enter product price in $"
                                        value={productPrice} onChange={(e )=> setProductPrice(e.target.value)} required />
                                </Form.Group>

                                <Form.Group className="mb-3">
                                    <Form.Label> <strong> Product Description </strong> </Form.Label>
                                    <Form.Control
                                        as="textarea"
                                        name="productDescription"
                                        rows={4} placeholder="Enter product description"
                                        value={productDescription}
                                        onChange={(e )=> setProductDescription(e.target.value)} required />
                                </Form.Group>

                                 <Form.Group className="mb-3">
                                    <Form.Label> <strong> Image URL </strong> </Form.Label>
                                     <Form.Control
                                         type="text"
                                         name="productImgUrl"
                                         value={productImgUrl}
                                         onChange={(e )=> setProductImgURL(e.target.value)} />
                                    {/*<Form.Control type={"file"} onChange={handleImageUpload}/>*/}
                                 </Form.Group>

                                <Form.Group className="mb-3">
                                    <Form.Label> <strong> Contact Info </strong> </Form.Label>
                                    <Form.Control
                                        as="textarea"
                                        rows={3}
                                        name="contactInfo"
                                        placeholder="Phone number OR Social media...etc"
                                        value={contactInfo}
                                        onChange={(e )=> setContactInfo(e.target.value)} required />

                                </Form.Group>
                                <Button variant="primary" type="submit" onClick={handleUpload}>
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


export default CustomNavbar;