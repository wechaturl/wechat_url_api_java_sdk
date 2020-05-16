package www.wechaturl.us.fangfeng.sdk.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import www.wechaturl.us.fangfeng.sdk.common.Const;
import www.wechaturl.us.fangfeng.sdk.exception.DefaultException;
import www.wechaturl.us.fangfeng.sdk.http.HttpClient;
import www.wechaturl.us.fangfeng.sdk.http.HttpClientTemplate;
import www.wechaturl.us.fangfeng.sdk.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import www.wechaturl.us.fangfeng.sdk.utils.UrlUtil;
import www.wechaturl.us.fangfeng.sdk.vo.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 本类提供接口如下：
 * <ul>
 * <li>listEntryDomainUrl - 获取入口域名池列表</li>
 * <li>transferLongToShortUrl - 长网址转短网址</li>
 * </ul>
 */
public class ShortUrlService {
  private final String shortUrlEntryDomainUrl = UrlUtil.getShortUrlEntryDomainUrl();
  private final String longToShortUrl = UrlUtil.getShortUrlLongToShortUrl();
  private final HttpClientTemplate httpClientTemplate = new HttpClientTemplate();

  public ShortUrlService(){
  }

  /**
   * <p>获取入口域名池列表</p>
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
   *     <li>layerType - 目前只支持以下4个值：layer_top(入口域名),layer_hide_1(二级跳转),layer_hide_2(三级跳转),layer_last(四级跳转)</li>
   *     <li>status - 注意，默认为空，不是0；具体值请查看https://wechaturl.gitbook.io/wechaturl/code_sum  url/域名微信检测状态码status</li>
   *     <li>page - 当前页面,默认为1</li>
   *     <li>rows - 每页面获取的条数,默认10,最大是50</li>
   *   </ul>
   * </blockquote>
   * <p>本API接口对应的会员中心页面 - <a href="https://www.wechaturl.us/user/index.html#business_management/user_short_domain_list">https://www.wechaturl.us/user/index.html#business_management/user_short_domain_list</a></p>
   * <p>本API接口对应的文档页面 - <a href="https://wechaturl.gitbook.io/wechaturl/shorturl/user_short_domain_list">https://wechaturl.gitbook.io/wechaturl/shorturl/user_short_domain_list</a></p>
   *
   * @param urlParam 包含各参数实例对象
   * @return Response
   * @throws DefaultException 参数没有初始化
   * @throws JsonProcessingException Json转对象异常
   */
  public Response<UrlArrayVO<EntryDomainUrlVO>> listEntryDomainUrl(UrlParam urlParam) throws DefaultException, JsonProcessingException {
    CommonUtil.isNotNull(urlParam);
    Map<String, String> paraMap = new HashMap<>();
    paraMap.put("appid", urlParam.getAppid());
    paraMap.put("appkey", urlParam.getAppkey());
    paraMap.put("type", Const.OPERATION_LIST);
    if (StringUtils.isNotEmpty(urlParam.getLayerType())) {
      paraMap.put("layer_type", urlParam.getLayerType());
    }
    if (StringUtils.isNotEmpty(urlParam.getStatus())) {
      paraMap.put("status", urlParam.getStatus());
    }
    if (StringUtils.isNotEmpty(urlParam.getPage())) {
      paraMap.put("page", urlParam.getPage());
    }
    if (StringUtils.isNotEmpty(urlParam.getRows())) {
      paraMap.put("rows", urlParam.getRows());
    }
    return httpClientTemplate.processPost(shortUrlEntryDomainUrl, paraMap, new TypeReference<Response<UrlArrayVO<EntryDomainUrlVO>>>() {
    });
  }

  /**
   * <p>长网址转短网址</p>
   *
   * <blockquote>
   * <p>使用说明：</p>
   * 调用时，UrlParam对象以下字段必须填值：
   *   <ul>
   *     <li>appid - http://www.wechaturl.us/user/index.html 去免费获取appid</li>
   *     <li>appkey - http://www.wechaturl.us/user/index.html 去免费获取appkey</li>
   *     <li>entry_type - 默认wechaturl;
   *                     目前支持['wechaturl','sohuurl','ueeurl','weibourl','tencent_weibourl','is_gd_url'];
   *                     这些类型可以到https://wechaturl.gitbook.io/wechaturl/the-third-shorturl 查看解释</li>
   *     <li>url - http(s)://开头的网址</li>
   *   </ul>
   * </blockquote>
   *
   * <p>本API接口对应的文档页面 - <a href="https://wechaturl.gitbook.io/wechaturl/shorturl/longurl_2_wechat_shorturl"></a>https://wechaturl.gitbook.io/wechaturl/shorturl/longurl_2_wechat_shorturl</p>
   *
   * @param urlParam 包含各参数实例对象
   * @return Response
   * @throws DefaultException 参数没有初始化
   * @throws JsonProcessingException Json转对象异常
   */
  public Response<ShortUrlVO> transferLongToShortUrl(UrlParam urlParam) throws JsonProcessingException, DefaultException {
    CommonUtil.isNotNull(urlParam);
    Map<String, String> paraMap = new HashMap<>();
    paraMap.put("appid", urlParam.getAppid());
    paraMap.put("appkey", urlParam.getAppkey());
    paraMap.put("url", urlParam.getUrl());
    if(StringUtils.isNotEmpty(urlParam.getEntryType())){
      paraMap.put("entry_type", urlParam.getEntryType());
    }
    return httpClientTemplate.processPost(longToShortUrl, paraMap, new TypeReference<Response<ShortUrlVO>>() {
    });
  }
}
