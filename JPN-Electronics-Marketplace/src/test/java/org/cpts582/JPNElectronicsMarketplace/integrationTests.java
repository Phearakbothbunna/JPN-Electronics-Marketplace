package org.cpts582.JPNElectronicsMarketplace;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cpts582.JPNElectronicsMarketplace.common.utils.SessionUtils;
import org.cpts582.JPNElectronicsMarketplace.entity.Product;
import org.cpts582.JPNElectronicsMarketplace.entity.User;
import org.cpts582.JPNElectronicsMarketplace.entity.params.PageParam;
import org.cpts582.JPNElectronicsMarketplace.entity.params.UserVO;
import org.cpts582.JPNElectronicsMarketplace.mapper.ProductMapper;
import org.cpts582.JPNElectronicsMarketplace.mapper.UserMapper;
import org.cpts582.JPNElectronicsMarketplace.service.impl.ProductServiceImpl;
import org.cpts582.JPNElectronicsMarketplace.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
public class integrationTests {
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private ProductMapper productMapper;
    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private UserVO userVO;

    private UserVO getUserVO() {
        UserVO currentUser = new UserVO();
        userVO.setUserId(1000L);
        userVO.setUserName("bob");
        userVO.setUserEmail("123@gmail.com");
        userVO.setUserContactInfo("111-222-3333");
        return currentUser;
    }

    // All tests are in the neighborhood of the app

    // Database and User verifying login
    @Test
    void app_login(){
        // Setting up by creating new user
        User user = new User();
        user.setUserEmail("j@gmail.com");
        user.setPassword("selive2000!");
        // Mock user
        User mock = new User();
        mock.setUserEmail("j@gmail.com");
        mock.setPassword("selive2000!");

        // check if the mock user is found in the database
        Mockito.when(userMapper.selectOne(Mockito.any())).thenReturn(mock);
        // test passed if the passwords match
        assertTrue(userService.verifyPassword(user));
    }

    // Test for getCurrentUserInfo
    @Test
    void getCurrentUserInfo() {
        // Setting up by creating a new user
        User user = new User();
        user.setUserEmail("current@gmail.com");
        user.setUserName("current user");
        user.setPassword("%Current123123");
        user.setUserContactInfo("2531112345");
        user.setUserId(1L);
        // Simulate the behavior of retrieving the user from the database
        Mockito.when(userMapper.selectOne(Mockito.any())).thenReturn(user);
        // Retrieve the info about the provided user
        UserVO userVO = userService.getCurrentUserInfo(user);
        // Verify that the info retrieved from userService matches with the one original user
        assertEquals(user.getUserEmail(), userVO.getUserEmail());
        assertEquals(user.getUserName(), userVO.getUserName());
        assertEquals(user.getUserContactInfo(), userVO.getUserContactInfo());
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
}
