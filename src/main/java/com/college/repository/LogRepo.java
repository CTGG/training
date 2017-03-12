package com.college.repository;

import com.college.domain.Log;
import com.college.domain.LogId;
import com.college.util.Type;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by G on 2017/3/12.
 */
public interface LogRepo extends CrudRepository<Log, LogId> {
//    public void save(int id, String operation, Type type);

    public List<Log> findById(int id);
}
