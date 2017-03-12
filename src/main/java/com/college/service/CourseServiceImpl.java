package com.college.service;

import com.college.domain.*;
import com.college.repository.CourseRepo;
import com.college.repository.LogRepo;
import com.college.repository.SettleRepo;
import com.college.util.ResultMessage;
import com.college.util.ResultType;
import com.college.util.TimeHelper;
import com.college.util.Type;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    @Resource
    LogRepo logRepo;
    @Resource
    SettleRepo settleRepo;

    @Override
    public ResultMessage orderCourse(int id, int courseid) {
        Course course = getCourse(courseid);
        double price = course.getPrice();
        Card card = cardService.getCard(id);
        double money = card.getMoney();

        if (price > money){
            return new ResultMessage(ResultType.FAIL,"余额不足，请充值");
        }

        //得到折扣后的价格
        Card temp = cardService.getCard(id);
        int level = temp.getLevel();
        price -= level * 0.1 * price;

        Log log = new Log(id, "预定课程"+course.getName(), Type.MEMBER);
        logRepo.save(log);



        cardService.pay(id, price);

        Log log1 = new Log(id, "支付"+price+"元", Type.MEMBER);
        logRepo.save(log1);
        Log log2 = new Log(course.getCollegeid(), course.getName()+"被学生"+id+"订购，收入"+price+"元", Type.COLLEGE);
        logRepo.save(log2);

        //存入会员卡支付内容，便于结算以及退款
        SettleItem settleItem = new SettleItem();
        settleItem.setStudentid(id);
        settleItem.setCollegeid(course.getCollegeid());
        settleItem.setCourseid(courseid);
        settleItem.setMoney(price);
        settleItem.setCounted(false);
        settleRepo.save(settleItem);

        scoreService.registerStudent(id, courseid);
        return new ResultMessage(ResultType.SUCCESS, "订购课程成功");
    }

    @Override
    public void orderCourseByCash(int id, int courseid) {
        Course course = getCourse(courseid);
        double price = course.getPrice();
        Log log = new Log(id, "订购课程"+course.getName()+"，支付现金"+price+"元", Type.MEMBER);
        logRepo.save(log);
        Log log1 = new Log(course.getCollegeid(), course.getName()+"被学生"+id+"订购，收取现金"+price+"元", Type.COLLEGE);
        logRepo.save(log1);
        scoreService.registerStudent(id, courseid);
    }

    @Override
    public void cancelOrder(int id, int courseid) {
        Course course = getCourse(courseid);
        Log log = new Log(id, "取消预定课程"+course.getName(), Type.MEMBER);
        logRepo.save(log);
        //从settleitem中得到price，并将它删除

        
        SettleItemId sid = new SettleItemId();
        sid.setCourseid(courseid);
        sid.setStudentid(id);
        SettleItem item = settleRepo.findOne(sid);

        double price = item.getMoney();
        cardService.refund(id, price);

        Log log1 = new Log(id, "收到退款"+price+"元", Type.MEMBER);
        logRepo.save(log1);
        Log log2 = new Log(course.getCollegeid(), course.getName()+"被学生"+id+"退订，支出"+price+"元", Type.COLLEGE);
        logRepo.save(log2);
        scoreService.deleteCourseItem(id, courseid);

        settleRepo.delete(item);
    }

    @Override
    public void dropCourse(int id, int courseid) {
        Course course = getCourse(courseid);
        Log log = new Log(id, "退出课程"+course.getName(), Type.MEMBER);
        logRepo.save(log);
        Log log1 = new Log(course.getCollegeid(), "学生"+id+"退出课程"+course.getName(), Type.COLLEGE);
        logRepo.save(log1);
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

    @Override
    public List<Course> getAvailableCourses(int id) {
        //TODO whether cast works well
        List<Course> courses = (List<Course>) courseRepo.findAll();
        List<Score> scores = scoreService.getMyScores(id);
        List<Integer> alreadyids = new ArrayList<>();
        for (Score s:scores
             ) {
            alreadyids.add(s.getCourseid());
        }

        for (Course course:courses
             ) {
            Date enddate = course.getEnddate();
            Date nowdate = TimeHelper.getCurrentDate();
            //TODO int & integer
            int courseid = course.getId();
            if (enddate.before(nowdate) || alreadyids.contains(courseid)){
                courses.remove(course);
            }
        }
        return courses;
    }

    @Override
    public List<Course> getMyCourse(int id) {
        List<Score> scores = scoreService.getMyScores(id);
        List<Course> courses = new ArrayList<>();
        for (Score s: scores
             ) {
            int courseid = s.getCourseid();
            Course course = getCourseById(courseid);
            courses.add(course);
        }
        return courses;
    }

    @Override
    public Course getCourseById(int courseid) {
        return courseRepo.findById(courseid);
    }

    @Override
    public List<Course> getMyApplications(int collegeid) {
        return courseRepo.findByCollegeid(collegeid);
    }


    private Course getCourse(int courseid){
        return courseRepo.findById(courseid);
    }

    private Course save(Course course){
        return courseRepo.save(course);
    }

}
