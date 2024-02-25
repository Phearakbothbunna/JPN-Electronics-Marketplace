package org.cpts582.JPNElectronicsMarketplace.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "Product", description = "Product object corresponding to db")
@Data
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("Product ID")
    @TableId
    private Long productId;

    @ApiModelProperty("User ID of owner of the product")
    private Long productSellerId;

    @ApiModelProperty("Product name")
    private String productName;

    @ApiModelProperty("Product description")
    private String productDescription;

    @ApiModelProperty("Product price")
    private Integer productPrice;

    @ApiModelProperty("Product image (URL)")
    private String productImgUrl;

    @ApiModelProperty("Contact info for product")
    private String contactInfo;

    @ApiModelProperty("Product upload time")
    private LocalDateTime productUploadTime;

    @ApiModelProperty("If product is sold")
    private Integer productIsSold;

    @ApiModelProperty("Logic deletion")
    @TableLogic(delval = "1", value = "0")
    private Integer productIsDeleted;


}
