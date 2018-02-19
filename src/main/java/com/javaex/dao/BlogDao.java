package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CateVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlsession;

	public void createBlog(int userNo) {
		sqlsession.insert("blog.createBlog", userNo);
	}

	public void createCategory(int userNo) {
		sqlsession.insert("blog.createCategory", userNo);
		
	}

	public BlogVo getBlogInfo(int userNo) {
		return sqlsession.selectOne("blog.selectBlogInfoByUserNo", userNo);
		
	}

	public CateVo getCateInfo(int userNo) {
		return sqlsession.selectOne("blog.selectCateInfoByUserNo", userNo);
	}

	public void modifyBlog(BlogVo bvo) {
		sqlsession.update("blog.modifyBlog", bvo);
		
	}
}
