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
import com.asiana.mall.service.PurchaseServiceImpl;
import com.asiana.mall.vo.Member;
import com.asiana.mall.vo.Message;
import com.asiana.mall.vo.Cart;
import com.asiana.mall.vo.InfoList;
import java.util.Map;
import java.util.Random;

@Controller
public class MainController {

	private final MemberServiceImpl memberService;
	private final CartServiceImpl cartService;
	private final PurchaseServiceImpl purchaseService;

	public MainController(MemberServiceImpl memberService, CartServiceImpl cartService,
			PurchaseServiceImpl purchaseService) {
		this.memberService = memberService;
		this.cartService = cartService;
		this.purchaseService = purchaseService;
	}

	public String encodeBcrypt(String pwd) {
		return new BCryptPasswordEncoder().encode(pwd);
	}

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
			// ????????? ?????? ?????? ????????? ???????????? ?????????
			Map<String, String> validatorResult = memberService.validateHandling(errors);
			for (String key : validatorResult.keySet()) {
				if (key.equals("valid_pwd") || key.equals("valid_pwdCheck")) {
					mv.addObject("valid_idCheck", "????????? ?????? ????????? ????????????.");
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
		mv.addObject("data", new Message("??????????????? ?????????????????????.", "/ShopMiniMall/main"));
		mv.setViewName("/ShopMiniMall/Message");

		return mv;
	}

	@GetMapping("/ShopMiniMall/cart")
	public ModelAndView getCart(ModelAndView mv, @AuthenticationPrincipal User userInfo, InfoList list) {
		mv.setViewName("/ShopMiniMall/cart");
		mv.addObject("data", cartService.getCart(userInfo.getUsername()));
		mv.addObject("list", list);
		return mv;
	}

	@PostMapping("/ShopMiniMall/cart")
	public ModelAndView insertCart(ModelAndView mv, @AuthenticationPrincipal User userInfo, Random random, Cart cart) {
		cart.setCartNum(random.nextInt(1000000000));
		cart.setUserId(userInfo.getUsername());

		cartService.postCart(cart);
		mv.addObject("data", new Message("???????????? ????????? ?????????????????????.", "/ShopMiniMall/main"));
		mv.setViewName("/ShopMiniMall/Message");
		return mv;
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

	@GetMapping("/ShopMiniMall/mypage")
	public ModelAndView getmypage(ModelAndView mv, @AuthenticationPrincipal User userInfo, Member member) {
		mv.setViewName("/ShopMiniMall/mypage");
		mv.addObject("user", memberService.getMemberById(userInfo.getUsername()));
		mv.addObject("ref", member);
		mv.addObject("purchase", purchaseService.getPurchase(userInfo.getUsername()));
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

	@DeleteMapping("/ShopMiniMall/cart/list")
	public ModelAndView deleteCartByNumberList(ModelAndView mv, @AuthenticationPrincipal User userInfo, InfoList list,
			InfoList refList) {
		cartService.deleteCartByNumberList(list.getList());
		mv.setViewName("/ShopMiniMall/cart");
		mv.addObject("data", cartService.getCart(userInfo.getUsername()));
		mv.addObject("list", refList);
		return mv;
	}
}