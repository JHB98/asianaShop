package com.asiana.mall.service;

import java.util.List;

import com.asiana.mall.vo.Product;
import com.asiana.mall.vo.Purchase;

public interface PurchaseService {
    List<Product> selectProductListByIdList(List<Integer> intList);

    void postPurchase(List<Purchase> purchaseList);

    List<Purchase> getPurchase(String userId);
}