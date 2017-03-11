package com.college.service;

import org.springframework.stereotype.Component;

/**
 * Created by G on 2017/3/11.
 */
@Component
public interface ScoreService {
    /**
     * 把一个学生和所选课程加入score表，默认成绩为0
     *  @param id
     * @param courseid
     */
    public void registerStudent(int id, int courseid);


    /**
     * 把一个学生和他选的那门课删除
     * @param id
     * @param courseid
     */
    public void deleteCourseItem(int id, int courseid);


    /**
     * 登记分数
     * @param courseid
     * @param studentid
     * @param score
     */
    public void scoreRegister(int courseid, int studentid, int score);

}
