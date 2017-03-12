package com.college.service;

import com.college.domain.Course;
import com.college.util.Grade;
import com.college.util.Settlement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
     * @param courseid
     */
    public void modifyApplication(int valid, int courseid);


    /**
     * 先得到所有counted为FALSE的settleitem
     * 然后将settleitem按collegeid分类汇总变成settlement
     * 得到所有settlement项
     *
     * @return
     */
    public List<Settlement> showSettle();


    /**
     * 结算所有的counted=false
     * 把金额加入college账户
     * 然后把counted都置为true
     * @param semts
     */
    public void settle(List<Settlement> semts);

    /**
     * 得到各个机构招生的人数
     * @return map 第一个元素为机构的id， 第二个元素为学生人数
     */
    public Map<Integer, Integer> getStuNumOfCollege();

    /**
     * 得到各个成绩等级的学员的统计情况
     * @return map 第一个元素为成绩等级，第二个元素为学生人数
     */
    public Map<Grade, Integer> getStudySum();

    /**
     * 得到各个机构的收入，结算和未结算所有的
     * @return map 第一个元素为机构的id， 第二个元素为收入金额
     */
    public Map<Integer, Double> getFinanceSum();






}
