package org.cpts582.JPNElectronicsMarketplace.common.utils;


import org.cpts582.JPNElectronicsMarketplace.entity.params.UserVO;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;


public class SessionUtils {
    public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }

    public static void setCurrentUser(UserVO loginUser) {
        session().setAttribute("currentUser", loginUser);
    }
    public static UserVO getCurrentUser() {
        return (UserVO) session().getAttribute("currentUser");
    }
}
