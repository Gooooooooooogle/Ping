package com.ping.core.util;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;

/**
 * 基于solrj的工具类，提供搜索相关的方法
 * @author ex
 * @version 0.1
 */
public class SolrUtils {

    public static final String SOLR_URL = "http://localhost:8080/solr/db";

    /**
     * 根据查询参数对solr进行查询获得结果
     * @param queryArgs
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    public static SolrDocumentList solr(String queryArgs) throws IOException, SolrServerException {
        SolrClient solr = new HttpSolrClient(SOLR_URL);
        SolrQuery parameters = new SolrQuery();
        parameters.set("q", queryArgs);

        QueryResponse response = solr.query(parameters);
        SolrDocumentList solrDocumentList = response.getResults();

        return solrDocumentList;
    }

    public static void main(String[] args) {
        System.out.println();
    }

}
