package com.example.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.login.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
	void deleteById(int id);
	Message findById(long id);
}
