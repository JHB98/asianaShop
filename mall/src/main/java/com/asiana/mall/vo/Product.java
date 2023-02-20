package com.asiana.mall.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {
    private int number;
    private String brand;
    private String name;
    private int price;
    private String category;
}
