package org.iclass.mvc.dto;

import java.time.LocalDate;

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
public class CommunityComments {
	//필드,객체의 프로퍼티
	private int idx;
	private long mref;
	private String writer;
	private String content;
	private LocalDate createdAt;
	private String ip;
	private int heart;
	
}