package org.seckill.dao;

import org.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {

    /**
     * insert kill detail, could filter duplicated items
     * @param seckillId
     * @param userPhone
     * @return
     * the row number was inserted
     */
    int insertSuccessKilled(long seckillId, long userPhone);

    /**
     * query successKilled by id, with Seckill returned
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(long seckillId);
}
