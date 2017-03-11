package com.college.service;

import com.college.domain.Card;
import com.college.domain.Course;
import com.college.domain.Score;
import com.college.repository.CourseRepo;
import com.college.util.ResultMessage;
import com.college.util.ResultType;
import com.college.util.TimeHelper;

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
    public ResultMessage applyAddCourse(Course course) {
        Date start = course.getStartdate();
        Date end = course.getEnddate();
        boolean isFuture = start.after(TimeHelper.getCurrentDate());
        boolean isValid = start.before(end);
        if (isFuture && isValid){
            courseRepo.save(course);
            return new ResultMessage(ResultType.SUCCESS, "开班申请已提交");
        }else if (!isFuture) {
            return new ResultMessage(ResultType.FAIL, "开班日期应该晚于今天");
        }else {
            return new ResultMessage(ResultType.FAIL,"起始日期应该早于结束日期");
        }

    }

    @Override
    public ResultMessage modifyInfo(Course course) {
        int courseid = course.getId();
        Course oldcourse = courseRepo.findById(courseid);
        Date startdate = oldcourse.getStartdate();

        if (startdate.before(TimeHelper.getCurrentDate())){
            return new ResultMessage(ResultType.FAIL, "该课程已开班，不可更改");
        }else {
            oldcourse.setCollegeid(course.getCollegeid());
            oldcourse.setName(course.getName());
            oldcourse.setStartdate(course.getStartdate());
            oldcourse.setEnddate(course.getEnddate());
            oldcourse.setTeacher(course.getTeacher());
            oldcourse.setPrice(course.getPrice());
            oldcourse.setValid(false);
            save(oldcourse);
            return new ResultMessage(ResultType.SUCCESS,"开班信息修改已发送总经理");
        }
    }



    private Course getCourse(int courseid){
        return courseRepo.findById(courseid);
    }

    private Course save(Course course){
        return courseRepo.save(course);
    }

}
