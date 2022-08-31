package com.example.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.login.model.Todolist;
@Repository
public interface TodoRepository extends JpaRepository<Todolist, Long>{
Todolist findById(long id);
void deleteById(int id);
}
