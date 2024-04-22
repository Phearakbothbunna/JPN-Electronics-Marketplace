import './Home.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import CustomNavbar from "./Navbar";
import { Container, Row, Col, Card, Button } from 'react-bootstrap';
import {useEffect, useState} from "react";
import {getProductsExceptUsers} from "./api/product";


function Home() {

    const userData = JSON.parse(sessionStorage.getItem("user"))
    const [products, setProducts] = useState([]);
    const [showContactInfo, setShowContactInfo] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const response = await getProductsExceptUsers({ pageNo: 1, pageSize: 10 });
                setProducts(response);
                console.log(products.data.record.contactInfo)

            } catch (error) {
                setError(error);
                console.error("Error fetching products:", error);
            }
        };

        fetchProducts();
    }, []);

    // This is to show or hide the contact info according to each card's index
    const handleShowContactInfo = (index) => {
        setShowContactInfo(prevState => ({
            ...prevState, [index]: !prevState[index]
        }));
    };

    return(
        <>
            <CustomNavbar />
            <Container fluid id='listing-container'>
                <Row>
                    {products.data && products.data.records && products.data.records.map((record, index) => (
                        <Card
                            key={index}
                            style={{ width: '16rem', marginBottom: '30px', marginRight: '50px'  }} data-bs-theme="dark">
                            <Card.Img
                                src={record.productImgUrl || 'https://via.placeholder.com/150'}
                                style={{ width: 'auto', height: 'auto' }}
                                alt='Image Not Found'
                            />

                            <Card.Body style={{ display: 'flex', flexDirection: 'column' }}>
                                <Card.Title id='card-title'>{record.productName}</Card.Title>
                                <Card.Text id='card-text'>{record.productDescription}</Card.Text>
                                <Card.Text id='card-text'>${record.productPrice}</Card.Text>
                                <Button className={"view_contact"} variant="light" style={{ marginTop: 'auto' }} onClick={() => handleShowContactInfo(index)}>
                                    {showContactInfo[index] ? 'Hide Contact Info' : 'View Contact Info'} </Button>
                                {showContactInfo[index] && (
                                    <div className={"contact_display"} style={{fontSize: '20px'}}>
                                        {record.contactInfo}
                                    </div>
                                )}
                            </Card.Body>
                        </Card>
                    ))}

                </Row>
            </Container>
        </>
    )
}


export default Home;