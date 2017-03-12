package com.college.service;

import com.college.domain.Log;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by G on 2017/3/12.
 */
@Component
public interface LogService {
    /**
     * 查询某人的统计信息
     * @param id
     * @return
     */
    public List<Log> showLog(int id);


}
