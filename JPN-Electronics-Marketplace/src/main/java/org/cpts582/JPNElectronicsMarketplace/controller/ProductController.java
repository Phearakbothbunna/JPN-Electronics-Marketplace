package org.cpts582.JPNElectronicsMarketplace.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import org.cpts582.JPNElectronicsMarketplace.common.JsonResponse;
import org.cpts582.JPNElectronicsMarketplace.entity.Product;
import org.cpts582.JPNElectronicsMarketplace.entity.params.PageParam;
import org.cpts582.JPNElectronicsMarketplace.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Api(tags = "Product related interface", description = "Including CRUD of products")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @ApiOperation("Get all the products in marketplace, excluding current user's")
    @ResponseBody
    @GetMapping("/shop")
    public JsonResponse getOthersProducts(@ApiParam("Page specs")
                                          @RequestBody PageParam pageParam) {
        return JsonResponse.success(productService.getProductSoldByOthers(pageParam));
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @ApiOperation("Get all the products uploaded, sold by current user")
    @ResponseBody
    @GetMapping("/myProducts")
    public JsonResponse getProductsISell(@ApiParam("Page specs")
                                         @RequestBody PageParam pageParam) {
        return JsonResponse.success(productService.getMyProducts(pageParam));
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @ApiOperation("Search products in the marketplace specified by keyword")
    @ResponseBody
    @GetMapping("/searchProducts")
    public JsonResponse getProductsFromSearchKey(@ApiParam("Page specs")
                                                 @RequestBody PageParam pageParam,
                                                 @ApiParam("Search keyword")
                                                 @RequestParam("key") String key) {
        return JsonResponse.success(productService.searchProducts(pageParam, key));
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @ApiOperation("Add a new product from the current user")
    @ResponseBody
    @GetMapping("/addProduct")
    public JsonResponse postNewProduct(@ApiParam("Product object")
                                       @RequestBody Product product) {
        return JsonResponse.success(productService.addProduct(product));
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @ApiOperation("Sell one product, remove from marketplace but remain in user's list")
    @ResponseBody
    @GetMapping("/sellProduct")
    public JsonResponse sellProduct(@ApiParam("Product ID")
                                    @RequestParam("productId") Long productId) {
        return JsonResponse.success(productService.sellProduct(productId));
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @ApiOperation("Delete one product, remove from marketplace and user's list")
    @ResponseBody
    @GetMapping("/deleteProduct")
    public JsonResponse removeProduct(@ApiParam("Product ID")
                                      @RequestParam("productId") Long productId) {
        return JsonResponse.success(productService.deleteProduct(productId));
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @ApiOperation("Change the price of the product")
    @ResponseBody
    @GetMapping("/editProductPrice")
    public JsonResponse editProductPrice(@ApiParam("Product ID")
                                         @RequestParam("productId") Long productId,
                                         @ApiParam("Product edited price")
                                         @RequestParam("newPrice")Integer newPrice) {
        return JsonResponse.success(productService.editProductPrice(productId, newPrice));
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @ApiOperation("Change the description of the product")
    @ResponseBody
    @GetMapping("/editProductInfo")
    public JsonResponse editProductDescription(@ApiParam("Product ID")
                                               @RequestParam("productId") Long productId,
                                               @ApiParam("Product edited description")
                                               @RequestParam("newDescription") String newDescription) {
        return JsonResponse.success(productService.editProductInfo(productId, newDescription));
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @ApiOperation("Change the name of the product")
    @ResponseBody
    @GetMapping("/editProductName")
    public JsonResponse editProductName(@ApiParam("Product ID")
                                        @RequestParam("productId") Long productId,
                                        @ApiParam("Product edited name")
                                        @RequestParam("newName") String newName) {
        return JsonResponse.success(productService.editProductName(productId, newName));
    }





}
