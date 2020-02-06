package com.gsafety.dawn.community.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Http Client帮助类.
 * Created by nusmg 2017-12-18
 */
public class HttpClientUtilImpl implements HttpClientUtil {
    private Logger logger = LoggerFactory.getLogger(HttpClientUtilImpl.class);    //日志记录

    /**
     * Http post 请求.
     *
     * @param <R>    返回数据泛型
     * @param <T>    请求的body数据类型数据
     * @param url    请求路径 url
     * @param params 请求的body
     * @param clazz  返回的数据类型
     * @return the r
     */
    @Override
    public <R, T> R httpPost(String url, T params, Class<R> clazz) {
        return httpPost(url, params, clazz, false);
    }


    /**
     * Http post 请求.
     *
     * @param <R>            返回数据泛型
     * @param <T>            请求的body数据类型数据
     * @param url            请求路径 url
     * @param params         请求的body
     * @param clazz          返回的数据类型
     * @param noNeedResponse 是否需要数据返回
     * @return the r
     */
    @Override
    public <R, T> R httpPost(String url, T params, Class<R> clazz, boolean noNeedResponse) {
        String resultData = httpReturnString(url,params, noNeedResponse,"post");
        if (StringUtils.isEmpty(resultData)) {
            return null;
        }
        return JsonUtil.fromJson(resultData, clazz);
    }

    /**
     * Http post 请求.
     *
     * @param <R>            返回数据泛型
     * @param <T>            请求的body数据类型数据
     * @param url            请求路径 url
     * @param params         请求的body
     * @param typeReference  返回的数据类型
     * @param noNeedResponse 是否需要数据返回
     * @return the r
     */
    @Override
    public <R, T> R httpPostReturnTypeRef(String url, T params, TypeReference typeReference, boolean noNeedResponse) {
        String resultData = httpReturnString(url,params, noNeedResponse,"post");
        if (StringUtils.isEmpty(resultData)) {
            return null;
        }
        return JsonUtil.fromJsonType(resultData, typeReference);
    }


    /**
     * Http post 请求.返回response string
     *
     * @param url
     * @param params
     * @param noNeedResponse
     * @param <T>
     * @return
     */
    private <T> String httpReturnString(String url,T params, boolean noNeedResponse, String type) {
        String resultData = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpPost postMethod = new HttpPost(url);
            HttpPut putMethod = new HttpPut(url);
                if (null != params) {
                    //解决中文乱码问题
                    StringEntity entity = new StringEntity(JsonUtil.toJson(params), "utf-8");
                    // entity.setContentEncoding("UTF-8")
                    entity.setContentType("application/json");
                    postMethod.setEntity(entity);
                    putMethod.setEntity(entity);
                }

            CloseableHttpResponse response = null;
            try {
                if (type == "post") {
                    response = httpClient.execute(postMethod);
                } else if (type == "put") {
                    response = httpClient.execute(putMethod);
                }
                if (noNeedResponse) {
                    return null;
                }
                if (response != null) {
                    int status = response.getStatusLine().getStatusCode();
                    /**请求发送成功，并得到响应**/
                    if (status == 200 || status == 201) {
                        /**读取服务器返回过来的json字符串数据**/
                        resultData = EntityUtils.toString(response.getEntity());
                    }
                }
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        } catch (IOException e) {
            logger.error(type + "请求提交失败:" + url, e);
        }
        return resultData;
    }

    /**
     * 发送get请求
     *
     * @param <R>   the type parameter
     * @param url   路径
     * @param clazz the clazz
     * @return r
     */
    @Override
    public <R> R httpGet(String url, Class<R> clazz) {
        //get请求返回结果
        String jsonString = httpGet(url);
        /**把json字符串转换成json对象**/
        return JsonUtil.fromJson(jsonString, clazz);
    }

    /**
     * 发送get请求，用于获取泛型对象内包含集合时使用，如ReturnBase<List<User>>
     *
     * @param <R>           the type parameter
     * @param url           路径
     * @param typeReference 类型引用对象
     * @return r
     */
    @Override
    public <R> R httpGetWithType(String url, TypeReference typeReference) {
        //get请求返回结果
        String jsonString = httpGet(url);
        /**把json字符串转换成json对象**/
        return JsonUtil.fromJsonType(jsonString, typeReference);
    }

