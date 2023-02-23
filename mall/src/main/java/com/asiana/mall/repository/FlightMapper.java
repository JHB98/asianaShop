package com.asiana.mall.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.asiana.mall.vo.Flight;

@Mapper
public interface FlightMapper {
	public List<Flight> searchFlight(Flight flight) throws Exception;
}
