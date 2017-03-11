package com.college.service;

import com.college.domain.Score;
import com.college.repository.ScoreRepo;

import javax.annotation.Resource;

/**
 * Created by G on 2017/3/11.
 */
public class ScoreServiceImpl implements ScoreService {
    @Resource
    ScoreRepo scoreRepo;

    @Override
    public void registerStudent(int id, int courseid) {
        Score score = new Score();
        score.setStudentid(id);
        score.setCourseid(courseid);
        scoreRepo.save(score);
    }

    @Override
    public void deleteCourseItem(int id, int courseid) {


        scoreRepo.findByCompositeId(id, courseid);
    }




}
