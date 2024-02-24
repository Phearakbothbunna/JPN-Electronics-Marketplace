package org.cpts582.JPNElectronicsMarketplace.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cpts582.JPNElectronicsMarketplace.common.utils.SessionUtils;
import org.cpts582.JPNElectronicsMarketplace.entity.Product;
import org.cpts582.JPNElectronicsMarketplace.entity.User;
import org.cpts582.JPNElectronicsMarketplace.entity.params.UserVO;
import org.cpts582.JPNElectronicsMarketplace.mapper.UserMapper;
import org.cpts582.JPNElectronicsMarketplace.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean verifyPassword(User user) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("user_email",user.getUserEmail());
        if(userMapper.selectOne(wrapper)==null){
            return false;   // user does not exist
        }
        //user account exists
        String correctPassword = userMapper.selectOne(wrapper).getPassword();
        return correctPassword.equals(user.getPassword());
    }

    @Override
    public UserVO getCurrentUserInfo(User user) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("user_email",user.getUserEmail());
        User userDO=userMapper.selectOne(wrapper);
        UserVO userVO=new UserVO();
        BeanUtils.copyProperties(userDO,userVO);
        return userVO;
    }

    @Override
    public boolean registerUser(User user) {
        User newUser = new User();
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("user_email",user.getUserEmail());
        if (getOne(wrapper) != null) {
            return false;
        }
        newUser.setUserEmail(user.getUserEmail());
        newUser.setUserName(user.getUserName());
        newUser.setPassword(user.getPassword());
        save(newUser);
        return true;
    }



}
