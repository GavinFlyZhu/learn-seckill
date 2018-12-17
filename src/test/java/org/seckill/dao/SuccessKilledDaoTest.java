package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


// config spring and junit first. junit will load srpingIOC with this config
@RunWith(SpringJUnit4ClassRunner.class)
// config spring config xml path
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Test
    public void testInsertSuccessKilled() throws Exception{
        int insertedNumber = successKilledDao.insertSuccessKilled(1000L, 17322222821L);
        System.out.println(insertedNumber);
    }

    @Test
    public void testQueryByIdWithSeckill() throws Exception{
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(1000L, 17322222821L);
        System.out.println(successKilled.getSeckill());
    }

}