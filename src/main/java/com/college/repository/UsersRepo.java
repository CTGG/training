package com.college.repository;

import com.college.domain.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by G on 2017/3/10.
 */
public interface UsersRepo extends CrudRepository<Users,Long>{
    Users findById(int id);

    @Override
    Users save(Users users);


}
