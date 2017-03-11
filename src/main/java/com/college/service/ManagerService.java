package com.college.service;

import com.college.domain.Card;
import com.college.domain.Course;
import com.college.domain.Score;
import com.college.util.Settlement;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by G on 2017/3/11.
 */
@Component
public interface ManagerService {
    /**
     * 得到所有未审批的申请
     * @return
     */
    public List<Course> getAllApplication();

    /**
     * 得到某个特定的开班申请
     * @param courseid
     * @return
     */
    public Course getApplicationById(int courseid);


    /**
     * 审批开班申请，如果传入的值为0，则将该申请从表中清除
     * @param valid
     */
    public void modifyApplication(int valid);

    /**
     * 将会员卡支付结算给各机构
     * 退课的人返回钱款
     */
    public List<Settlement> settle();




}
