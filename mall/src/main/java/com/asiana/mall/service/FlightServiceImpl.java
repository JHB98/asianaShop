package com.asiana.mall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.asiana.mall.repository.FlightMapper;
import com.asiana.mall.vo.Flight;

@Service
public class FlightServiceImpl implements FlightService {

	private FlightMapper flightMapper;

	public FlightServiceImpl(FlightMapper flightMapper) {
		this.flightMapper = flightMapper;
	}

	@Override
	public List<Flight> getFlight(Flight flight) throws Exception {
		return flightMapper.selectFlight(flight);
	}
}
