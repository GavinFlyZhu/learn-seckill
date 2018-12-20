package org.seckill.service;


import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * business interface: stand on "user" perspective.
 * Three points: function size, parameters, return type (return, exception)
 */
public interface SeckillService {

    /**
     * query all seckill records
     *
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * query one seckill record
     *
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * seckill is started or not, return seckill url and time
     *
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * execute seckill operation
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException;

    /**
     * execute seckill by procedure
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5);
}
