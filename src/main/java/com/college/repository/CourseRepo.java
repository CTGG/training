package com.college.repository;

import com.college.domain.Course;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by G on 2017/3/11.
 */
public interface CourseRepo extends CrudRepository<Course,Long> {
    Course findById(int courseid);
}
