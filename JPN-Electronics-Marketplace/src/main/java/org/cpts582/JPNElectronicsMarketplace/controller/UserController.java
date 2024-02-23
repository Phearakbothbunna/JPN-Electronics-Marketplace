package org.cpts582.JPNElectronicsMarketplace.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.cpts582.JPNElectronicsMarketplace.common.JsonResponse;
import org.cpts582.JPNElectronicsMarketplace.common.utils.SessionUtils;
import org.cpts582.JPNElectronicsMarketplace.entity.User;
import org.cpts582.JPNElectronicsMarketplace.entity.params.UserVO;
import org.cpts582.JPNElectronicsMarketplace.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Api(tags = "User related interface", description = "Including login and register")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @ApiOperation("Login and return the info of the current user")
    @ResponseBody
    @PostMapping("/login")
    public JsonResponse login(@RequestBody User user){
        if(userService.verifyPassword(user)){
            UserVO currentUser=userService.getCurrentUserInfo(user);
            SessionUtils.setCurrentUser(currentUser);
            return JsonResponse.success(currentUser,"Login succeed!");
        }
        return JsonResponse.failure("User email or password incorrect");
    }



}
