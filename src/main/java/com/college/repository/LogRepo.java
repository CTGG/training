package com.college.repository;

import com.college.domain.Log;
import com.college.util.Type;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by G on 2017/3/12.
 */
public interface LogRepo extends CrudRepository<Log, Long> {
    public void save(int opeid, String operation, Type type);
}
