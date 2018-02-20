package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserService uService;

	
	@RequestMapping(value="/loginform", method=RequestMethod.GET)
	public String loginform() {
		return "user/loginForm";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam("id") String id, @RequestParam("password") String password, HttpSession session) {
		
		System.out.println("user.login진입");
		
		UserVo authUser = uService.login(id,password);
		
		if(authUser == null) {
			return "redirect:/user/loginform?result=fail";
		} else {
			session.setAttribute("authUser", authUser);
			return "main/index";
		}
	}
	
	@RequestMapping(value="/joinform", method=RequestMethod.GET)
	public String joinform() {
		return "user/joinForm";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("user.join 진입");
		System.out.println(userVo.toString());
		
		uService.join(userVo);
		
		return "user/joinSuccess";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
		
	
}
