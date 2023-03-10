package com.asiana.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.asiana.mall.service.FlightServiceImpl;
import com.asiana.mall.vo.Flight;

@Controller
public class FlightController {
	private FlightServiceImpl flightService;

	public FlightController(FlightServiceImpl flightService) {
		this.flightService = flightService;
	}

	@GetMapping("/ShopMiniMall/flightForm")
	public String flightForm(Flight flight) {
		return "/ShopMiniMall/flight";
	}

	@GetMapping("/ShopMiniMall/flight")
	public ModelAndView getFlightList(ModelAndView mv, Flight flight) throws Exception {
		mv.setViewName("/ShopMiniMall/purchase :: flightList");
		mv.addObject("flightInfo", flightService.getFlight(flight));
		return mv;
	}
}