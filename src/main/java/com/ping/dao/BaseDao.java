package com.ping.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ping.core.Page;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.util.Assert;

/**
 * DAO��Ļ��࣬��װ�˳��õ�DAO��������
 * @author ex
 */
public class BaseDao<E> {

	private Class<E> entityClass;
	
	@Autowired
	protected HibernateTemplate hibernateTemplate;
	
	/**
     * ͨ�������ȡ����ȷ��������
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseDao() {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class) params[0];
	}
	
	/**
     * ����ID����POʵ��
     * @param id
     * @return
     */
    public E load(Serializable id) {
        return (E) hibernateTemplate.load(entityClass, id);
    }

    public E get(Serializable id) {
        return (E) hibernateTemplate.get(entityClass, id);
    }

    /**
     * ���ӳټ��ص�ʵ��POִ�г�ʼ��
     * @param entity
     */
    public void initialize(Object entity) {
        hibernateTemplate.initialize(entity);
    }

    /**
     * ��ȡPO�����ж���
     * @return
     */
    public List<E> loadAll() {
        return hibernateTemplate.loadAll(entityClass);
    }

    /**
     * ����PO
     * @param entity
     */
    public void save(E entity) {
        hibernateTemplate.save(entity);
    }

    /**
     * ɾ��PO
     * @param entity
     */
    public void delete(E entity) {
        hibernateTemplate.delete(entity);
    }

    /**
     * ����PO
     * @param entity
     */
    public void update(E entity) {
        hibernateTemplate.update(entity);
    }

    /**
     * ִ��HQL��ѯ
     * @param hql
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<E> find(String hql) {
        return (List<E>) hibernateTemplate.find(hql);
    }

    /**
     * ִ�д�������HQL��ѯ
     * @param hql
     * @param params
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<E> find(String hql, Object... params) {
        return (List<E>) hibernateTemplate.find(hql, params);
    }

    /*------------------------------�ṩ���ݷ�ҳ֧��-----------------------------------------*/

    /**
     * ��ҳ��ѯ������ʹ��HQL
     * @param hql
     * @param pageNo
     * @param pageSize
     * @param values
     * @return
     */
    @SuppressWarnings("unchecked")
	public Page<E> pagedQuery(String hql, int pageNo, int pageSize, Object... values) {
        Assert.hasText(hql);
        Assert.isTrue(pageNo >= 1, "��ǰҳ��Ų���С�� 1");
        
        //Count��ѯ
        /*String countQueryString = "select count(*) " + removeSelect(removeOrderBy(hql));
        int totalCount = (int) hibernateTemplate.find(countQueryString, values).get(0);
        if (totalCount < 1) {
            return new Page<E>();
        }*/
        
        //ʵ�ʲ�ѯ���ط�ҳ����
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        
        //Query query = createQuery(removeSelect(removeOrderBy(hql)), values);
        //List<E> list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
        List<E> list = (List<E>) hibernateTemplate.find(removeSelect(removeOrderBy(hql)), values);
        return new Page<E>(pageNo, pageSize, startIndex, list);
    }

    /**
     * ����Query����
     * @param hql
     * @param values
     * @return
     */
    public Query createQuery(String hql, Object... values) {
        Assert.hasText(hql);
        
        Query query = getSession().createQuery(hql);
        if (values.length > 0) {
        	for (int i = 0; i < values.length; i++) {
        		query.setParameter(i, values[i]);
        	}
        }
        return query;
    }

    private Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

    /**
     * ȥ��hql��select�Ӿ�
     * @param hql
     * @return
     */
    private static String removeSelect(String hql) {
        Assert.hasText(hql);
        int beginPos = hql.toLowerCase().indexOf("from");
        Assert.isTrue(beginPos != -1, "hql:" + hql + "must has a keyword 'from'");
        return hql.substring(beginPos);
    }

    /**
     * ȥ��hql��orderby�Ӿ�
     * @param hql
     * @return
     */
    private static String removeOrderBy(String hql) {
        Assert.hasText(hql);
        Pattern pattern = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(hql);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "");
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}
