package com.asiana.mall.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.asiana.mall.vo.Flight;

@Mapper
public interface FlightMapper {
	List<Flight> selectFlight(@Param("flight") Flight flight) throws Exception;
}
