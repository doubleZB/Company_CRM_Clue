package com.sunll.common.solr;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator
 * on 2017/10/27
 */
public class ReSolrData<T> implements Serializable{

    private T t;

    private int page;

    private Long totalPage;

    private List<T> tList;

    public List<T> gettList() {
        return tList;
    }

    public void settList(List<T> tList) {
        this.tList = tList;
    }

    public ReSolrData(T t){
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }
}
