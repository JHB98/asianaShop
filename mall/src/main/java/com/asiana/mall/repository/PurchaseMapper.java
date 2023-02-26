package com.asiana.mall.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.asiana.mall.vo.Product;
import com.asiana.mall.vo.Purchase;

@Mapper
public interface PurchaseMapper {
    List<Product> selectProductListByIdList(List<Integer> intList);

    void insertPurchase(@Param("purchaseList") List<Purchase> purchaseList);

    Purchase selectPurchaseInfo(@Param("purchaseId") int purchaseId);

    List<Purchase> selectPurchase(@Param("id") String id);
}
