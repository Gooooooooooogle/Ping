package com.ping.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ��ҳ���󣬰�����ǰҳ���ݼ���ҳ��Ϣ
 * @author ex
 */
public class Page<E> implements Serializable {
    private static final long serialVersionUID = 2667671026602298301L;
    public static final int DEFAULT_PAGE_SIZE = 12;

    private int pageNo;
    private int pageSize;
    /*
     * ��ǰҳ��һ��������list�е�λ�ã���0��ʼ
     */
    private int start;
    /*
     * ��ǰҳ�ļ�¼
     */
    private List<E> data;

    /*
     * �ܼ�¼��
     */
    private long totalCount;

    public Page() {
        this(0, DEFAULT_PAGE_SIZE, 0, new ArrayList<E>());
    }

    public Page(int pageNo, int pageSize, int start, List<E> list) {
        super();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.start = start;
        this.data = list;
    }

    /**
     * ��ȡ��һҳ��һ�����������ݼ��е�λ�ã��Զ���ָ��ÿҳ����
     * @param pageNo
     * @param pageSize
     * @return
     */
    public static int getStartOfPage(int pageNo, int pageSize) {
        return (pageNo - 1) * pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}

