package com.ping.core.util;

import com.ping.domain.Image;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by ASUS on 2015/5/16.
 */
public class SolrUtilTest {
    private static String url = "http://localhost:8788/solr/db";
    private SolrClient client;

    @Before
    public void init() {
        client = new HttpSolrClient(url);
    }

    @After
    public void destroy() {
        client = null;
    }

    // ���ĵ�����
    public void addDoc() throws Exception {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", 10000001);
        doc.addField("name", "Solr Input Document");
        doc.addField("manu", "this is SolrInputDocument content");
        client.add(doc);
        client.commit();
    }

    // �����ĵ�����
    public void addDocList() throws Exception {
        Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        for (int i = 1; i < 11; i++) {
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("id", 1000000 + i);
            doc.addField("name", "batch solr document insert " + i);
            doc.addField("manu", "batch solr document content insert " + i);
            docs.add(doc);
        }
        client.add(docs);
        client.commit();
    }

    // �����ĵ���Ӧ�� Java Bean ����
    public void addDocBean() throws Exception {
        Image doc = new Image();
        doc.setImageId("555");
        client.addBean(doc);
        client.commit();
    }

    // �����ĵ� Bean ����
    public void addDocBeanList() throws Exception {
        Collection<Image> docs = new ArrayList<Image>();
        for (int i = 1; i < 11; i++) {
            Image doc = new Image();
            doc.setImageId("555");
            docs.add(doc);
        }
        client.addBeans(docs);
        client.commit();
    }

    // ���� id ɾ�������ĵ�
    public void remove() throws Exception {
        client.deleteById("100000000");
        client.commit();
    }

    // �������� id ɾ���ĵ�
    public void removeList() throws Exception {
        List<String> ids = new ArrayList<String>();
        for (int i = 1; i < 6; i++) {
            ids.add("10000000" + i);
        }
        client.deleteById(ids);
        client.commit();
    }

    // ���ݲ�ѯ����ɾ���ĵ�
    public void removeByQuery() throws Exception {
        client.deleteByQuery("id:100000007");
        client.commit();
    }

    // ��ѯ����
    public void queryAll() throws Exception {
        SolrQuery query = new SolrQuery();
        query.set("q", "*:*");
        query.setStart(0);
        query.setRows(Integer.MAX_VALUE);
        // query.set("start", 0);
        // query.set("row", Integer.MAX_VALUE);
        query.set("image_id", "image_id desc");
        query.set("fl", "image_id, image_title");
        QueryResponse response = client.query(query);
        SolrDocumentList docs = response.getResults();
        for (SolrDocument doc : docs) {
            log(doc);
        }
    }
    // һЩ��������
    public void otherMethod() throws Exception {
        client.getBinder();
        client.optimize(); // �ϲ������ļ��������Ż��������ṩ���ܣ�����Ҫһ����ʱ��
        client.ping(); // ping�������Ƿ����ӳɹ�
        client.rollback();
        client.commit();
    }

