package www.wechaturl.us.fangfeng.sdk.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import www.wechaturl.us.fangfeng.sdk.common.Const;
import www.wechaturl.us.fangfeng.sdk.exception.DefaultException;
import www.wechaturl.us.fangfeng.sdk.http.HttpClient;
import www.wechaturl.us.fangfeng.sdk.http.HttpClientTemplate;
import www.wechaturl.us.fangfeng.sdk.utils.CommonUtil;
import www.wechaturl.us.fangfeng.sdk.utils.UrlUtil;
import www.wechaturl.us.fangfeng.sdk.vo.*;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 本类提供域名监控api
 * <ul>
 *   <li>addUrl - 添加-网址自动监控</li>
 *   <li>updateUrl - 修改-网址自动监控</li>
 *   <li>deleteUrl - 删除-网址自动监控</li>
 *   <li>listUrl - 监控列表-网址自动监控</li>
 *   <li>listFrequency - 支持频率-网址自动监控</li>
 *   <li>singleUrlCheck - 单次检测-网址实时监控</li>
 * </ul>
 */
public class DomainUrlMonitorService {
  private final String cycleCheckUrl = UrlUtil.getDomainUrlMonitorCycleCheckUrlUrl();
  private final String singleCheckUrl = UrlUtil.getDomainUrlMonitorSingleCheckUrl();
  private final HttpClientTemplate httpClientTemplate = new HttpClientTemplate();

  public DomainUrlMonitorService(){
  }

  /**
   * <p>添加-网址自动监控</p>
   *
   * <blockquote>
   * <p>使用说明：</p>
   * 调用时，UrlParam对象以下字段必须填值：
   *   <ul>
   *     <li>appid - http://www.wechaturl.us/user/index.html 去免费获取appid</li>
   *     <li>appkey - http://www.wechaturl.us/user/index.html 去免费获取appkey</li>
   *     <li>url - http(s)://开头的网址</li>
   *   </ul>
   *   其中以下字段为选填：
   *   <ul>
   *     <li>frequency - 秒钟,目前支持  1×60,2×60, 5×60,10×60,30×60, 120×60,1440×60, 2880×60,
   *                     默认是你会员权限对应的最小值,查看https://www.wechaturl.us/user/userinfo/user_packages.html</li>
   *     <li>isMonitor - 值只能是0和1,默认为0.
   *                     如果是1,马上启动,按照你设置的频率开始监控,如果有异常会通过手机短信,邮箱,微信通知你.
   *                     设置通知方式请查看https://www.wechaturl.us/user/index.html#userinfo/alert_setting</li>
   *   </ul>
   * </blockquote>
   * <p>本API接口对应的会员中心页面 - <a href="https://www.wechaturl.us/user/index.html#business_management/url_cycle_check">https://www.wechaturl.us/user/index.html#business_management/url_cycle_check</a></p>
   * <p>本API接口对应的文档页面 - <a href="https://wechaturl.gitbook.io/wechaturl/url_cycle_check/url_cycle_check_add">https://wechaturl.gitbook.io/wechaturl/url_cycle_check/url_cycle_check_add</a></p>
   *
   * @param urlParam 包含各参数实例对象
   * @return Response
   * @throws DefaultException 参数没有初始化
   * @throws JsonProcessingException Json转对象异常
   */
  public Response addUrl(UrlParam urlParam) throws DefaultException, JsonProcessingException {
    CommonUtil.isNotNull(urlParam);
    Map<String, String> paraMap = new HashMap<>();
    paraMap.put("appid", urlParam.getAppid());
    paraMap.put("appkey", urlParam.getAppkey());
    paraMap.put("type", Const.OPERATION_ADD);
    paraMap.put("url", urlParam.getUrl());
    if (StringUtils.isNotEmpty(urlParam.getFrequency())) {
      paraMap.put("frequency", urlParam.getFrequency());
    }
    if(StringUtils.isNotEmpty(urlParam.getIsMonitor())){
      paraMap.put("is_monitor", urlParam.getIsMonitor());
    }
    return httpClientTemplate.processPost(cycleCheckUrl, paraMap, new TypeReference<Response<String>>() {
    });
  }

