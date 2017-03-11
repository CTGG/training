package com.college.service;

import com.college.domain.Card;
import com.college.domain.Course;
import com.college.repository.CourseRepo;
import com.college.util.ResultMessage;
import com.college.util.ResultType;

import javax.annotation.Resource;
import java.sql.Date;

/**
 * Created by G on 2017/3/11.
 */
public class CourseServiceImpl implements CourseService{
    @Resource
    CourseRepo courseRepo;
    @Resource
    CardService cardService;
    @Resource
    ScoreService scoreService;

    @Override
    public ResultMessage orderCourse(int id, int courseid) {
        Course course = getCourse(courseid);
        double price = course.getPrice();
        Card card = cardService.getCard(id);
        double money = card.getMoney();

        if (price > money){
            return new ResultMessage(ResultType.FAIL,"余额不足，请充值");
        }

        cardService.pay(id, price);

        scoreService.registerStudent(id, courseid);
        return new ResultMessage(ResultType.SUCCESS, "订购课程成功");
    }

    @Override
    public void orderCourseByCash(int id, int courseid) {
        scoreService.registerStudent(id, courseid);
    }

    @Override
    public void cancelOrder(int id, int courseid) {
        Course course = getCourse(courseid);
        double price = course.getPrice();
        cardService.refund(id, price);

        dropCourse(id, courseid);
    }

    @Override
    public void dropCourse(int id, int courseid) {
        scoreService.deleteCourseItem(id, courseid);
    }

    @Override
    public void applyAddCourse(Course course) {
        //TODO check
        courseRepo.save(course);
    }

    @Override
    public ResultMessage modifyInfo(Course course) {
        int courseid = course.getId();
        Course oldcourse = courseRepo.findById(courseid);
        Date startdate = oldcourse.getStartdate();

        //TODO
        return null;
    }

    @Override
    public void scoreRegister(int courseid, int studentid, int score) {

    }

    private Course getCourse(int courseid){
        return courseRepo.findById(courseid);
    }

    private Course save(Course course){
        return courseRepo.save(course);
    }

}
