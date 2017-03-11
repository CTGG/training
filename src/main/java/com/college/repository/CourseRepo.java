package com.college.repository;

import com.college.domain.Course;
import com.college.domain.Users;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by G on 2017/3/11.
 */
public interface CourseRepo extends CrudRepository<Course,Long> {


}
