package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.BlogService;
import com.javaex.vo.CateVo;
import com.javaex.vo.UserVo;

@Controller
public class ApiCateController {

	@Autowired
	private BlogService bService;
	
	@ResponseBody
	@RequestMapping(value="/admin/catelist", method=RequestMethod.POST)
	public List<CateVo> catelist(@RequestParam("userNo") int userNo ) {
		
		List<CateVo> clist = bService.allCateList(userNo);
		System.out.println(clist.toString());
		
		return clist;
	}
	
	@ResponseBody
	@RequestMapping(value="/admin/addcate", method=RequestMethod.POST)
	public CateVo addCate(@RequestBody CateVo cateVo) {

		bService.insertCate(cateVo);
		System.out.println(cateVo.toString());
		
		return cateVo;
	}
	
	@ResponseBody
	@RequestMapping(value="/admin/delete", method=RequestMethod.POST)
	public int delete(@RequestParam("cateNo") int cateNo ) {
		
		
		return bService.deleteCate(cateNo);
		
	}
}

