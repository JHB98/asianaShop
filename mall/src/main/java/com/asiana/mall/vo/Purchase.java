package com.asiana.mall.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    private int purchaseNum;
    private String userId;
    private int flightNum;
    private String departure;
    private String arrival;
    private Date boardingDate;
    private int productNum;
    private String brand;
    private String name;
    private int amount;
    private int price;
}