package com.college.repository;

import com.college.domain.Score;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by G on 2017/3/11.
 */
public interface ScoreRepo extends CrudRepository<Score,Long> {

    public Score save(Score score);

    public Score findByCompositeId(int id, int courseid);
}
