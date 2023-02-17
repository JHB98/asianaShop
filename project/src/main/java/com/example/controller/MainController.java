package com.example.controller;

import com.shop.project.service.MemberServiceImpl;
import com.shop.project.vo.Member;
import com.shop.project.vo.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	private final MemberServiceImpl memberService;

	public MainController(MemberServiceImpl memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/ShopMiniMall/main")
	public ModelAndView getMain(Member member, ModelAndView mv) {
		mv.setViewName("/ShopMiniMall/main");
		mv.addObject("data", memberService.getMember(member)); // 데이터 바꿔야함
		return mv;
	}

	// @RequestMapping("/ShopMiniMall/member")
	// public ModelAndView getMember(Member member, ModelAndView mv) {
	// mv.setViewName("/ShopMiniMall/member");
	// mv.addObject("data", memberService.getMember(member));
	// return mv;
	// }

	@GetMapping("/ShopMiniMall/member")
	public String getMember() {
		return "/ShopMiniMall/login2";
	}

	@GetMapping("/ShopMiniMall/MemberUIServlet")
	public String getMembershipForm(Model model, Member member) {
		model.addAttribute("member", member);
		return "/ShopMiniMall/memberShip";
	}

	// TODO: PostMapping 으로 변경해야될듯
	@GetMapping("/ShopMiniMall/membership")
	public ModelAndView insertMember(ModelAndView mv, Member member) {
		memberService.postMember(member);
		mv.addObject("data", new Message("회원가입이 완료되었습니다.", "/ShopMiniMall/main"));
		mv.setViewName("/ShopMiniMall/Message");

		return mv;
	}

	@PostMapping("/ShopMiniMall/login")
	public String login(Member member) {
		return "/ShopMiniMall/memberShip";
	}
}
