import 'bootstrap/dist/css/bootstrap.min.css';
import CustomNavbar from "./Navbar";
import { Container, Row, Col, Card, Button } from 'react-bootstrap';
import {useEffect, useState} from "react";
import {getMyProducts, deleteProduct} from "./api/product";


function MyListing() {

    const [myProducts, setMyProducts] = useState([]);
    const [error, setError] = useState(null);
    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const response = await getMyProducts({ pageNo: 1, pageSize: 10 });
                setMyProducts(response);
                // console.log(myProducts.data.record.contactInfo)

            } catch (error) {
                // console.log(myProducts.data.records.productId)
                setError(error);
                console.error("Error:", error);
            }
        };

        fetchProducts();
    }, []);

    const handleDeleteListings = async (productId) => {
        try {
            const response = await deleteProduct({productId});

            if (response.data === true) {
                console.log('Product deleted successfully!');
            } else {
                console.error('Failed to delete product:', response.data);
            }
        } catch (error) {
            console.error('Error deleting product:', error);
        }
    };

    return(
        <>
            <CustomNavbar />

            <Container fluid id='listing-container'>
                <Row>
                    <h2>MY LISTINGS</h2>
                    {myProducts.data && myProducts.data.records && myProducts.data.records.map((record, index) => (
                        <Card
                            key={index}
                            style={{ width: '16rem', marginBottom: '30px', marginTop: '20px', marginRight: '50px'  }} data-bs-theme="dark">
                            <Card.Img
                                src={record.productImgUrl || 'https://via.placeholder.com/150'}
                                style={{ width: 'auto', height: 'auto' }}
                                alt='Image Not Found'
                            />

                            <Card.Body style={{ display: 'flex', flexDirection: 'column' }}>
                                <Card.Title id='card-title'>{record.productName}</Card.Title>
                                <Card.Text id='card-text'>{record.productDescription}</Card.Text>
                                <Card.Text id='card-text'>${record.productPrice}</Card.Text>
                                <Button
                                    variant="danger"
                                    style={{ marginTop: 'auto' }}
                                    onClick={() => handleDeleteListings(record.productId)}
                                > Delete Listing </Button>
                            </Card.Body>
                        </Card>
                    ))}

                </Row>
            </Container>
        </>
    )
}


export default MyListing;