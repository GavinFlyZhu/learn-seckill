package org.seckill.dao;

import org.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;


public interface SeckillDao {

    /**
     * reduce the number
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNumber(long seckillId, Date killTime);

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
    List<Seckill> queryAll(int offset, int limit);

}
