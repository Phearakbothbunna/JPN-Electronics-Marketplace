package org.cpts582.JPNElectronicsMarketplace;
import org.cpts582.JPNElectronicsMarketplace.entity.User;
import org.cpts582.JPNElectronicsMarketplace.entity.params.UserVO;
import org.cpts582.JPNElectronicsMarketplace.mapper.UserMapper;
import org.cpts582.JPNElectronicsMarketplace.service.IUserService;
import org.cpts582.JPNElectronicsMarketplace.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.AssertFalse;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class userTest {
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserServiceImpl userService;

    // Test for when user entered correct credentials
    @Test
    void verify_correct_password(){
        User user = new User();
        user.setUserEmail("test@gmail.com");
        user.setPassword("%Abc123123");

        User mockedUser = new User();
        mockedUser.setUserEmail("test@gmail.com");
        mockedUser.setPassword("%Abc123123");

        Mockito.when(userMapper.selectOne(Mockito.any())).thenReturn(mockedUser);
        assertTrue(userService.verifyPassword(user));

    }

    // Test for when user entered incorrect password
    @Test
    void verify_incorrect_password() {
        User user = new User();
        user.setUserEmail("test2@gmail.com");
        user.setPassword("%Abc123123");

        User mockedUser = new User();
        mockedUser.setUserEmail("test2@gmail.com");
        mockedUser.setPassword("@Abcabc123");

        Mockito.when(userMapper.selectOne(Mockito.any())).thenReturn(mockedUser);
        assertFalse(userService.verifyPassword(user));

    }

    // Test for when user does not exist
    @Test
    void verify_invalid_user() {
        User user = new User();
        user.setUserEmail("user@notexist.com");
        user.setPassword("randomPassword");

        Mockito.when(userMapper.selectOne(Mockito.any())).thenReturn(null);
        assertFalse(userService.verifyPassword(user));
    }

    // Test for getCurrentUserInfo
    @Test
    void getCurrentUserInfo() {
        User user = new User();
        user.setUserEmail("current@gmail.com");
        user.setUserName("current user");
        user.setPassword("%Current123123");
        user.setUserContactInfo("2531112345");
        user.setUserId(1L);

        Mockito.when(userMapper.selectOne(Mockito.any())).thenReturn(user);

        UserVO userVO = userService.getCurrentUserInfo(user);
        assertEquals(user.getUserEmail(), userVO.getUserEmail());
        assertEquals(user.getUserName(), userVO.getUserName());
        assertEquals(user.getUserContactInfo(), userVO.getUserContactInfo());
    }

    // Test the user registration for new user that doesn't exist yet
    @Test
    void register_new_user() {
        Mockito.when(userMapper.selectOne(Mockito.any())).thenReturn(null);

        boolean res = userService.registerUser(new User());
        assertTrue(res);
    }

    // Test the user registration for a user that already exists in the db
    @Test
    void register_existing_user() {

        User existingUser = new User();
        existingUser.setUserEmail("existing@gmail.com");


        Mockito.when(userMapper.selectOne(Mockito.any())).thenReturn(existingUser);
        assertFalse(userService.registerUser(new User()));
    }



}
