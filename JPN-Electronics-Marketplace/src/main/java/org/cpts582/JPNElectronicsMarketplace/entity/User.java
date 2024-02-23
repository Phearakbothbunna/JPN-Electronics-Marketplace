package org.cpts582.JPNElectronicsMarketplace.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "User", description = "User object corresponding to db")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("User ID")
    @TableId
    private Long userId;

    @ApiModelProperty("User Email")
    private String userEmail;

    @ApiModelProperty("User name")
    private String userName;

    @ApiModelProperty("User contact information")
    private String userContactInfo;

    @ApiModelProperty("User password")
    private String password;

    @ApiModelProperty("Logic deletion")
    @TableLogic(delval = "1", value = "0")
    private Integer userIsDeleted;

}
