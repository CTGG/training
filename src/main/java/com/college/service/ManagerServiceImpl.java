package com.college.service;

import com.college.domain.*;
import com.college.repository.CourseRepo;
import com.college.repository.ScoreRepo;
import com.college.repository.SettleRepo;
import com.college.repository.UsersRepo;
import com.college.util.Grade;
import com.college.util.Settlement;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by G on 2017/3/12.
 */
public class ManagerServiceImpl implements ManagerService{
    @Resource
    CourseRepo courseRepo;
    @Resource
    SettleRepo settleRepo;
    @Resource
    CardService cardService;
    @Resource
    ScoreRepo scoreRepo;


    @Override
    public List<Course> getAllApplication() {
        return courseRepo.getNoValidCourse();
    }

    @Override
    public Course getApplicationById(int courseid) {
        return courseRepo.findById(courseid);
    }

    @Override
    public void modifyApplication(int valid, int courseid) {
        Course course = courseRepo.findById(courseid);
        if (valid == 0){
            courseRepo.delete(course);
        }else {
            course.setValid(true);
            courseRepo.save(course);
        }
    }

    @Override
    public List<Settlement> showSettle() {
        List<SettleItem> items = settleRepo.getNoCountedItems();
        List<Settlement> result = getSemtList(items);
        return result;
    }

    @Override
    public void settle(List<Settlement> semts) {
        for (Settlement semt: semts
             ) {
            //give money to college
            Card card = cardService.getCard(semt.getCollegeid());
            double money = card.getMoney();
            money += semt.getTotal();
            card.setMoney(money);
            cardService.save(card);

            //set counted = true
            List<SettleItem> items = semt.getList();
            for (SettleItem item: items
                 ) {
                item.setCounted(true);
                settleRepo.save(item);
            }
        }


    }

    @Override
    public Map<Integer, Integer> getStuNumOfCollege() {
        Map<Integer, Integer> numbers = new HashMap<>();
        List<Score> scores = scoreRepo.findAll();
        for (Score score: scores
             ) {
            int courseid = score.getCourseid();
            Course course = courseRepo.findById(courseid);
            int collegeid = course.getCollegeid();
            Set<Integer> keys = numbers.keySet();
            //if this college has been put in map, the add its value
            if (keys.contains(collegeid)){
                int count = numbers.get(collegeid);
                count++;
                numbers.put(collegeid, count);
            }else {
                numbers.put(collegeid, 1);
            }
        }
        return numbers;
    }

    @Override
    public Map<Grade, Integer> getStudySum() {
        List<Score> scores = scoreRepo.findAll();
        Map<Grade, Integer> map = new HashMap<>();
        map.put(Grade.A, 0);
        map.put(Grade.B, 0);
        map.put(Grade.C, 0);
        map.put(Grade.D, 0);

        for (Score s: scores
             ) {
            int score = s.getScore();
            if (score<=100 && score>=90){
                int number = map.get(Grade.A);
                number++;
                map.put(Grade.A, number);
            }else if (score>=80 && score<90){
                int number = map.get(Grade.B);
                number++;
                map.put(Grade.B, number);
            }else if (score>=60 && score<80){
                int number = map.get(Grade.C);
                number++;
                map.put(Grade.C, number);
            }else if (score>=0 && score<60){
                int number = map.get(Grade.D);
                number++;
                map.put(Grade.D, number);
            }
        }


        return map;
    }

    @Override
    public Map<Integer, Double> getFinanceSum() {
        Map<Integer, Double> map = new HashMap<>();
        List<SettleItem> items = settleRepo.getAllSettle();
        List<Settlement> result = getSemtList(items);
        for (Settlement semt: result
             ) {
            map.put(semt.getCollegeid(), semt.getTotal());
        }
        return map;
    }

    private List<Settlement> getSemtList(List<SettleItem> items){
        Set<Integer> collids = new HashSet<>();
        //get all collids
        for (SettleItem item: items
                ) {
            collids.add(item.getCollegeid());
        }

        List<Settlement> result = new ArrayList<>();
        //initiate each collid
        for (Integer collid: collids
                ) {
            result.add(new Settlement(collid));
        }

        //for each item, add item to the right settlement
        for (SettleItem item: items
                ) {
            for (Settlement setmt : result
                    ) {
                int itemid = item.getCollegeid();
                int semtid = setmt.getCollegeid();
                if (itemid == semtid){
                    setmt.addItem(item);
                }
            }
        }


        //set total
        for (Settlement semt:result
                ) {
            semt.setTotal();
        }

        return result;
    }
}



