    @Test
    // query �����÷�����
    public void queryCase() {
        // AND ����
        SolrQuery params = new SolrQuery("2");
        // OR ����
        //params.setQuery("image_username:taylor swift OR image_hot:2");
        // �ո� ��ͬ�� OR
        //params.setQuery("name:server manu:dell");
        // params.setQuery("name:solr - manu:inc");
        // params.setQuery("name:server + manu:dell");
        // ��ѯname����solr apple
        //params.setQuery("name:solr,apple");
        // manu������inc
        //params.setQuery("name:solr,apple NOT manu:inc");
        // 50 <= price <= 200
        //params.setQuery("price:[50 TO 200]");
        //params.setQuery("popularity:[5 TO 6]");
        // 50 <= price <= 200 AND 5 <= popularity <= 6
        //params.setQuery("price:[50 TO 200] AND popularity:[5 TO 6]");
        //params.setQuery("price:[50 TO 200] OR popularity:[5 TO 6]");
        // ��������ѯ������������� filter ���ƶ��������ϣ���and
        // params.addFilterQuery("id:VA902B");
        // params.addFilterQuery("price:[50 TO 200]");
        // params.addFilterQuery("popularity:[* TO 5]");
        // params.addFilterQuery("weight:*");
        // 0 < popularity < 6 û�е���
        // params.addFilterQuery("popularity:{0 TO 6}");
        // ����
        params.addSort("image_id", SolrQuery.ORDER.asc);
        // ��ҳ��start��ʼҳ��rowsÿҳ��ʾ��¼����
        // params.add("start", "0");
        // params.add("rows", "200");
        // params.setStart(0);
        // params.setRows(200);
        // ���ø���
        params.setHighlight(true); // �����������
        params.addHighlightField("image_username");// �����ֶ�
        params.setHighlightSimplePre("<font color='red'>");// ��ǣ������ؼ���ǰ׺
        params.setHighlightSimplePost("</font>");// ��׺
        params.setHighlightSnippets(1);// �����Ƭ����Ĭ��Ϊ1
        params.setHighlightFragsize(1000);// ÿ����Ƭ����󳤶ȣ�Ĭ��Ϊ100
        // ��Ƭ��Ϣ
        params.setFacet(true).setFacetMinCount(1).setFacetLimit(5)// ��
                .addFacetField("image_username")// ��Ƭ�ֶ�
                .addFacetField("image_title");
        // params.setQueryType("");
        try {
            QueryResponse response = client.query(params);
            /*
             * List<Index> indexs = response.getBeans(Index.class); for (int i =
             * 0; i < indexs.size(); i++) { fail(indexs.get(i)); }
             */
            // �����ѯ�����
            SolrDocumentList list = response.getResults();
            log("query result nums: " + list.getNumFound());
            for (int i = 0; i < list.size(); i++) {
                log(list.get(i));
            }
            // �����Ƭ��Ϣ
            List<FacetField> facets = response.getFacetFields();
            for (FacetField facet : facets) {
                log(facet);
                List<FacetField.Count> facetCounts = facet.getValues();
                for (FacetField.Count count : facetCounts) {
                    System.out.println(count.getName() + ": "
                            + count.getCount());
                }
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    // ��Ƭ��ѯ�� ����ͳ�ƹؼ��ּ����ֵĴ������������Զ���ȫ��ʾ
    public void facetQueryCase() {
        SolrQuery params = new SolrQuery("*:*");
        // ����
        params.addSort("image_id", SolrQuery.ORDER.asc);
        params.setStart(0);
        params.setRows(200);
        // FacetΪsolr�еĲ�η����ѯ
        // ��Ƭ��Ϣ
        params.setFacet(true).setQuery("*:*").setFacetMinCount(1)
                .setFacetLimit(5)   // ��
                .setFacetPrefix("taylor")  // ��ѯimage_username��image_cataloge�йؼ���ǰ׺��taylor��
                .addFacetField("image_username").addFacetField("image_cataloge");   // ��Ƭ�ֶ�
        try {
            QueryResponse response = client.query(params);
            // �����ѯ�����
            SolrDocumentList list = response.getResults();
            log("Query result nums: " + list.getNumFound());
            for (int i = 0; i < list.size(); i++) {
                log(list.get(i));
            }
            log("All facet filed result: ");
            // �����Ƭ��Ϣ
            List<FacetField> facets = response.getFacetFields();
            for (FacetField facet : facets) {
                log(facet);
                List<FacetField.Count> facetCounts = facet.getValues();
                for (FacetField.Count count : facetCounts) {
                    // �ؼ��� - ���ִ���
                    log(count.getName() + ": " + count.getCount());
                }
            }
            log("Search facet [name] filed result: ");
            // �����Ƭ��Ϣ
            FacetField facetField = response.getFacetField("image_username");
            List<FacetField.Count> facetFields = facetField.getValues();
            for (FacetField.Count count : facetFields) {
                // �ؼ��� - ���ִ���
                log(count.getName() + ": " + count.getCount());
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void log(Object obj) {
        System.out.println(obj);
    }

}
