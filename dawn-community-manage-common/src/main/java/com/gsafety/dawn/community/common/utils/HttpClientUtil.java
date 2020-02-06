package com.gsafety.dawn.community.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Http Client帮助类.
 * Created by nusmg 2017-12-18
 */
public interface HttpClientUtil {


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
    <R, T> R httpPost(String url, T params, Class<R> clazz);


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
    <R, T> R httpPost(String url, T params, Class<R> clazz, boolean noNeedResponse);

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
    <R, T> R httpPostReturnTypeRef(String url, T params, TypeReference typeReference, boolean noNeedResponse) ;

    /**
     * 发送get请求
     *
     * @param <R>   the type parameter
     * @param url   路径
     * @param clazz the clazz
     * @return r
     */
    <R> R httpGet(String url, Class<R> clazz);

    /**
     * 发送get请求，用于获取泛型对象内包含集合时使用，如ReturnBase<List<User>>
     *
     * @param <R>           the type parameter
     * @param url           路径
     * @param typeReference 类型引用对象
     * @return r
     */
    <R> R httpGetWithType(String url, TypeReference typeReference);

    /**
     * 发送get请求
     *
     * @param url 路径
     * @return string
     */
    String httpGet(String url);



    /**
     * Http put 请求.
     *
     * @param <R>            返回数据泛型
     * @param <T>            请求的body数据类型数据
     * @param url            请求路径 url
     * @param params         请求的body
     * @param typeReference  返回的数据类型
     * @param noNeedResponse 是否需要数据返回
     * @return the r
     */
    <R, T> R httpPutReturnTypeRef(String url, T params, TypeReference typeReference, boolean noNeedResponse) ;


    /**
     * Http put 请求.
     *
     * @param <R>            返回数据泛型
     * @param <T>            请求的body数据类型数据
     * @param url            请求路径 url
     * @param params         请求的body
     * @param clazz          返回的数据类型
     * @param noNeedResponse 是否需要数据返回
     * @return the r
     */
    <R, T> R httpPut(String url, T params, Class<R> clazz, boolean noNeedResponse);

    /**
     * 发送delete请求
     *
     * @param <R>   the type parameter
     * @param url   路径
     * @param clazz the clazz
     * @return r
     */
    <R> R httpDelete(String url, Class<R> clazz);

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
    <R, T> R httpPost(String url, String authorization, T params, TypeReference typeReference);

    /**
     * 发送get请求，用于获取泛型对象内包含集合时使用，如ReturnBase<List<User>>
     *
     * @param <R>           the type parameter
     * @param url           路径
     * @param authorization    请求权限头 url
     * @param typeReference 类型引用对象
     * @return r
     */
     <R> R httpGetWithType(String url, String authorization, TypeReference typeReference);
}
