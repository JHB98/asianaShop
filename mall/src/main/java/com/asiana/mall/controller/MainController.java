package com.asiana.mall.controller;

import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.asiana.mall.service.MemberServiceImpl;
import com.asiana.mall.service.ProductServiceImpl;
import com.asiana.mall.vo.Member;
import com.asiana.mall.vo.Message;
import com.asiana.mall.vo.Product;
import java.util.Map;

@Controller
public class MainController {

	private final MemberServiceImpl memberService;
	private final ProductServiceImpl productService;

	public MainController(MemberServiceImpl memberService, ProductServiceImpl productService) {
		this.memberService = memberService;
		this.productService = productService;
	}

	public String encodeBcrypt(String pwd) {
		return new BCryptPasswordEncoder().encode(pwd);
	}

	@GetMapping("/ShopMiniMall/main")
	public ModelAndView getMain(Product product, ModelAndView mv) {
		mv.setViewName("/ShopMiniMall/main");
		mv.addObject("data", productService.getProduct(product));
		return mv;
	}

	@GetMapping("/ShopMiniMall/member")
	public ModelAndView getMember(Member member, ModelAndView mv) {
		mv.setViewName("/ShopMiniMall/member");
		mv.addObject("data", memberService.getMember(member));
		return mv;
	}

	@GetMapping("/ShopMiniMall/MemberUIServlet")
	public String getMembershipForm(Model model, Member member) {
		model.addAttribute("member", member);
		return "/ShopMiniMall/memberShip";
	}

	@GetMapping("/ShopMiniMall/product/{number}")
	public ModelAndView getProductById(@PathVariable("number") int number, ModelAndView mv) {
		mv.setViewName("/ShopMiniMall/product");
		mv.addObject("data", productService.getProductById(number));

		return mv;
	}

	@PostMapping("/ShopMiniMall/membership")
	public ModelAndView insertMember(@Valid Member member, Errors errors, ModelAndView mv) {
		if (errors.hasErrors()) {
			// 유효성 통과 못한 필드와 메시지를 핸들링
			Map<String, String> validatorResult = memberService.validateHandling(errors);
			for (String key : validatorResult.keySet()) {
				mv.addObject(key, validatorResult.get(key));
			}
			mv.setViewName("/ShopMiniMall/memberShip");
			return mv;
		}

		member.setPwd(encodeBcrypt(member.getPwd()));
		memberService.postMember(member);
		mv.addObject("data", new Message("회원가입이 완료되었습니다.", "/ShopMiniMall/main"));
		mv.setViewName("/ShopMiniMall/Message");

		return mv;
	}
}
