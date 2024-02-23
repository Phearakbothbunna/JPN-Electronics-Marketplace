package org.cpts582.JPNElectronicsMarketplace.service;

import org.cpts582.JPNElectronicsMarketplace.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cpts582.JPNElectronicsMarketplace.entity.params.UserVO;


public interface IUserService extends IService<User> {
    boolean verifyPassword(User user);
    UserVO getCurrentUserInfo(User user);
}
