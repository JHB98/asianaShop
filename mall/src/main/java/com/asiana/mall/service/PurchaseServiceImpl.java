package com.asiana.mall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.asiana.mall.repository.PurchaseMapper;
import com.asiana.mall.vo.Product;
import com.asiana.mall.vo.Purchase;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	private PurchaseMapper purchaseMapper;

	public PurchaseServiceImpl(PurchaseMapper purchaseMapper) {
		this.purchaseMapper = purchaseMapper;
	}

	@Override
	public List<Purchase> getPurchase(String userId) {
		return purchaseMapper.selectPurchase(userId);
	}

	@Override
	public void postPurchase(List<Purchase> purchaseList) {
		purchaseMapper.insertPurchase(purchaseList);
	}

	@Override
	public List<Product> selectProductListByIdList(List<Integer> intList) {
		List<Product> list = purchaseMapper.selectProductListByIdList(intList);
		return list;
	}
}