package org.iclass.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.mvc.dto.Community;
import org.iclass.mvc.dto.PageRequestDTO;

@Mapper
public interface CommunityMapper {
	//글목록 페이징 - 검색기능으로 수정합니다.
	List<Community> pagelist(PageRequestDTO pageRequestDTO);
	
	int count(PageRequestDTO pageRequestDTO);
	
	//글 수정,글 읽기
	Community selectByIdx(long idx);
	
	//글 읽기 
	void setReadCount(long idx);
	
	//글 쓰기
	int insert(Community vo);
	
	//글 수정
	int update(Community vo);
	
	//글 삭제
	int delete(long idx);
}
