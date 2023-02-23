package com.asiana.mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiana.mall.repository.FlightMapper;
import com.asiana.mall.vo.Flight;

@Service
public class FlightServiceImpl implements FlightService {
	@Autowired
	FlightMapper mapper;

	@Override
	public List<Flight> searchFlight(Flight flight) throws Exception {
		// TODO Auto-generated method stub
		List<Flight> list = mapper.searchFlight(flight);
		System.out.print(list);
		System.out.print("아아");
		
		return list;
	}
}
