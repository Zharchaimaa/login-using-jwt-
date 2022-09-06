package com.example.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor  
public class ListUser {
	private Long id;
	private String username;
	private String email;
	private String role;
}
