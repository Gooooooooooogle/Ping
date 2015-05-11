package com.ping.service;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

/**
 * ���������Ի���
 * @author eric
 */
@SpringApplicationContext({"pp-service.xml", "pp-dao.xml"})
public class BaseServiceTest extends UnitilsJUnit4 {

    @SpringBean("hibernateTemplate")
    public HibernateTemplate hibernateTemplate;

}
