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

    // 单文档插入
    public void addDoc() throws Exception {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", 10000001);
        doc.addField("name", "Solr Input Document");
        doc.addField("manu", "this is SolrInputDocument content");
        client.add(doc);
        client.commit();
    }

    // 批量文档插入
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

    // 单个文档对应的 Java Bean 插入
    public void addDocBean() throws Exception {
        Image doc = new Image();
        doc.setImageId("555");
        client.addBean(doc);
        client.commit();
    }

    // 批量文档 Bean 插入
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

    // 根据 id 删除单个文档
    public void remove() throws Exception {
        client.deleteById("100000000");
        client.commit();
    }

    // 根据批量 id 删除文档
    public void removeList() throws Exception {
        List<String> ids = new ArrayList<String>();
        for (int i = 1; i < 6; i++) {
            ids.add("10000000" + i);
        }
        client.deleteById(ids);
        client.commit();
    }

    // 根据查询条件删除文档
    public void removeByQuery() throws Exception {
        client.deleteByQuery("id:100000007");
        client.commit();
    }

    // 查询操作
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
    // 一些附属操作
    public void otherMethod() throws Exception {
        client.getBinder();
        client.optimize(); // 合并索引文件，可以优化索引、提供性能，但需要一定的时间
        client.ping(); // ping服务器是否连接成功
        client.rollback();
        client.commit();
    }

    @Test
    // query 基本用法测试
    public void queryCase() {
        // AND 并且
        SolrQuery params = new SolrQuery("2");
        // OR 或者
        //params.setQuery("image_username:taylor swift OR image_hot:2");
        // 空格 等同于 OR
        //params.setQuery("name:server manu:dell");
        // params.setQuery("name:solr - manu:inc");
        // params.setQuery("name:server + manu:dell");
        // 查询name包含solr apple
        //params.setQuery("name:solr,apple");
        // manu不包含inc
        //params.setQuery("name:solr,apple NOT manu:inc");
        // 50 <= price <= 200
        //params.setQuery("price:[50 TO 200]");
        //params.setQuery("popularity:[5 TO 6]");
        // 50 <= price <= 200 AND 5 <= popularity <= 6
        //params.setQuery("price:[50 TO 200] AND popularity:[5 TO 6]");
        //params.setQuery("price:[50 TO 200] OR popularity:[5 TO 6]");
        // 过滤器查询，可以提高性能 filter 类似多个条件组合，如and
        // params.addFilterQuery("id:VA902B");
        // params.addFilterQuery("price:[50 TO 200]");
        // params.addFilterQuery("popularity:[* TO 5]");
        // params.addFilterQuery("weight:*");
        // 0 < popularity < 6 没有等于
        // params.addFilterQuery("popularity:{0 TO 6}");
        // 排序
        params.addSort("image_id", SolrQuery.ORDER.asc);
        // 分页：start开始页，rows每页显示记录条数
        // params.add("start", "0");
        // params.add("rows", "200");
        // params.setStart(0);
        // params.setRows(200);
        // 设置高亮
        params.setHighlight(true); // 开启高亮组件
        params.addHighlightField("image_username");// 高亮字段
        params.setHighlightSimplePre("<font color='red'>");// 标记，高亮关键字前缀
        params.setHighlightSimplePost("</font>");// 后缀
        params.setHighlightSnippets(1);// 结果分片数，默认为1
        params.setHighlightFragsize(1000);// 每个分片的最大长度，默认为100
        // 分片信息
        params.setFacet(true).setFacetMinCount(1).setFacetLimit(5)// 段
                .addFacetField("image_username")// 分片字段
                .addFacetField("image_title");
        // params.setQueryType("");
        try {
            QueryResponse response = client.query(params);
            /*
             * List<Index> indexs = response.getBeans(Index.class); for (int i =
             * 0; i < indexs.size(); i++) { fail(indexs.get(i)); }
             */
            // 输出查询结果集
            SolrDocumentList list = response.getResults();
            log("query result nums: " + list.getNumFound());
            for (int i = 0; i < list.size(); i++) {
                log(list.get(i));
            }
            // 输出分片信息
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
    // 分片查询， 可以统计关键字及出现的次数、或是做自动补全提示
    public void facetQueryCase() {
        SolrQuery params = new SolrQuery("*:*");
        // 排序
        params.addSort("image_id", SolrQuery.ORDER.asc);
        params.setStart(0);
        params.setRows(200);
        // Facet为solr中的层次分类查询
        // 分片信息
        params.setFacet(true).setQuery("*:*").setFacetMinCount(1)
                .setFacetLimit(5)   // 段
                .setFacetPrefix("taylor")  // 查询image_username、image_cataloge中关键字前缀是taylor的
                .addFacetField("image_username").addFacetField("image_cataloge");   // 分片字段
        try {
            QueryResponse response = client.query(params);
            // 输出查询结果集
            SolrDocumentList list = response.getResults();
            log("Query result nums: " + list.getNumFound());
            for (int i = 0; i < list.size(); i++) {
                log(list.get(i));
            }
            log("All facet filed result: ");
            // 输出分片信息
            List<FacetField> facets = response.getFacetFields();
            for (FacetField facet : facets) {
                log(facet);
                List<FacetField.Count> facetCounts = facet.getValues();
                for (FacetField.Count count : facetCounts) {
                    // 关键字 - 出现次数
                    log(count.getName() + ": " + count.getCount());
                }
            }
            log("Search facet [name] filed result: ");
            // 输出分片信息
            FacetField facetField = response.getFacetField("image_username");
            List<FacetField.Count> facetFields = facetField.getValues();
            for (FacetField.Count count : facetFields) {
                // 关键字 - 出现次数
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