  /**
   * <p>删除-网址自动监控</p>
   *
   * <blockquote>
   * <p>使用说明：</p>
   * 调用时，UrlParam对象以下字段必须填值：
   *   <ul>
   *     <li>appid - http://www.wechaturl.us/user/index.html 去免费获取appid</li>
   *     <li>appkey - http://www.wechaturl.us/user/index.html 去免费获取appkey</li>
   *     <li>url - 与id二选一 http(s)://开头的网址</li>
   *     <li>id - 与url二选一</li>
   *   </ul>
   * </blockquote>
   * <p>本API接口对应的会员中心页面 - <a href="https://www.wechaturl.us/user/index.html#business_management/url_cycle_check">https://www.wechaturl.us/user/index.html#business_management/url_cycle_check</a></p>
   * <p>本API接口对应的文档页面 - <a href="https://wechaturl.gitbook.io/wechaturl/url_cycle_check/url_cycle_check_delete">https://wechaturl.gitbook.io/wechaturl/url_cycle_check/url_cycle_check_delete</a></p>
   *
   * @param urlParam 包含各参数实例对象
   * @return Response
   * @throws DefaultException 参数没有初始化
   * @throws JsonProcessingException Json转对象异常
   */
  public Response deleteUrl(UrlParam urlParam) throws DefaultException, JsonProcessingException {
    CommonUtil.isNotNull(urlParam);
    Map<String, String> paraMap = new HashMap<>();
    paraMap.put("appid", urlParam.getAppid());
    paraMap.put("appkey", urlParam.getAppkey());
    paraMap.put("type", Const.OPERATION_DELETE);
    if(StringUtils.isNotEmpty(urlParam.getId())){
      paraMap.put("id", urlParam.getId());
    }else {
      paraMap.put("url", urlParam.getUrl());
    }
    return httpClientTemplate.processPost(cycleCheckUrl, paraMap, new TypeReference<Response<String>>() {
    });
  }

  /**
   * <p>修改-网址自动监控</p>
   *
   * <blockquote>
   * <p>使用说明：</p>
   * 调用时，UrlParam对象以下字段必须填值：
   *   <ul>
   *     <li>appid - http://www.wechaturl.us/user/index.html 去免费获取appid</li>
   *     <li>appkey - http://www.wechaturl.us/user/index.html 去免费获取appkey</li>
   *     <li>url - http(s)://开头的网址</li>
   *   </ul>
   *   其中以下字段为选填：
   *   <ul>
   *     <li>frequency - 秒钟,目前支持  1×60,2×60, 5×60,10×60,30×60, 120×60,1440×60, 2880×60,
   *                     默认是你会员权限对应的最小值,查看https://www.wechaturl.us/user/userinfo/user_packages.html</li>
   *     <li>isMonitor - 值只能是0和1,默认为0.
   *                     如果是1,马上启动,按照你设置的频率开始监控,如果有异常会通过手机短信,邮箱,微信通知你.
   *                     设置通知方式请查看https://www.wechaturl.us/user/index.html#userinfo/alert_setting</li>
   *   </ul>
   * </blockquote>
   * <p>本API接口对应的会员中心页面 - <a href="https://www.wechaturl.us/user/index.html#business_management/url_cycle_check">https://www.wechaturl.us/user/index.html#business_management/url_cycle_check</a></p>
   * <p>本API接口对应的文档页面 - <a href="https://wechaturl.gitbook.io/wechaturl/url_cycle_check/url_cycle_check_edit">https://wechaturl.gitbook.io/wechaturl/url_cycle_check/url_cycle_check_edit</a></p>
   *
   * @param urlParam 包含各参数实例对象
   * @return Response
   * @throws DefaultException 参数没有初始化
   * @throws JsonProcessingException Json转对象异常
   */
  public Response updateUrl(UrlParam urlParam) throws DefaultException, JsonProcessingException {
    CommonUtil.isNotNull(urlParam);
    Map<String, String> paraMap = new HashMap<>();
    paraMap.put("appid", urlParam.getAppid());
    paraMap.put("appkey", urlParam.getAppkey());
    paraMap.put("type", Const.OPERATION_EDIT);
    paraMap.put("url", urlParam.getUrl());
    if (StringUtils.isNotEmpty(urlParam.getFrequency())) {
      paraMap.put("frequency", urlParam.getFrequency());
    }
    if(StringUtils.isNotEmpty(urlParam.getIsMonitor())){
      paraMap.put("is_monitor", urlParam.getIsMonitor());
    }
    return httpClientTemplate.processPost(cycleCheckUrl, paraMap, new TypeReference<Response<String>>() {
    });
  }

