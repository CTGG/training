package com.college.service;

import com.college.domain.Course;
import com.college.domain.Score;
import com.college.repository.CourseRepo;
import com.college.repository.LogRepo;
import com.college.repository.ScoreRepo;
import com.college.util.Type;

import javax.annotation.Resource;

/**
 * Created by G on 2017/3/11.
 */
public class ScoreServiceImpl implements ScoreService {
    @Resource
    ScoreRepo scoreRepo;
    @Resource
    LogRepo logRepo;
    @Resource
    CourseRepo courseRepo;

    @Override
    public void registerStudent(int id, int courseid) {
        Score score = new Score();
        score.setStudentid(id);
        score.setCourseid(courseid);
        save(score);
    }

    @Override
    public void deleteCourseItem(int id, int courseid) {
        Score score = scoreRepo.findByCompositeId(id, courseid);
        scoreRepo.delete(score);
    }


    @Override
    public void scoreRegister(int courseid, int studentid, int score) {
        Score item = new Score();
        item.setCourseid(courseid);
        item.setStudentid(studentid);
        item.setScore(score);
        save(item);
//        Course course = courseRepo.findById(courseid);
//        logRepo.save(studentid, "课程"+course.getName()+"得分", Type.MEMBER);
    }

    private Score save(Score score){
        return scoreRepo.save(score);
    }

}
