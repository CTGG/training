package com.college.repository;


import com.college.domain.SettleItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by G on 2017/3/12.
 */
public interface SettleRepo extends CrudRepository<SettleItem, Long>{

    /**
     * 得到所有item
     * @return
     */
    List<SettleItem> getAllSettle();

    /**
     * 得到某个机构所有待结算的item
     * @param collegeid
     * @return
     */
    List<SettleItem> getCollegeAllSettle(int collegeid);

    SettleItem fingByKey(int studentid, int courseid);


    @Query(value = "select s from com.college.domain.SettleItem as s where s.counted=false", nativeQuery=true)
    List<SettleItem> getNoCountedItems();
}
