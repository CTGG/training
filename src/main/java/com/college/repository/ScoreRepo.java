package com.college.repository;

import com.college.domain.Score;
import com.college.domain.SettleItemId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by G on 2017/3/11.
 */
public interface ScoreRepo extends CrudRepository<Score,SettleItemId> {

    public Score save(Score score);

    public Score findByStudentidAndCourseid(int id, int courseid);

    public List<Score> findByStudentid(int studentid);

    public List<Score> findAll();
}
