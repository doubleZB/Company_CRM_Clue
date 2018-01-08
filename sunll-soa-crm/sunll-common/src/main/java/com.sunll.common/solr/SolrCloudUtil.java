package com.sunll.common.solr;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sunll
 * on 2017/10/25
 */
@Service
public class SolrCloudUtil<T> {

    private T t;

    public SolrCloudUtil() {
    }

    public SolrCloudUtil(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t){
        this.t = t;
    }

    @Autowired
    private CloudSolrServer cloudSolrServer;


    /**
     *
     * @param collection 分片
     * @param queryMap   主查询条件
     * @param filterQueryMap 过滤条件
     * @param sortField  排序字段
     * @param order      排序（desc/asc）
     * @param page       当前页数
     * @param pageSize   每页显示的数量
     * @param df          默认搜索域
     * @param t          泛型实体
     * @return
     * @throws Exception
     */
    public ReSolrData query(String collection,Map<String, Object> queryMap, Map<String, Object> filterQueryMap,
                      String sortField, String order, int page, int pageSize, String df,T t) throws Exception {
        SolrQuery solrQuery = new SolrQuery();
        //拼装查询条件
        //主查询条件
        if (queryMap.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<String, Object> vo : queryMap.entrySet()) {
                if (StringUtils.isNoneBlank(stringBuilder.toString())) {
                    stringBuilder.append(" AND ").append(vo.getKey()).append(":").append(vo.getValue());
                } else {
                    stringBuilder.append(vo.getKey()).append(":").append(vo.getValue());
                }
            }
            solrQuery.setQuery(stringBuilder.toString());
        } else {
            solrQuery.setQuery("*:*");
        }
        //范围过滤条件
        if (filterQueryMap.size() > 0) {
            for (Map.Entry<String, Object> vo : filterQueryMap.entrySet()) {
                solrQuery.addFilterQuery(vo.getKey() + ":[" + vo.getValue() + "]");
            }
        }

