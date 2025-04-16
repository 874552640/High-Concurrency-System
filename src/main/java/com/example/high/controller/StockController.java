package com.example.high.controller;

import com.example.high.common.Result;
import com.example.high.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private ProductService productService;

    /**
     * 扣减库存
     * @param productId 商品ID
     * @param quantity 扣减数量
     * @return 操作结果
     */
    @PostMapping("/{productId}/decrease")
    public Result<Boolean> decreaseStock(
            @PathVariable Long productId,
            @RequestParam Integer quantity) {
        try {
            boolean success = productService.decreaseStock(productId, quantity);
            return Result.success(success);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 增加库存
     * @param productId 商品ID
     * @param quantity 增加数量
     * @return 操作结果
     */
    @PostMapping("/{productId}/increase")
    public Result<Boolean> increaseStock(
            @PathVariable Long productId,
            @RequestParam Integer quantity) {
        try {
            boolean success = productService.increaseStock(productId, quantity);
            return Result.success(success);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 查询商品库存
     * @param productId 商品ID
     * @return 商品信息（包含库存）
     */
    @GetMapping("/{productId}")
    public Result<Integer> getStock(@PathVariable Long productId) {
        try {
            var product = productService.getProductById(productId);
            if (product == null) {
                return Result.error("商品不存在");
            }
            return Result.success(product.getStock());
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }
} 