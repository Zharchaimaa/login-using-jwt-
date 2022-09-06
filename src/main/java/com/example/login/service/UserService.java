package com.example.login.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.login.model.File;
import com.example.login.model.User;
import com.example.login.repository.FileRepository;
import com.example.login.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private FileRepository fileRepository;
	
	@Transactional(readOnly = true)
	public long getCountOfUsers() {
		long count = userRepository.count();
		//userRepository.findByRole(null)
		return count;
	}
	@Transactional
	public long getCountOfUserByRole(User user) {
		if ("ROLE_ADMIN".equals(user.getRole())) {
			  long count = userRepository.count();
			  return count;
		} else {
			long count2 = userRepository.count();
			return count2;
		}
		//return count;
	}
	
	@Transactional
	public List<User> listAll(){
		//Sort.by("email").ascending()
		return userRepository.findAll();
	}
	@Transactional
	public long getCountFileOk(){
		//Sort.by("email").ascending()
		return fileRepository.countFileOK();
	}
	@Transactional
	public long getValidName(){
		//Sort.by("email").ascending()
		return fileRepository.countVNOK();
	}
	@Transactional
	public long getValidNameKO(){
		//Sort.by("email").ascending()
		return fileRepository.countVNKO();
	}
	@Transactional
	public long getValidDateOK(){
		//Sort.by("email").ascending()
		return fileRepository.countVNKO();
	}
	@Transactional
	public long getValidDateKO(){
		//Sort.by("email").ascending()
		return fileRepository.countVNKO();
	}
	@Transactional
	public long getValidMontantOK(){
		//Sort.by("email").ascending()
		return fileRepository.countVNKO();
	}
	@Transactional
	public long getValidMontantKO(){
		//Sort.by("email").ascending()
		return fileRepository.countVNKO();
	}
	@Transactional
	public long getValidColumnKO(){
		//Sort.by("email").ascending()
		return fileRepository.countVNKO();
	}
	@Transactional
	public long getValidColumnOK(){
		//Sort.by("email").ascending()
		return fileRepository.countVNKO();
	}
}