        //排序条件
        if ("desc".equals(order)) {
            solrQuery.setSort(sortField, SolrQuery.ORDER.desc);
        } else {
            solrQuery.setSort(sortField, SolrQuery.ORDER.asc);
        }
        //分页条件
        if (page == 0) page = 1;
        //计算分页
        int start = (page - 1) * pageSize;
        solrQuery.setStart(start);
        solrQuery.setRows(pageSize);
        //设置默认搜索域
        solrQuery.set("df", df);
        /*//设置高亮
        solrQuery.setHighlight(true);
        //高亮显示的域
        solrQuery.addHighlightField("name");
        //高亮显示的前缀
        solrQuery.setHighlightSimplePre("<span style=\"color:red\">");
        //高亮后缀
        solrQuery.setHighlightSimplePost("</span>");*/
        //执行查询
        Map<String, Object> result = queryBySolrQuery(solrQuery);
        //计算总页数
        Long recordCount = (Long) result.get("recordCount");
        int pageCount = (int) (recordCount / pageSize);
        if (recordCount % pageSize > 0) {
            pageCount++;
        }
        result.put("pageCount", pageCount);
        result.put("page", page);
        ReSolrData<T> reSolrData = new ReSolrData<>(t);
        QueryResponse queryResponse = (QueryResponse) result.get("queryResponse");
        List<T> list = queryResponse.getBeans((Class<T>) t.getClass());
        reSolrData.setPage(page);
        reSolrData.settList(list);
        reSolrData.setTotalPage(recordCount);
        return reSolrData;

    }

    private Map<String, Object> queryBySolrQuery(SolrQuery solrQuery) throws Exception {

        //根据查询条件查询索引库

        QueryResponse response = cloudSolrServer.query(solrQuery);


        //取查询结果  存在错误需要添加泛型o

        /*List<T> list = response.getBeans(t);*/

       /* //取高亮显示

        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

        //商品列表

        List<User> productList = new ArrayList<>();

        //遍历列表

        for (SolrDocument solrDocument : solrDocumentList) {

            //取商品信息

            User model = new User();

            model.setId((int) solrDocument.get("id"));
            System.out.println(solrDocument.get("id"));
            List<String> list = highlighting.get(solrDocument.get("id")+"").get("name");
            String name = "";

            if (null != list && list.size() > 0) {

                name = list.get(0);

            } else {

                name = (String) solrDocument.get("name");

            }

            model.setName(name);

           *//* model.setAge((int) solrDocument.get("description"));*//*


            //添加到商品列表

            productList.add(model);

        }
*/
        //添加到返回值对象中

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("queryResponse", response);
        //查询结果的总数量
        map.put("recordCount",response.getResults().getNumFound());
        return map;

    }

    /**
     * 向solrCloud上创建索引bean
     * 实体中需要加入solr注解@Field
     * 如果实体中的字段和solr中的不匹配则可以使用@Field("solr中对应的字段"）
     *
     * @param object
     * @param collection
     * @return boolean
     */
    public boolean creatOrUpdateIndex(Object object, String collection) {
        try {
            if (StringUtils.isNotBlank(collection)) {
                cloudSolrServer.setDefaultCollection(collection);
            }
            UpdateResponse updateResponse = cloudSolrServer.addBean(object);
            return updateResponse.getStatus() == 0 && commonStatus(updateResponse, cloudSolrServer);
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 创建索引
     *
     * @param map
     * @return
     */
    public boolean creatOrUpdateIndex(Map<String, Object> map, String collection) {
        SolrInputDocument document = new SolrInputDocument();
        if (map.size() > 0) {
            for (Map.Entry<String, Object> vo : map.entrySet()) {
                document.addField(vo.getKey(), vo.getValue());
            }
        }
        try {
            if (StringUtils.isNotBlank(collection)) {
                cloudSolrServer.setDefaultCollection(collection);
            }
            UpdateResponse updateResponse = cloudSolrServer.add(document);
            return updateResponse.getStatus() == 0 && commonStatus(updateResponse, cloudSolrServer);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 批量创建索引
     *
     * @param objectList
     */
    public boolean creatOrUpdateIndexBatch(List<Object> objectList, String collection) {
        try {
            if (StringUtils.isNotBlank(collection)) {
                cloudSolrServer.setDefaultCollection(collection);
            }
            UpdateResponse updateResponse = cloudSolrServer.addBeans(objectList);
            return updateResponse.getStatus() == 0 && commonStatus(updateResponse, cloudSolrServer);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 删除所有索引
     *
     * @return
     */
    public boolean deleteAll() {
        String sQuery = "*:*";
        try {
            UpdateResponse updateResponse = cloudSolrServer.deleteByQuery(sQuery);
            return updateResponse.getStatus() == 0 && commonStatus(updateResponse, cloudSolrServer);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 通过id删除
     *
     * @param id
     * @return boolean
     */
    public boolean deleteById(String id) {
        try {
            UpdateResponse updateResponse = cloudSolrServer.deleteById(id);
            return updateResponse.getStatus() == 0 && commonStatus(updateResponse, cloudSolrServer);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 通过查询条件删除
     *
     * @param solrQuery
     * @return
     */
    public boolean deleteBySolrQuery(String solrQuery) {
        try {
            UpdateResponse updateResponse = cloudSolrServer.deleteByQuery(solrQuery);
            return updateResponse.getStatus() == 0 && commonStatus(updateResponse, cloudSolrServer);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean commonStatus(UpdateResponse updateResponse, CloudSolrServer cloudSolrServer) {
        if (updateResponse.getStatus() == 0) {
            try {
                updateResponse = cloudSolrServer.commit();
                return updateResponse.getStatus() == 0;
            } catch (SolrServerException | IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

   /*
   //查询使用例子
   public ReSolrData querySolr(User user) throws Exception {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("name","sunll");
        queryMap.put("description","大傻子");
        Map<String, Object> filterQueryMap = new HashMap<>();
        filterQueryMap.put("id","100001 TO 100007");
        User1 user1 = new User1();
        return solrCloudUtil.query("collection1",queryMap,filterQueryMap,"id",
                "desc",1,2,"name",user1);
    }
    //创建索引使用的例子
    public String creatIndex(User user) {
        solrCloudUtil.creatOrUpdateIndex(user,"collection1");
        return "成功";
    }
    */
}
