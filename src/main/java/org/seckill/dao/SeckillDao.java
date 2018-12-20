package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

import java.util.*;


public interface SeckillDao {

    /**
     * reduce the number
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     * query the seckill by id
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * query by offset and limit
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * use procedure to kill
     * @param paramMap
     */
    void killByProcedure(Map<String, Object> paramMap);
}
