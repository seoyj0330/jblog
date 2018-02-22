package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CateVo;
import com.javaex.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao bdao;

	public BlogVo getBlogInfo(int userNo) {
		return bdao.getBlogInfo(userNo);
		
	}

	public CateVo getCateInfo(int userNo) {
		// TODO Auto-generated method stub
		return bdao.getCateInfo(userNo);
	}
	
	public String modifyBlog(MultipartFile logoFile, String blogTitle, int userNo) {
		String saveDir = "D:\\javaStudy\\upload";
		
		BlogVo bvo = new BlogVo();
		//파일정보 수집
		
		
		//원래파일이름
		String orgName = logoFile.getOriginalFilename();
		System.out.println(orgName);
		
		//확장자
		String exName = logoFile.getOriginalFilename().substring(logoFile.getOriginalFilename().lastIndexOf("."));
		System.out.println(exName);
		
		//저장파일이름		겹치면 덮어씌워버리니까 주의
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString()+exName;
		System.out.println(saveName);
		
		//파일저장위치(path)
		String filePath = saveDir + "\\" + saveName;
		System.out.println(filePath);
		
		//파일사이즈
		long fileSize = logoFile.getSize();
		System.out.println(fileSize);
		
		//vo만들어서 값 넣어주기		//파일 저장할때 DB저장하기위해서
		bvo.setBlogTitle(blogTitle);
		bvo.setLogoFile(saveName);
		bvo.setUserNo(userNo);
		
		
		//파일카피
		try {
			
			byte[] fileData = logoFile.getBytes();
			OutputStream out = new FileOutputStream(saveDir+"/"+saveName);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileData);
			
			if(bout != null) {
				bout.close();
			}
			
		} catch(IOException e) {
			
			e.printStackTrace();
		}
		
		//DB 저장
		bdao.modifyBlog(bvo);
		
		return saveName;
	}

	public List<CateVo> allCateList(int userNo) {
		
		return bdao.cateList(userNo);
	}

	public CateVo insertCate(CateVo cateVo) {
		
		bdao.insertcate(cateVo);
		
		return cateVo;
	}

	public int deleteCate(int cateNo) {
		return bdao.deleteCate(cateNo);
	}

	public void writePost(PostVo postVo) {
		bdao.writePost(postVo);
		
	}

	
}
