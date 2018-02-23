package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CateVo;
import com.javaex.vo.PostVo;

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

	public List<CateVo> cateList(int userNo) {
		
		List<CateVo> list = sqlsession.selectList("blog.allCateList", userNo);
		
//		System.out.println(list.size());
//		
//		for(CateVo cate : list) {
//			System.out.println(cate.getCateName());
//		}
		
		return list;
	}

	public CateVo insertcate(CateVo cateVo) {
	
		System.out.println("cateno값 받아오기전  : " + cateVo.getCateNo());		//no받아오기 전 (값없음)
		sqlsession.insert("blog.addCate",cateVo);
		
		int cateno = cateVo.getCateNo();		//값이 들어온 상태
		System.out.println("값이 들어온 후  : " + cateno);
		
		return cateVo;
	}

	public int deleteCate(int cateNo) {
		return sqlsession.delete("blog.deleteCate", cateNo);
	}

	public void writePost(PostVo postVo) {
		System.out.println(postVo);
		sqlsession.insert("blog.writePost", postVo);
	}
}