    /**
     * 发送get请求
     *
     * @param url 路径
     * @return string
     */
    @Override
    public String httpGet(String url) {
        //get请求返回结果
        String resultData = null;

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            //发送get请求
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = null;
            try {
                response = client.execute(request);

                /**请求发送成功，并得到响应**/
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    /**读取服务器返回过来的json字符串数据**/
                    resultData = EntityUtils.toString(response.getEntity(), "utf-8");
                } else {
                    logger.error("get request error:" + url);
                }
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        } catch (IOException e) {
            logger.error("get request error:" + url, e);
        }
        return resultData;
    }

    /**
     * Http put 请求.
     *
     * @param url            请求路径 url
     * @param params         请求的body
     * @param typeReference  返回的数据类型
     * @param noNeedResponse 是否需要数据返回
     * @return the r
     */
    @Override
    public <R, T> R httpPutReturnTypeRef(String url, T params, TypeReference typeReference, boolean noNeedResponse) {
        String resultData = httpReturnString(url, params, noNeedResponse,"put");
        if (StringUtils.isEmpty(resultData)) {
            return null;
        }
        return JsonUtil.fromJsonType(resultData, typeReference);
    }

    /**
     * Http put 请求.
     *
     * @param url            请求路径 url
     * @param params         请求的body
     * @param clazz          返回的数据类型
     * @param noNeedResponse 是否需要数据返回
     * @return the r
     */
    @Override
    public <R, T> R httpPut(String url, T params, Class<R> clazz, boolean noNeedResponse) {
        String resultData = httpReturnString(url, params, noNeedResponse,"put");
        if (StringUtils.isEmpty(resultData)) {
            return null;
        }
        return JsonUtil.fromJson(resultData, clazz);
    }

    /**
     * 发送delete请求
     *
     * @param url   路径
     * @param clazz the clazz
     * @return r
     */
    @Override
    public <R> R httpDelete(String url, Class<R> clazz) {
        //delete请求返回结果
        String resultData = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            //发送delete请求
            HttpDelete request = new HttpDelete(url);
            CloseableHttpResponse response = null;
            try {
                response = client.execute(request);

                /**请求发送成功，并得到响应**/
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    /**读取服务器返回过来的json字符串数据**/
                    resultData = EntityUtils.toString(response.getEntity());
                } else {
                    logger.error("delete request error:" + url);
                }
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        } catch (IOException e) {
            logger.error("delete request error:" + url, e);
        }
        /**把json字符串转换成json对象**/
        return JsonUtil.fromJson(resultData, clazz);
    }

    /**
     * Http post 请求.
     *
     * @param <R>    返回数据泛型
     * @param <T>    请求的body数据类型数据
     * @param url    请求路径 url
     * @param authorization    请求权限头 url
     * @param params 请求的body
     * @param typeReference  返回的数据类型
     * @return the r
     */
    @Override
    public <R, T> R httpPost(String url,String authorization,T params, TypeReference typeReference) {
        String resultData = httpReturnString(url, authorization,params, false,"post");
        if (StringUtils.isEmpty(resultData)) {
            return null;
        }
        return JsonUtil.fromJsonType(resultData, typeReference);
    }
    private <T> String httpReturnString(String url, String authorization,T params, boolean noNeedResponse, String type) {
        String resultData = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpPost postMethod = new HttpPost(url);
            if(authorization!=null) {
                postMethod.setHeader("Authorization",authorization);}
            HttpPut putMethod = new HttpPut(url);
            if (null != params) {
                //解决中文乱码问题
                String Json="";
                if(params instanceof String){
                    Json = params.toString();
                }
                else
                {
                    Json= JsonUtil.toJson(params);
                }
                //StringEntity entity = new StringEntity(JsonUtil.toJson(params), "utf-8");
                StringEntity entity = new StringEntity(Json, "utf-8");
                entity.setContentType("application/json");
                postMethod.setEntity(entity);
                putMethod.setEntity(entity);
            }

            CloseableHttpResponse response = null;
            try {
                if (type == "post") {
                    response = httpClient.execute(postMethod);
                } else if (type == "put") {
                    response = httpClient.execute(putMethod);
                }
                if (noNeedResponse) {
                    return null;
                }
                if (response != null) {
                    int status = response.getStatusLine().getStatusCode();
                    //请求发送成功，并得到响应
                    if (status == 200 || status == 201) {
                        //读取服务器返回过来的json字符串数据
                        resultData = EntityUtils.toString(response.getEntity());
                    }
                }
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        } catch (IOException e) {
            logger.error(type + "请求提交失败:" + url, e);
        }
        return resultData;
    }

    /**
     * 发送get请求，用于获取泛型对象内包含集合时使用，如ReturnBase<List<User>>
     *
     * @param <R>           the type parameter
     * @param url           路径
     * @param authorization    请求权限头 url
     * @param typeReference 类型引用对象
     * @return r
     */
    @Override
    public <R> R httpGetWithType(String url, String authorization,TypeReference typeReference) {
        //get请求返回结果
        String jsonString = httpGet(url,authorization);
        /**把json字符串转换成json对象**/
        return JsonUtil.fromJsonType(jsonString, typeReference);
    }

    private String httpGet(String url,String authorization) {
        //get请求返回结果
        String resultData = null;

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            //发送get请求
            HttpGet request = new HttpGet(url);
            request.setHeader("Authorization",authorization);
            CloseableHttpResponse response = null;
            try {
                response = client.execute(request);

                /**请求发送成功，并得到响应**/
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    /**读取服务器返回过来的json字符串数据**/
                    resultData = EntityUtils.toString(response.getEntity(), "utf-8");
                } else {
                    logger.error("get request error:" + url);
                }
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        } catch (IOException e) {
            logger.error("get request error:" + url, e);
        }
        return resultData;
    }
}
