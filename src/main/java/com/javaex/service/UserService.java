package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao uDao;
	@Autowired
	private BlogDao bDao;

	public UserVo login(String id, String password) {
		return uDao.getUser(id,password);
	}

	public void join(UserVo userVo) {
		int userNo = uDao.join(userVo);
		bDao.createBlog(userNo);
		bDao.createCategory(userNo);
		
	}

	public boolean idCheck(String id) {
		boolean result;
		
		UserVo uVo = uDao.getUser(id);
		
		if(uVo != null) {			//이미 id가 존재한다면 실패
			result = false;
		} else {
			result = true;
		}
		return result;
	}

	public int getUser(String userid) {
		UserVo uvo = uDao.getUser(userid);
		int getUserNo = uvo.getUserNo();
		
		return getUserNo;
	}
	
	

}
