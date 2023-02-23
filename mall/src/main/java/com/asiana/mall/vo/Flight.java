package com.asiana.mall.vo;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("Flight")
public class Flight {
	private int flightId;
	private String departure;
	private String arrival;
	private Date boardingDate;
}