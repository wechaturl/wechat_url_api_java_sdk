package www.wechaturl.us.fangfeng.sdk.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import www.wechaturl.us.fangfeng.sdk.http.HttpClient;
import www.wechaturl.us.fangfeng.sdk.common.Const;
import www.wechaturl.us.fangfeng.sdk.exception.DefaultException;
import www.wechaturl.us.fangfeng.sdk.http.HttpClientTemplate;
import www.wechaturl.us.fangfeng.sdk.utils.CommonUtil;
import www.wechaturl.us.fangfeng.sdk.utils.UrlUtil;
import www.wechaturl.us.fangfeng.sdk.vo.*;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 本类提供任意网址防封API列表如下：
 * <ul>
 *     <li>addUrl - 添加防封网址</li>
 *     <li>updateUrl - 修改防封网址</li>
 *     <li>deleteUrl - 删除防封网址</li>
 *     <li>listUrl - 获取防封网址列表</li>
 * </ul>
 */
public class SingleUrlProtectService {
  private final String REQUEST_URL = UrlUtil.getSingleUrlProtectUrl();
  private final HttpClientTemplate httpClientTemplate = new HttpClientTemplate();


  public SingleUrlProtectService() {
  }

  /**
   * <p>添加防封网址</p>
   *
   * <blockquote>
   * <p>使用说明：</p>
   * 调用时，UrlParam对象以下字段必须填值：
   *   <ul>
   *     <li>appid</li>
   *     <li>appkey</li>
   *     <li>url - http(s)://开头的网址</li>
   *   </ul>
   *   其中以下字段为选填：
   *   <ul>
   *     <li>visitType - 值只能是:browser,frame,jump,android_browser.默认  jump
   *                     如果你不知道它含义请到会员中心页面版查看</li>
   *     <li>title - 网址的title标签</li>
   *     <li>keywords - 网址的keywords标签</li>
   *     <li>description - 网址的description标签</li>
   *   </ul>
   * </blockquote>
   *
   * <p>本API接口对应的会员中心页面 - <a href="https://www.wechaturl.us/user/index.html#business_management/single_url_list">https://www.wechaturl.us/user/index.html#business_management/single_url_list</a></p>
   * <p>本API接口对应文档 - <a href="https://wechaturl.gitbook.io/wechaturl/single_url/single_url_add">https://wechaturl.gitbook.io/wechaturl/single_url/single_url_add</a></p>
   *
   * @param urlParam 包含各参数实例对象
   * @return Response
   * @throws DefaultException 通常是参数没有初始化
   * @throws JsonProcessingException Json转对象异常
   */
  public Response<WeiboVO> addUrl(UrlParam urlParam) throws DefaultException, JsonProcessingException {
    Map<String, String> paraMap = buildPara(urlParam, Const.OPERATION_ADD);
    return httpClientTemplate.processPost(REQUEST_URL, paraMap, new TypeReference<Response<WeiboVO>>() {
    });
  }

  /**
   * <p>删除防封网址</p>
   *
   * <blockquote>
   * <p>使用说明：</p>
   * 调用时，UrlParam对象以下字段必须填值：
   *   <ul>
   *     <li>appid</li>
   *     <li>appkey</li>
   *     <li>url - 待删除以http(s)://开头的网址 或者 下面对应id;url和id至少一个必须，优先通过id查找</li>
   *     <li>id - 待删除的id编号，这个值在列表里面可以看到;url和id至少一个必须，优先通过id查找</li>
   *   </ul>
   * </blockquote>
   * <p>本API接口对应的会员中心页面 - <a href="https://www.wechaturl.us/user/index.html#business_management/single_url_list">https://www.wechaturl.us/user/index.html#business_management/single_url_list</a></p>
   * <p>本API接口对应文档 - <a href="https://wechaturl.gitbook.io/wechaturl/single_url/single_url_delete">https://wechaturl.gitbook.io/wechaturl/single_url/single_url_delete</a></p>
   *
   * @param urlParam 包含各参数实例对象
   * @return Response
   * @throws DefaultException 通常是参数没有初始化
   * @throws JsonProcessingException Json转对象异常
   */
  public Response deleteUrl(UrlParam urlParam) throws DefaultException, JsonProcessingException {
    CommonUtil.isNotNull(urlParam);
    Map<String, String> paraMap = new HashMap<>();
    paraMap.put("appid", urlParam.getAppid());
    paraMap.put("appkey", urlParam.getAppkey());
    paraMap.put("type", Const.OPERATION_DELETE);
    if(StringUtils.isEmpty(urlParam.getId())){
      paraMap.put("url", urlParam.getUrl());
    } else {
      paraMap.put("id", urlParam.getId());
    }
    return httpClientTemplate.processPost(REQUEST_URL, paraMap, new TypeReference<Response<String>>() {
    });
  }

