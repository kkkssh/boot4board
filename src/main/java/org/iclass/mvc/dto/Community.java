package org.iclass.mvc.dto;

import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Community {
	//필드,객체의 프로퍼티
	private long r;
	private long idx;
	private String writer;
	private String title;
	private String content;
	private int readCount;
	private Timestamp createdAt;
	private String ip;
	private String commentCount;

}