package com.asiana.mall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.asiana.mall.repository.PurchaseMapper;
import com.asiana.mall.vo.Product;
import com.asiana.mall.vo.PurchaseInfo;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	private PurchaseMapper purchaseMapper;
	
	public PurchaseServiceImpl(PurchaseMapper purchaseMapper) {
		this.purchaseMapper = purchaseMapper;
	}
	
	@Override
	public PurchaseInfo getPurchaseInfo(int purchaseId) {
		PurchaseInfo result = purchaseMapper.selectPurchaseInfo(purchaseId);
		return result;
	}

	@Override
	public int createPurchase(List<PurchaseInfo> purchaseInfoList) {
		int result = purchaseMapper.insertPurchase(purchaseInfoList);
		return result;
	}

	@Override
	public List<Product> selectProductListByIdList(List<Integer> intList) {
		List<Product> list = purchaseMapper.selectProductListByIdList(intList);
		return list;
	}
}