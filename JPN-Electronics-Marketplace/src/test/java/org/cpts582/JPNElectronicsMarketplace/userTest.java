package org.cpts582.JPNElectronicsMarketplace;
import org.cpts582.JPNElectronicsMarketplace.entity.User;
import org.cpts582.JPNElectronicsMarketplace.entity.params.UserVO;
import org.cpts582.JPNElectronicsMarketplace.mapper.UserMapper;
import org.cpts582.JPNElectronicsMarketplace.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

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
        // Setting up by creating new user
        User user = new User();
        user.setUserEmail("test@gmail.com");
        user.setPassword("%Abc123123");
        // Setting up another user to mock the behavior (this represents a user retrieved from database)
        User mockedUser = new User();
        mockedUser.setUserEmail("test@gmail.com");
        mockedUser.setPassword("%Abc123123");
        // Return the mockedUser if it's found in the database with the provided email
        Mockito.when(userMapper.selectOne(Mockito.any())).thenReturn(mockedUser);
        // Return true if the password verification was successful
        assertTrue(userService.verifyPassword(user));
    }

    // Test for when user entered incorrect password
    @Test
    void verify_incorrect_password() {
        // Setting up by creating a new user
        User user = new User();
        user.setUserEmail("test2@gmail.com");
        user.setPassword("%Abc123123");
        // Create another user to mock the behavior
        User mockedUser = new User();
        mockedUser.setUserEmail("test2@gmail.com");
        mockedUser.setPassword("@Abcabc123");
        // Return the mockedUser if it's found in the database with the provided email (simulation)
        Mockito.when(userMapper.selectOne(Mockito.any())).thenReturn(mockedUser);
        // Return false if the password for the new user does not match with the mocked user
        assertFalse(userService.verifyPassword(user));

    }

    // Test for when user does not exist
    @Test
    void verify_invalid_user() {
        // Setting up by creating a new user
        User user = new User();
        user.setUserEmail("user@notexist.com");
        user.setPassword("randomPassword");
        // The mock method should return null for the case where the user does not exist in the database
        Mockito.when(userMapper.selectOne(Mockito.any())).thenReturn(null);
        // Return false for the password verification since the user does not exist
        assertFalse(userService.verifyPassword(user));
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

    // Test the user registration for new user that doesn't exist yet
    @Test
    void register_new_user() {
        // The mock method should return null for the case where the user does not already exist in the database
        Mockito.when(userMapper.selectOne(Mockito.any())).thenReturn(null);
        // Attempt to create a new user object and store the result of the creation (success or fail)
        boolean res = userService.registerUser(new User());
        // If the user was created successfully, it should return true (success)
        assertTrue(res);
    }

    // Test the user registration for a user that already exists in the db
    @Test
    void register_existing_user() {
        // Setting up by creating a new user
        User existingUser = new User();
        existingUser.setUserEmail("existing@gmail.com");
        // The mock method should return the user that already existed in the database
        Mockito.when(userMapper.selectOne(Mockito.any())).thenReturn(existingUser);
        // Should return false to indicate that user registration failed since user already existed
        assertFalse(userService.registerUser(new User()));
    }
}