  /**
   * <p>监控列表-网址自动监控</p>
   *
   * <blockquote>
   * <p>使用说明：</p>
   * 调用时，UrlParam对象以下字段必须填值：
   *   <ul>
   *     <li>appid - http://www.wechaturl.us/user/index.html 去免费获取appid</li>
   *     <li>appkey - http://www.wechaturl.us/user/index.html 去免费获取appkey</li>
   *   </ul>
   *   其中以下字段为选填：
   *   <ul>
   *     <li>page - 当前页面,默认为1</li>
   *     <li>rows - 每页面获取的条数,默认10,最大是50</li>
   *   </ul>
   * </blockquote>
   * <p>本API接口对应的会员中心页面 - <a href="https://www.wechaturl.us/user/index.html#business_management/url_cycle_check">https://www.wechaturl.us/user/index.html#business_management/url_cycle_check</a></p>
   * <p>本API接口对应的文档页面 - <a href="https://wechaturl.gitbook.io/wechaturl/url_cycle_check/url_cycle_check_list">https://wechaturl.gitbook.io/wechaturl/url_cycle_check/url_cycle_check_list</a></p>
   *
   * @param urlParam 包含各参数实例对象
   * @return Response
   * @throws DefaultException 参数没有初始化
   * @throws JsonProcessingException Json转对象异常
   */
  public Response<UrlArrayVO<DomainUrlMonitorVO>> listUrl(UrlParam urlParam) throws DefaultException, JsonProcessingException {
    CommonUtil.isNotNull(urlParam);
    Map<String, String> paraMap = new HashMap<>();
    paraMap.put("appid", urlParam.getAppid());
    paraMap.put("appkey", urlParam.getAppkey());
    paraMap.put("type", Const.OPERATION_LIST);
    if (StringUtils.isNotEmpty(urlParam.getPage())) {
      paraMap.put("page", urlParam.getPage());
    }
    if (StringUtils.isNotEmpty(urlParam.getRows())) {
      paraMap.put("rows", urlParam.getRows());
    }
    return httpClientTemplate.processPost(cycleCheckUrl, paraMap, new TypeReference<Response<UrlArrayVO<DomainUrlMonitorVO>>>() {
    });
  }

  /**
   * <p>支持频率-网址自动监控</p>
   *
   * <blockquote>
   * <p>使用说明：</p>
   * 调用时，UrlParam对象以下字段必须填值：
   *   <ul>
   *     <li>appid - http://www.wechaturl.us/user/index.html 去免费获取appid</li>
   *     <li>appkey - http://www.wechaturl.us/user/index.html 去免费获取appkey</li>
   *   </ul>
   * </blockquote>
   * <p>本API接口对应的会员中心页面 - <a href="https://www.wechaturl.us/user/index.html#business_management/url_cycle_check">https://www.wechaturl.us/user/index.html#business_management/url_cycle_check</a></p>
   * <p>本API接口对应的文档页面 - <a href="https://wechaturl.gitbook.io/wechaturl/url_cycle_check/url_cycle_check_frequency">https://wechaturl.gitbook.io/wechaturl/url_cycle_check/url_cycle_check_frequency</a></p>
   *
   * @param urlParam 包含各参数实例对象
   * @return Response
   * @throws DefaultException 参数没有初始化
   * @throws JsonProcessingException Json转对象异常
   */
  public Response<List<Integer>> listFrequency(UrlParam urlParam) throws DefaultException, JsonProcessingException {
    CommonUtil.isNotNull(urlParam);
    Map<String, String> paraMap = new HashMap<>();
    paraMap.put("appid", urlParam.getAppid());
    paraMap.put("appkey", urlParam.getAppkey());
    paraMap.put("type", Const.OPERATION_FREQUENCY);
    return httpClientTemplate.processPost(cycleCheckUrl, paraMap, new TypeReference<Response<List<Integer>>>() {
    });
  }

  /**
   * <p>单次检测-网址实时监控</p>
   *
   * <blockquote>
   * <p>使用说明：</p>
   * 调用时，UrlParam对象以下字段必须填值：
   *   <ul>
   *     <li>appid - http://www.wechaturl.us/user/index.html 去免费获取appid</li>
   *     <li>appkey - http://www.wechaturl.us/user/index.html 去免费获取appkey</li>
   *     <li>url - http(s)://开头的网址</li>
   *   </ul>
   * </blockquote>
   * <p>本API接口对应的会员中心页面 - <a href="https://www.wechaturl.us/user/index.html#business_management/url_check">https://www.wechaturl.us/user/index.html#business_management/url_check</a></p>
   * <p>本API接口对应的文档页面 - <a href="https://wechaturl.gitbook.io/wechaturl/url_cycle_check/url_check">https://wechaturl.gitbook.io/wechaturl/url_cycle_check/url_check</a></p>
   *
   * @param urlParam 包含各参数实例对象
   * @return Response
   * @throws DefaultException 参数没有初始化
   * @throws JsonProcessingException Json转对象异常
   */
  public Response<DomainCheckResultVO> singleUrlCheck(UrlParam urlParam) throws DefaultException, JsonProcessingException {
    CommonUtil.isNotNull(urlParam);
    Map<String, String> paraMap = new HashMap<>();
    paraMap.put("appid", urlParam.getAppid());
    paraMap.put("appkey", urlParam.getAppkey());
    paraMap.put("url", urlParam.getUrl());
    return httpClientTemplate.processPost(singleCheckUrl, paraMap, new TypeReference<Response<DomainCheckResultVO>>() {
    });
  }
}
