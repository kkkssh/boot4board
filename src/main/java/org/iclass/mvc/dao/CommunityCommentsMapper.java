package org.iclass.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.mvc.dto.CommunityComments;
@Mapper
public interface CommunityCommentsMapper {
	
	int insert(CommunityComments vo);
	int delete(long idx);
	int maxOf();
	List<CommunityComments> commentsList(long idx);
	int setCommentCount(long idx);
	
}
