package com.college.service;

import com.college.domain.Log;
import com.college.repository.LogRepo;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by G on 2017/3/12.
 */
public class LogServiceImpl implements LogService{
    @Resource
    LogRepo logRepo;
    @Override
    public List<Log> showLog(int id) {
        return logRepo.findById(id);
    }
}
