package com.asiana.mall.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cart {
    private int cartNum;
    private String userId;
    private int productNum;
    private String brand;
    private String name;
    private int price;
    private String category;
    private int amount;
}