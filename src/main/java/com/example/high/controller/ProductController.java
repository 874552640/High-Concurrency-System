package com.example.high.controller;

import com.example.high.common.Result;
import com.example.high.entity.Product;
import com.example.high.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 创建商品
     */
    @PostMapping
    public Result<Product> createProduct(@RequestBody Product product) {
        try {
            Product savedProduct = productService.createProduct(product);
            return Result.success(savedProduct);
        } catch (Exception e) {
            return Result.error("创建商品失败：" + e.getMessage());
        }
    }

    /**
     * 更新商品
     */
    @PutMapping("/{id}")
    public Result<Boolean> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try {
            product.setId(id);
            boolean success = productService.updateProduct(product);
            return Result.success(success);
        } catch (Exception e) {
            return Result.error("更新商品失败：" + e.getMessage());
        }
    }

    /**
     * 删除商品
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error("删除商品失败：" + e.getMessage());
        }
    }

    /**
     * 获取商品详情
     */
    @GetMapping("/{id}")
    public Result<Product> getProduct(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            return Result.success(product);
        } catch (Exception e) {
            return Result.error("获取商品失败：" + e.getMessage());
        }
    }

    /**
     * 分页查询商品
     */
    @GetMapping("/page")
    public Result<List<Product>> listProducts(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        try {
            List<Product> products = productService.listProducts(pageNum, pageSize);
            return Result.success(products);
        } catch (Exception e) {
            return Result.error("查询商品失败：" + e.getMessage());
        }
    }

    /**
     * 按类别查询商品
     */
    @GetMapping("/category/{category}")
    public Result<List<Product>> getProductsByCategory(@PathVariable String category) {
        try {
            List<Product> products = productService.getProductsByCategory(category);
            return Result.success(products);
        } catch (Exception e) {
            return Result.error("查询商品失败：" + e.getMessage());
        }
    }

    /**
     * 更新商品库存
     */
    @PutMapping("/{id}/stock")
    public Result<Product> updateStock(
            @PathVariable Long id,
            @RequestParam Integer stock) {
        try {
            Product product = productService.updateStock(id, stock);
            return Result.success(product);
        } catch (Exception e) {
            return Result.error("更新库存失败：" + e.getMessage());
        }
    }

    /**
     * 更新商品状态
     */
    @PutMapping("/{id}/status")
    public Result<Product> updateStatus(
            @PathVariable Long id,
            @RequestParam Integer status) {
        try {
            Product product = productService.updateStatus(id, status);
            return Result.success(product);
        } catch (Exception e) {
            return Result.error("更新状态失败：" + e.getMessage());
        }
    }
} 