package com.asiana.mall.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.asiana.mall.vo.Product;
import com.asiana.mall.vo.PurchaseInfo;

@Mapper
public interface PurchaseMapper {
    List<Product> selectProductListByIdList(List<Integer> intList) ;
    int insertPurchase(@Param("purchaseInfoList") List<PurchaseInfo> purchaseInfoList);
    PurchaseInfo selectPurchaseInfo(@Param("purchaseId") int purchaseId);
}
