package com.asiana.mall.service;

import java.util.List;

import com.asiana.mall.vo.Flight;

public interface FlightService {
	public List<Flight> searchFlight(Flight flight) throws Exception;
}
