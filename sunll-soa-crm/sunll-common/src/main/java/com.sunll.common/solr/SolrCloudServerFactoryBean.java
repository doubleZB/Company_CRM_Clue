package com.sunll.common.solr;

import org.apache.http.client.HttpClient;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.HttpClientUtil;
import org.apache.solr.client.solrj.impl.LBHttpSolrServer;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by sunll on 2017/10/18.
 */
public class SolrCloudServerFactoryBean implements FactoryBean<SolrServer>, InitializingBean {

    private CloudSolrServer cloudSolrServer;

    private String zkHost;

    private String defaultCollection;

    private int maxConnections = 1000;

    private int maxConnectionsPerHost = 500;

    private int zkClientTimeout = 10000;

    private int zkConnectTimeout = 10000;

    private Lock lock = new ReentrantLock();

    public SolrServer getObject() throws Exception {
        return cloudSolrServer;
    }

    public Class<SolrServer> getObjectType() {
        return SolrServer.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws Exception {
        ModifiableSolrParams params = new ModifiableSolrParams();
        params.set(HttpClientUtil.PROP_MAX_CONNECTIONS, maxConnections);
        params.set(HttpClientUtil.PROP_MAX_CONNECTIONS_PER_HOST, maxConnectionsPerHost);
        HttpClient client = HttpClientUtil.createClient(params);
        LBHttpSolrServer lbServer = new LBHttpSolrServer(client);
        lock.lock();
        try {
            if (cloudSolrServer == null) {
                cloudSolrServer = new CloudSolrServer(zkHost, lbServer);
            }
        } finally {
            lock.unlock();
        }

        cloudSolrServer.setDefaultCollection(defaultCollection);
        cloudSolrServer.setZkClientTimeout(zkClientTimeout);
        cloudSolrServer.setZkConnectTimeout(zkConnectTimeout);
    }

    public void setCloudSolrServer(CloudSolrServer cloudSolrServer) {
        this.cloudSolrServer = cloudSolrServer;
    }

    public void setZkHost(String zkHost) {
        this.zkHost = zkHost;
    }

    public void setDefaultCollection(String defaultCollection) {
        this.defaultCollection = defaultCollection;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public void setMaxConnectionsPerHost(int maxConnectionsPerHost) {
        this.maxConnectionsPerHost = maxConnectionsPerHost;
    }

    public void setZkClientTimeout(int zkClientTimeout) {
        this.zkClientTimeout = zkClientTimeout;
    }

    public void setZkConnectTimeout(int zkConnectTimeout) {
        this.zkConnectTimeout = zkConnectTimeout;
    }

}
