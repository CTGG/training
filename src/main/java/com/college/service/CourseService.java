package com.college.service;

import com.college.domain.Course;
import com.college.util.ResultMessage;

/**
 * Created by G on 2017/3/11.
 */
public interface CourseService {
    /**
     * 0. 查看该课程的价格
     *
     * 如果金额不足则失败，直接返回
     * 如果金额充足：
     * 1. 更新lastpay，扣除学生money，增加历史金额，看看level是否改变，active置为1
     * 2. course表中增加一条记录，学生的成绩默认为0
     *
     * @param id
     * @param courseid
     * @return
     */
    public ResultMessage orderCourse(int id, int courseid);

    /**
     *
     * course表中增加一条记录，学生的成绩默认为0
     *
     * @param id
     * @param courseid
     */
    public void orderCourseByCash(int id, int courseid);

    /**
     * 0. 查看该课程的价格
     *
     * 1. 原数增加学生money，减少历史金额，看看level是否改变
     * 2. course表中删去学生的课程记录
     * @param id
     * @param courseid
     */
    public void cancelOrder(int id, int courseid);



    /**
     * course表中删去学生的课程记录
     * @param id
     * @param courseid
     */
    public void dropCourse(int id, int courseid);


    /**
     * 增加一个开班申请，默认未批准
     * @param course
     */
    public void applyAddCourse(Course course);


    /**
     * 如果已经开班了，则不允许修改信息
     * 否则更新课程信息
     * @param course
     * @return
     */
    public ResultMessage modifyInfo(Course course);


    /**
     *
     * @param courseid
     * @param studentid
     * @param score
     */
    public void scoreRegister(int courseid, int studentid, int score);

}
