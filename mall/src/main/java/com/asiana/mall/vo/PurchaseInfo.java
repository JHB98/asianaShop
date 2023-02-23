package com.asiana.mall.vo;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseInfo {
    private int purchaseNum;
    @NotBlank
    private int userNum;
    @NotBlank
    private int flightNum;
    @NotBlank
	private int productNum;   
    private int amount;  
    private Date orderDate;   
    private String purchaseDetailStatus;
}