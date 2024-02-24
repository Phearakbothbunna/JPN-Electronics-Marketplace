package org.cpts582.JPNElectronicsMarketplace.service;

import org.cpts582.JPNElectronicsMarketplace.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cpts582.JPNElectronicsMarketplace.entity.params.PageParam;

public interface IProductService extends IService<Product> {
    Page<Product> getProductSoldByOthers(PageParam pageParam);
    Page<Product> getMyProducts(PageParam pageParam);
    Page<Product> searchProducts(PageParam pageParam,String key);
    boolean addProduct(Product product);
    boolean sellProduct(Long productId);
    boolean deleteProduct(Long productId);
    boolean editProductPrice(Long productId, Integer newPrice);
    boolean editProductInfo(Long productId, String newDescription);
    boolean editProductName(Long productId, String newName);
}
