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
public class BookUser {
	
	private String id;
	private String name;
	private String email;
	private int age;
	private LocalDate reg_date;
	private String password;

}