package com.college.repository;

import com.college.domain.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by G on 2017/3/11.
 */
public interface CourseRepo extends CrudRepository<Course,Long> {
    Course findById(int courseid);


    //TODO
    @Query(value = "select c from com.college.domain.Course c where c.valid = false", nativeQuery=true)
    List<Course> getNoValidCourse();


    List<Course> findByCollegeid(int collegeid);
}
