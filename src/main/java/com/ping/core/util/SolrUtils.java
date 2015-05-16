package com.ping.core.util;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;

/**
 * ����solrj�Ĺ����࣬�ṩ������صķ���
 * @author ex
 * @version 0.1
 */
public class SolrUtils {

    public static final String SOLR_URL = "http://localhost:8080/solr/db";

    /**
     * ���ݲ�ѯ������solr���в�ѯ��ý��
     * @param queryArgs
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    public static SolrDocumentList solr(String queryArgs) throws IOException, SolrServerException {
        SolrClient solr = new HttpSolrClient(SOLR_URL);

        SolrQuery parameters = new SolrQuery();
        parameters.set("q", queryArgs);
        parameters.addSort("image_id", SolrQuery.ORDER.asc);
        parameters.setHighlight(true);
        parameters.addHighlightField("image_username");
        parameters.setHighlightSimplePre("<font color='red'>");
        parameters.setHighlightSimplePost("</font>");
        parameters.setHighlightSnippets(1);// �����Ƭ����Ĭ��Ϊ1
        parameters.setHighlightFragsize(1000);// ÿ����Ƭ����󳤶ȣ�Ĭ��Ϊ100

        QueryResponse response = solr.query(parameters);
        SolrDocumentList solrDocumentList = response.getResults();

        return solrDocumentList;
    }

}
