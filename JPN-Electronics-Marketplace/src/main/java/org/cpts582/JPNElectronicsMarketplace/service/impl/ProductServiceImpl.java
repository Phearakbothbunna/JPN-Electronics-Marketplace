package org.cpts582.JPNElectronicsMarketplace.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cpts582.JPNElectronicsMarketplace.common.utils.SessionUtils;
import org.cpts582.JPNElectronicsMarketplace.entity.Product;
import org.cpts582.JPNElectronicsMarketplace.entity.params.PageParam;
import org.cpts582.JPNElectronicsMarketplace.mapper.ProductMapper;
import org.cpts582.JPNElectronicsMarketplace.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;
    @Override
    public Page<Product> getProductSoldByOthers(PageParam pageParam) {
        Page<Product> products = new Page<>(pageParam.getPageNo(), pageParam.getPageSize());
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.ne("product_seller_id", SessionUtils.getCurrentUser().getUserId())
                .eq("product_is_deleted",0)
                .eq("product_is_sold",0);
        return productMapper.selectPage(products, wrapper);
    }

    @Override
    public Page<Product> getMyProducts(PageParam pageParam) {
        Page<Product> products = new Page<>(pageParam.getPageNo(), pageParam.getPageSize());
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("product_seller_id", SessionUtils.getCurrentUser().getUserId())
                .eq("product_is_deleted",0);
        return productMapper.selectPage(products, wrapper);

    }

    @Override
    public Page<Product> searchProducts(PageParam pageParam, String key) {
        Page<Product> page=new Page<>(pageParam.getPageNo(), pageParam.getPageSize());
        QueryWrapper<Product> wrapper=new QueryWrapper<>();
        if(StringUtils.isNotBlank(key)){
            wrapper.like("product_name", key)
                    .eq("product_is_deleted",0)
                    .eq("product_is_sold",0);
        }
        return productMapper.selectPage(page, wrapper);
    }

    @Override
    public boolean addProduct(Product product) {
        product = new Product();
        product.setProductDescription(product.getProductDescription());
        product.setProductName(product.getProductName());
        product.setProductPrice(product.getProductPrice());
        product.setProductImgUrl(product.getProductImgUrl());
        product.setProductUploadTime(LocalDateTime.now());
        product.setProductSellerId(SessionUtils.getCurrentUser().getUserId());
        return save(product);
    }

    @Override
    public boolean sellProduct(Long productId) {
        QueryWrapper<Product> wrapper=new QueryWrapper<>();
        Product product = getById(productId);
        product.setProductIsSold(1);
        return saveOrUpdate(product);
    }

    @Override
    public boolean deleteProduct(Long productId) {
        QueryWrapper<Product> wrapper=new QueryWrapper<>();
        Product product = getById(productId);
        product.setProductIsDeleted(1);
        return saveOrUpdate(product);
    }

    @Override
    public boolean editProductPrice(Long productId, Integer newPrice) {
        QueryWrapper<Product> wrapper=new QueryWrapper<>();
        Product product = getById(productId);
        product.setProductPrice(newPrice);
        return saveOrUpdate(product);
    }

    @Override
    public boolean editProductInfo(Long productId, String newDescription) {
        QueryWrapper<Product> wrapper=new QueryWrapper<>();
        Product product = getById(productId);
        product.setProductDescription(newDescription);
        return saveOrUpdate(product);
    }

    @Override
    public boolean editProductName(Long productId, String newName) {
        QueryWrapper<Product> wrapper=new QueryWrapper<>();
        Product product = getById(productId);
        product.setProductName(newName);
        return saveOrUpdate(product);
    }

}
