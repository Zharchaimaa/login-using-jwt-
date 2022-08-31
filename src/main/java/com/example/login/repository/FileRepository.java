package com.example.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.login.model.File;


@Repository
public interface FileRepository extends JpaRepository<File, Long>{
File findById(long id);
}