  /**
   * <p>修改防封网址</p>
   *
   * <blockquote>
   * <p>使用说明：</p>
   * 调用时，UrlParam对象以下字段必须填值：
   *   <ul>
   *     <li>appid</li>
   *     <li>appkey</li>
   *     <li>url - http(s)://开头的网址</li>
   *   </ul>
   *   其中以下字段为选填：
   *   <ul>
   *     <li>visitType - 值只能是:browser,frame,jump,android_browser.默认  jump
   *                     如果你不知道它含义请到会员中心页面版查看</li>
   *     <li>title - 网址的title标签</li>
   *     <li>keywords - 网址的keywords标签</li>
   *     <li>description - 网址的description标签</li>
   *   </ul>
   * </blockquote>
   * <p>本API接口对应的会员中心页面 - <a href="https://www.wechaturl.us/user/index.html#business_management/single_url_list">https://www.wechaturl.us/user/index.html#business_management/single_url_list</a></p>
   * <p>本API接口对应文档 - <a href="https://wechaturl.gitbook.io/wechaturl/single_url/single_url_edit">https://wechaturl.gitbook.io/wechaturl/single_url/single_url_edit</a></p>
   * @param urlParam 包含各参数实例对象
   * @return Response
   * @throws DefaultException 通常是参数没有初始化
   * @throws JsonProcessingException Json转对象异常
   */
  public Response updateUrl(UrlParam urlParam) throws DefaultException, JsonProcessingException {
    Map<String, String> paraMap = buildPara(urlParam, Const.OPERATION_EDIT);
    return httpClientTemplate.processPost(REQUEST_URL, paraMap, new TypeReference<Response<String>>() {
    });
  }

  /**
   * <p>获取防封列表</p>
   *
   * <blockquote>
   * <p>使用说明：</p>
   * 调用时，UrlParam对象以下字段必须填值：
   *   <ul>
   *     <li>appid</li>
   *     <li>appkey</li>
   *   </ul>
   *   其中以下字段为选填：
   *   <ul>
   *     <li>url - http(s)://开头的网址; 默认不筛选网址</li>
   *     <li>page - 当前页面,默认为1</li>
   *     <li>rows - 每页面获取的条数,默认10,最大是50</li>
   *   </ul>
   * </blockquote>
   * <p>本API接口对应的会员中心页面 -
   *     <a href="https://www.wechaturl.us/user/index.html#business_management/single_url_list">https://www.wechaturl.us/user/index.html#business_management/single_url_list</a>
   * </p>
   * <p>本API接口对应文档 - <a href="https://wechaturl.gitbook.io/wechaturl/single_url/single_url_list">https://wechaturl.gitbook.io/wechaturl/single_url/single_url_list</a></p>
   *
   * @param urlParam 包含各参数实例对象
   * @return Response
   * @throws DefaultException 通常是参数没有初始化
   * @throws JsonProcessingException Json转对象异常
   */
  public Response<UrlArrayVO<UrlVO>> listUrl(UrlParam urlParam) throws DefaultException, JsonProcessingException {
    CommonUtil.isNotNull(urlParam);
    Map<String, String> paraMap = new HashMap<>();
    paraMap.put("appid", urlParam.getAppid());
    paraMap.put("appkey", urlParam.getAppkey());
    paraMap.put("type", Const.OPERATION_LIST);
    if (StringUtils.isNotEmpty(urlParam.getUrl())) {
      paraMap.put("url", urlParam.getUrl());
    }
    if (StringUtils.isNotEmpty(urlParam.getPage())) {
      paraMap.put("page", urlParam.getPage());
    }
    if (StringUtils.isNotEmpty(urlParam.getRows())) {
      paraMap.put("rows", urlParam.getRows());
    }
    return httpClientTemplate.processPost(REQUEST_URL, paraMap, new TypeReference<Response<UrlArrayVO<UrlVO>>>() {
    });
  }

  private Map<String, String> buildPara(UrlParam urlParam, String type) throws DefaultException {
    CommonUtil.isNotNull(urlParam);
    Map<String, String> paraMap = new HashMap<>();
    paraMap.put("appid", urlParam.getAppid());
    paraMap.put("appkey", urlParam.getAppkey());
    paraMap.put("type", type);
    paraMap.put("url", urlParam.getUrl());
    if (StringUtils.isNotEmpty(urlParam.getVisitType())) {
      paraMap.put("visit_type", urlParam.getVisitType());
    }
    if (StringUtils.isNotEmpty(urlParam.getTitle())) {
      paraMap.put("title", urlParam.getTitle());
    }
    if (StringUtils.isNotEmpty(urlParam.getKeywords())) {
      paraMap.put("keywords", urlParam.getKeywords());
    }
    if (StringUtils.isNotEmpty(urlParam.getDescription())) {
      paraMap.put("description", urlParam.getDescription());
    }
    return paraMap;
  }
}
