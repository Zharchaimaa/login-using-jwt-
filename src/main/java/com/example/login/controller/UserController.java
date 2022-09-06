package com.example.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.model.File;
import com.example.login.model.Message;
import com.example.login.repository.FileRepository;
import com.example.login.repository.MessageRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserController {
	@Autowired
	FileRepository fileRepository;
	
	@Autowired
	MessageRepository messageRepository;
	
	// for show details
		@GetMapping("/getAllFileById/{id}")
		public File getAllFileById(@PathVariable(name = "id") long id) {
			return fileRepository.findById(id);
		}
		// list of files
			@GetMapping("/getAllFiles")
			public List<File> getAllFiles() {
				return fileRepository.findAll();
			}
			
			@PostMapping("/addMessage")
			public Message addMessage(@RequestBody Message msg) {
				return messageRepository.save(msg);
			}
}
