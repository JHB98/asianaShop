package com.asiana.mall.controller;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.asiana.mall.service.CartServiceImpl;
import com.asiana.mall.service.MemberServiceImpl;
import com.asiana.mall.service.ProductServiceImpl;
import com.asiana.mall.vo.Member;
import com.asiana.mall.vo.Message;
import com.asiana.mall.vo.Product;
import com.asiana.mall.vo.Cart;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
public class MainController {

	private final MemberServiceImpl memberService;
	private final ProductServiceImpl productService;
	private final CartServiceImpl cartService;

	public MainController(MemberServiceImpl memberService, ProductServiceImpl productService,
			CartServiceImpl cartService) {
		this.memberService = memberService;
		this.productService = productService;
		this.cartService = cartService;
	}

	public String encodeBcrypt(String pwd) {
		return new BCryptPasswordEncoder().encode(pwd);
	}

	// @GetMapping("/ShopMiniMall/main")
	// public ModelAndView getMain(Product product, ModelAndView mv, Cart cart) {
	// mv.setViewName("/ShopMiniMall/main");
	// mv.addObject("cart", cart);
	// mv.addObject("data", productService.getProduct(product));
	// return mv;
	// }

	@GetMapping("/ShopMiniMall/member")
	public ModelAndView getMember(Member member, ModelAndView mv) {
		mv.setViewName("/ShopMiniMall/member");
		mv.addObject("data", memberService.getMember(member));
		return mv;
	}

	@GetMapping("/ShopMiniMall/member/{id}")
	@ResponseBody
	public boolean getMemberById(@PathVariable("id") String id, Member member) {
		if (memberService.getMemberById(id) == null) {
			return true;
		}
		return false;
	}

	@GetMapping("/ShopMiniMall/MemberUIServlet")
	public String getMembershipForm(Model model, Member member) {
		model.addAttribute("member", member);
		return "/ShopMiniMall/memberShip";
	}

	@PostMapping("/ShopMiniMall/membership")
	public ModelAndView insertMember(@Valid Member member, Errors errors, ModelAndView mv) {
		if (errors.hasErrors()) {
			// 유효성 통과 못한 필드와 메시지를 핸들링
			Map<String, String> validatorResult = memberService.validateHandling(errors);
			for (String key : validatorResult.keySet()) {
				if (key.equals("valid_pwd") || key.equals("valid_pwdCheck")) {
					mv.addObject("valid_idCheck", "아이디 중복 확인을 해주세요.");
					mv.addObject(key, validatorResult.get(key));
				} else {
					mv.addObject(key, validatorResult.get(key));
				}
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

	// @GetMapping("/ShopMiniMall/product/{number}")
	// public ModelAndView getProductById(@PathVariable("number") int number,
	// ModelAndView mv, Cart cart) {
	// mv.setViewName("/ShopMiniMall/product");
	// mv.addObject("cart", cart);
	// mv.addObject("data", productService.getProductById(number));
	//
	// return mv;
	// }

	// @GetMapping("/ShopMiniMall/product/category/{category}")
	// public ModelAndView getProductByCategory(ModelAndView mv,
	// @PathVariable("category") String category) {
	// mv.setViewName("/ShopMiniMall/main :: resultProduct");
	// mv.addObject("data", productService.getProductByCategory(category));
	//
	// return mv;
	// }

	@GetMapping("/ShopMiniMall/cart")
	public ModelAndView getCart(ModelAndView mv, @AuthenticationPrincipal User userInfo) {
		mv.setViewName("/ShopMiniMall/cart");
		mv.addObject("data", cartService.getCart(userInfo.getUsername()));
		return mv;
	}

	@PostMapping("/ShopMiniMall/cart")
	public void insertCart(@AuthenticationPrincipal User userInfo, Random random, Cart cart) {
		cart.setCartNum(random.nextInt(1000000000));
		cart.setUserId(userInfo.getUsername());
		System.out.println(cart);
		cartService.postCart(cart);
	}

	@DeleteMapping("/ShopMiniMall/cart/{cartNum}")
	public ModelAndView deleteCart(ModelAndView mv, @AuthenticationPrincipal User userInfo,
			@PathVariable("cartNum") int cartNum) {
		cartService.deleteCart(cartNum);
		mv.setViewName("/ShopMiniMall/cart");
		mv.addObject("data", cartService.getCart(userInfo.getUsername()));

		return mv;
	}

	@DeleteMapping("/ShopMiniMall/cart")
	public void deleteCartById(ModelAndView mv, @AuthenticationPrincipal User userInfo) {
		cartService.deleteCartById(userInfo.getUsername());
	}

	@PutMapping("/ShopMiniMall/cart/{cartNum}")
	public @ResponseBody void updateCart(@RequestParam("amount") int amount, @PathVariable("cartNum") int cartNum) {
		cartService.putCartAmount(cartNum, amount);
	}

	@GetMapping("/ShopMiniMall/payConfirm")
	public ModelAndView getpayConfirm(ModelAndView mv, @AuthenticationPrincipal User userInfo) {
		mv.setViewName("/ShopMiniMall/payConfirm");
		mv.addObject("data", cartService.getCart(userInfo.getUsername()));
		return mv;
	}

	@GetMapping("/ShopMiniMall/mypage")
	public ModelAndView getmypage(ModelAndView mv, @AuthenticationPrincipal User userInfo, Member member) {
		mv.setViewName("/ShopMiniMall/mypage");
		mv.addObject("user", memberService.getMemberById(userInfo.getUsername()));
		mv.addObject("ref", member);
		return mv;
	}

	@PutMapping("/ShopMiniMall/member/{id}")
	public ModelAndView updateMember(ModelAndView mv, @AuthenticationPrincipal User userInfo, Member ref,
			@PathVariable("id") String id, Member member) {

		memberService.putMember(id, ref);

		mv.setViewName("/ShopMiniMall/mypage");
		mv.addObject("user", memberService.getMemberById(userInfo.getUsername()));
		mv.addObject("ref", member);
		return mv;
	}

	@DeleteMapping("/ShopMiniMall/cart/list/{cartNumList}")
	public void deleteCartByNumberList(@PathVariable("cartNumList") String cartNumList) {
		cartService.deleteCartByNumberList(cartNumList);
	}
}
