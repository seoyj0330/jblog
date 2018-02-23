package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.service.UserService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CateVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UserVo;

@Controller
public class BlogController {

	@Autowired
	private BlogService bService;	

	@Autowired
	private UserService uService;
	
	@RequestMapping(value="/{userId}")
	public String main(@PathVariable("userId") String userid, Model model) {
		System.out.println("b.main진입");
		
		int userno = uService.getUser(userid);
		System.out.println("userId : " + userno);
		
		BlogVo blogVo = bService.getBlogInfo(userno);
		
		System.out.println(blogVo);
		model.addAttribute("blogVo", blogVo);
		
		List<CateVo> clist = bService.allCateList(userno);
		model.addAttribute("clist", clist);
		
		return "blog/blog-main";
	}
	
	@RequestMapping(value="/{userId}/admin/basic")
	public String basic(@PathVariable("userId") String userid, HttpSession session,BlogVo blogVo, CateVo cateVo, Model model
						/*@RequestParam(value="logo-file", required=false, defaultValue="") MultipartFile file*/) {
		
		System.out.println("b.basic진입");
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		blogVo = bService.getBlogInfo(authUser.getUserNo());
		cateVo = bService.getCateInfo(blogVo.getUserNo());
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("cateVo", cateVo);
		
		/*System.out.println(file.toString());
		String saveName= fService.restore(file);
		
		String url ="upload/" + saveName;			
		model.addAttribute("url", url);
		*/
		
		return "blog/admin/blog-admin-basic";
		}
	
	@RequestMapping(value="/{userId}/admin/modify", method=RequestMethod.POST)
	public String modify(@PathVariable("userId") String userid,@RequestParam(value="logo-file", required=false, defaultValue="") MultipartFile logoFile, Model model,
						 @RequestParam(value="title", required=false, defaultValue="") String blogTitle, HttpSession session) {
		
		System.out.println("modify 진입");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int userNo= authUser.getUserNo();
		
		bService.modifyBlog(logoFile, blogTitle, userNo);
		System.out.println(logoFile.toString());
		
		
		return "redirect:/{userId}";
	}
	
	@RequestMapping(value="/{userId}/admin/category")
	public String writeCate(@PathVariable("userId") String userid,HttpSession session, Model model) {
		
		System.out.println("b.category 진입");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		BlogVo bvo = bService.getBlogInfo(authUser.getUserNo());
		model.addAttribute("blogVo", bvo);
		
		return "blog/admin/blog-admin-cate";
	}
	
	@RequestMapping(value="/{userId}/admin/write")
	public String post(@PathVariable("userId") String userid, HttpSession session, CateVo catevo, Model model ) {
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		BlogVo bvo = bService.getBlogInfo(authUser.getUserNo());
		model.addAttribute("blogVo", bvo);
		
		int userNo = authUser.getUserNo();

		//카테고리 리스트 뿌려주기
		List<CateVo> clist = bService.allCateList(userNo);
		model.addAttribute("clist", clist);
		//userNo으로 받아 model에 넣어서 jsp로 넘겨주기

		return "blog/admin/blog-admin-write";
	}
	
	@RequestMapping(value="/{userId}/admin/writepost", method=RequestMethod.POST)
	public String writePost(@PathVariable("userId") String userid, HttpSession session,PostVo postVo ) {
		
		System.out.println("writepost진입");
		System.out.println(postVo);
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser.getId().equals(userid)) {
			
			bService.writePost(postVo);
			System.out.println("적은 글 : " + postVo.toString());
			
			return "redirect:/{userId}";
		} else {
			
			return"redirect:/user/joinform";
		}
		
	}

}
