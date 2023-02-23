package com.asiana.mall.service;

import java.util.List;

import com.asiana.mall.vo.Product;
import com.asiana.mall.vo.PurchaseInfo;

public interface PurchaseService {
    List<Product> selectProductListByIdList(List<Integer> intList);
    int createPurchase(List<PurchaseInfo> purchaseInfoList);
    PurchaseInfo getPurchaseInfo(int purchaseId);
}