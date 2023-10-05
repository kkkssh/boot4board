package org.iclass.mvc.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.mvc.dto.BookUser;
@Mapper
public interface BookUserMapper {
	
	int insert(BookUser dto);
	BookUser login(Map<String, String> map);
}
