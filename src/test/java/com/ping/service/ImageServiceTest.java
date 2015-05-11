package com.ping.service;

import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;

import java.util.Map;

/**
 * Created by ASUS on 2015/4/14.
 */
public class ImageServiceTest extends BaseServiceTest {
    @SpringBean("imageService")
    private ImageService imageService;

    @SpringBean("accountService")
    private AccountService accountService;

    /**
     * �ڲ��Գ�ʼ���У�����hibernate�������棬����Ӱ�����
     */
    @Before
    public void init() {
        SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
        Map<String, CollectionMetadata> roleMap = sessionFactory.getAllCollectionMetadata();
        for (String roleName : roleMap.keySet()) {
            sessionFactory.evictCollection(roleName);
        }
        Map<String, ClassMetadata> entityMap = sessionFactory.getAllClassMetadata();
        for (String entityName : entityMap.keySet()) {
            sessionFactory.evictEntity(entityName);
        }
        sessionFactory.evictQueries();
    }

    @Test
    @DataSet("pp.DataSet.xls")
    public void addImage() {
        /*Image image = XlsDataSetBeanFactory.createBean(ImageServiceTest.class
                "pp.DataSet.xls", "t_image", Image.class);
        imageService.addImage(image);
        Image imageDb = imageService.getImageById(image.getAccountId());
        Assert.assertThat(imageDb.getCatalogeId(), equals("xx"));*/
    }

}
