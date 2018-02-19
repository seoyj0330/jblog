package com.javaex.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlsession;

	public UserVo getUser(String id, String password) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("password", password);
		
		return sqlsession.selectOne("user.loginByidpw", map);
	}

	public int join(UserVo userVo) {
		System.out.println(userVo.getUserNo()); //비어있는 숫자 0 이들어있을꺼임
		sqlsession.insert("user.join", userVo); //userVo.No 에는 이제 숫자가 들어가있음
		int userNo = userVo.getUserNo();
		return userNo;
		
	}

	public UserVo getUser(String id) {
		
		return sqlsession.selectOne("user.selectById", id);
	}


}
