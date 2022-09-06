package com.example.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.login.model.File;
@Repository
public interface FileRepository extends JpaRepository<File, Long>{
File findById(long id);
@Query(value="select count(id) from File where valid_name='OK'")
long countVNOK();
@Query(value="select count(id) from File where valid_name='KO'")
long countVNKO();
@Query(value="select count(id) from File where valid_date='OK'")
long countVDOK();
@Query(value="select count(id) from File where valid_date='KO'")
long countVDKO();
@Query(value="select count(id) from File where valid_montant='OK'")
long countVMOK();
@Query(value="select count(id) from File where valid_montant='KO'")
long countVMKO();
@Query(value="select count(id) from File where valid_column='OK'")
long countVCOK();
@Query(value="select count(id) from File where valid_column='KO'")
long countVCKO();
@Query(value="select count(id) from File where valid_name='OK' and valid_date='OK' and valid_col='OK'and valid_montant='OK'")
long countFileOK();
}

