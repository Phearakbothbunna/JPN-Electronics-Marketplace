package org.cpts582.JPNElectronicsMarketplace;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cpts582.JPNElectronicsMarketplace.common.utils.SessionUtils;
import org.cpts582.JPNElectronicsMarketplace.entity.Product;
import org.cpts582.JPNElectronicsMarketplace.entity.User;
import org.cpts582.JPNElectronicsMarketplace.entity.params.PageParam;
import org.cpts582.JPNElectronicsMarketplace.entity.params.UserVO;
import org.cpts582.JPNElectronicsMarketplace.mapper.ProductMapper;
import org.cpts582.JPNElectronicsMarketplace.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;

import static com.baomidou.mybatisplus.generator.config.OutputFile.service;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class productTest {

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private UserVO userVO;

    @Before
    public void setUp() {
    }

    private UserVO getUserVO() {
        UserVO currentUser = new UserVO();
        userVO.setUserId(1000L);
        userVO.setUserName("bob");
        userVO.setUserEmail("123@gmail.com");
        userVO.setUserContactInfo("111-222-3333");
        return currentUser;
    }

    @Test
    public void testGetProductSoldByOthers() {
        SessionUtils.setCurrentUser(getUserVO());
        PageParam pageParam = new PageParam(1L, 10L);
        Page<Product> expectedProducts = new Page<>(1, 10);

        when(productMapper.selectPage(any(Page.class), any(QueryWrapper.class))).thenReturn(expectedProducts);

        Page<Product> actualProducts = productService.getProductSoldByOthers(pageParam);

        assertEquals(expectedProducts, actualProducts);
        verify(productMapper, times(1)).selectPage(any(Page.class), any(QueryWrapper.class));
    }

    @Test
    public void testGetMyProducts() {
        SessionUtils.setCurrentUser(getUserVO());
        PageParam pageParam = new PageParam(1L, 10L);
        Page<Product> expectedProducts = new Page<>(1, 10);

        when(productMapper.selectPage(any(Page.class), any(QueryWrapper.class))).thenReturn(expectedProducts);

        Page<Product> actualProducts = productService.getMyProducts(pageParam);

        assertEquals(expectedProducts, actualProducts);
        verify(productMapper, times(1)).selectPage(any(Page.class), any(QueryWrapper.class));
    }

    @Test
    public void testSearchProducts() {
        PageParam pageParam = new PageParam(1L, 10L);
        String key = "product";
        Page<Product> expectedProducts = new Page<>(1, 10);

        when(productMapper.selectPage(any(Page.class), any(QueryWrapper.class))).thenReturn(expectedProducts);

        Page<Product> actualProducts = productService.searchProducts(pageParam, key);

        assertEquals(expectedProducts, actualProducts);
        verify(productMapper, times(1)).selectPage(any(Page.class), any(QueryWrapper.class));
    }

    @Test
    public void testAddProduct() {
        Product product = new Product();
        product.setProductName("New Product");
        product.setProductPrice(1000);
        product.setProductDescription("This is a new product");
        product.setProductImgUrl("http://example.com/product.jpg");

        // Set up the current user for the test
        UserVO currentUser = getUserVO();
        SessionUtils.setCurrentUser(currentUser);

        // Mock the behavior of save method
        when(productMapper.insert(any(Product.class))).thenReturn(1);

        // Execute the method under test
        boolean result = productService.addProduct(product);

        // Assertions
        assertTrue(result);
        assertNotNull(product.getProductUploadTime());
        assertEquals(currentUser.getUserId(), product.getProductSellerId());

        // Verify that the save method was called with the product object
        verify(productMapper).insert(product);
    }

    @Test
    public void testEditProductPrice() {
        Long productId = 1L;
        Integer newPrice = 1000;
        Product product = new Product();
        product.setProductId(productId);
        product.setProductPrice(500);

        // Mocking getById to return our product
        when(productService.getById(productId)).thenReturn(product);

        // Assuming saveOrUpdate returns a boolean in your implementation
        when(productMapper.updateById(product)).thenReturn(1); // 1 typically indicates a successful update in MyBatis

        // Execute the method under test
        boolean result = productService.editProductPrice(productId, newPrice);

        // Assertions
        assertTrue(result);
        assertEquals(newPrice, product.getProductPrice());

        // Verify that updateById was called on the mapper with the updated product
        verify(productMapper).updateById(product);
    }


    @Test
    public void testEditProductInfo() {
        Long productId = 1L;
        String newDescription = "Updated product description";
        Product product = new Product();
        product.setProductId(productId);
        product.setProductDescription("Original product description");

        when(productService.getById(productId)).thenReturn(product);
        when(productMapper.updateById(product)).thenReturn(1);

        boolean result = productService.editProductInfo(productId, newDescription);

        assertTrue(result);
        assertEquals(newDescription, product.getProductDescription());
        verify(productMapper).updateById(product);
    }

    @Test
    public void testEditProductName() {
        Long productId = 1L;
        String newName = "Updated product name";
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName("Original product name");

        when(productService.getById(productId)).thenReturn(product);
        when(productMapper.updateById(product)).thenReturn(1);

        boolean result = productService.editProductName(productId, newName);

        assertTrue(result);
        assertEquals(newName, product.getProductName());
        verify(productMapper).updateById(product);
    }

    @Test
    public void testDeleteProduct() {
        Long productId = 1L;
        Product product = new Product();
        product.setProductId(productId);
        product.setProductIsDeleted(0);

        when(productService.getById(productId)).thenReturn(product);
        when(productMapper.updateById(product)).thenReturn(1);

        boolean result = productService.deleteProduct(productId);

        assertTrue(result);
        assertEquals(1, product.getProductIsDeleted().intValue());
        verify(productMapper).updateById(product);
    }

    @Test
    public void testSellProduct() {
        // Setting up by creating a new product
        Long productId = 1L;
        Product product = new Product();
        product.setProductId(productId);
        product.setProductIsSold(0);

        // Perform mocking behavior
        // This will ensure that it returns the product object we created above when getById is called
        when(productService.getById(productId)).thenReturn(product);
        // This will ensure that the update operation is successful (changed from 0 to 1)
        when(productMapper.updateById(product)).thenReturn(1);

        // Store the boolean result of sellProduct
        boolean result = productService.sellProduct(productId);
        // Assert true if the sellProduct method got executed successfully
        assertTrue(result);
        // Check to see if the value has been updated to 1 indicating that it's been sold
        assertEquals(1, product.getProductIsSold().intValue());
        verify(productMapper).updateById(product);
    }





}
