package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;

@Controller
public class ApiUserController {
	
	@Autowired
	private UserService uService;
	
	@ResponseBody
	@RequestMapping(value="/user/api/id", method=RequestMethod.POST)
	public boolean emailCheck(@RequestParam("id") String id) {
		
		System.out.println(id);
		boolean result = uService.idCheck(id);
		System.out.println(result);
		
		return result;
	}

}
