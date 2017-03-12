package com.college.repository;


import com.college.domain.SettleItem;
import com.college.domain.SettleItemId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by G on 2017/3/12.
 */
public interface SettleRepo extends CrudRepository<SettleItem, SettleItemId>{

    /**
     * 得到所有item
     * @return
     */
    List<SettleItem> findAll();

    /**
     * 得到某个机构所有待结算的item
     * @param collegeid
     * @return
     */
    List<SettleItem> findByCollegeid(int collegeid);



    @Query(value = "select s from com.college.domain.SettleItem as s where s.counted=false", nativeQuery=true)
    List<SettleItem> getNoCountedItems();
}
