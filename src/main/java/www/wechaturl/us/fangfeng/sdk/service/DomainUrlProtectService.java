package www.wechaturl.us.fangfeng.sdk.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import www.wechaturl.us.fangfeng.sdk.common.Const;
import www.wechaturl.us.fangfeng.sdk.exception.DefaultException;
import www.wechaturl.us.fangfeng.sdk.http.HttpClientTemplate;
import www.wechaturl.us.fangfeng.sdk.utils.CharacterUtil;
import www.wechaturl.us.fangfeng.sdk.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import www.wechaturl.us.fangfeng.sdk.utils.UrlUtil;
import www.wechaturl.us.fangfeng.sdk.vo.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 本类提供域名防封API列表如下：
 * <ul>
 *     <li>listLandDomain - 获取落地页域名列表</li>
 *     <li>addUrl - 添加防封网址</li>
 *     <li>updateUrl - 修改防封网址</li>
 *     <li>deleteUrl - 删除防封网址</li>
 *     <li>listUrl - 获取防封网址列表</li>
 *     <li>highFrequencyCheck - 超高频域名检测</li>
 *     <li>dynamicSetURIParameters - 动态设置任何URI参数</li>
 * </ul>
 */
public class DomainUrlProtectService {
  private static final String REQUEST_URL = UrlUtil.getDomainUrlProtectUrl();
  private final HttpClientTemplate httpClientTemplate = new HttpClientTemplate();
  private static final String SYMBOLS = CommonUtil.getResource("dynamic.random.symbol");

  public DomainUrlProtectService() {
  }

  /**
   * <p>添加防封网址</p>
   *
   * <blockquote>
   * <p>接口使用说明：</p>
   * 调用时，UrlParam对象以下字段必须填值：
   *   <ul>
   *     <li>appid - http://www.wechaturl.us/user/index.html 去免费获取appid</li>
   *     <li>appkey - http://www.wechaturl.us/user/index.html 去免费获取appkey</li>
   *     <li>url - 输入http(s)://开头的网址. 记住,填写的网址对应的域名必须提前添加到落地页域名列表,可以到这里去操作
   *               https://www.wechaturl.us/user/index.html#business_management/land_domain</li>
   *   </ul>
   *   其中以下字段为选填：
   *   <ul>
   *     <li>groupId - 0(默认分组),2(海外网站),3(国内竞价),4(客户测试) - 不设值则系统默认设为0</li>
   *     <li>visitType - browser,frame,jump,android_browser - 默认jump, 如果你不知道它含义请到会员中心页面版‘添加防封网址’查看</li>
   *     <li>title - 网址的title标签,默认null</li>
   *     <li>keywords</li>
   *     <li>description</li>
   *   </ul>
   * </blockquote>
   * <p>本API接口对应的会员中心页面 - <a href="https://www.wechaturl.us/user/index.html#business_management/domain_url_list">https://www.wechaturl.us/user/index.html#business_management/domain_url_list</a></p>
   * <p>本API接口对应的文档页面 - <a href="https://wechaturl.gitbook.io/wechaturl/domain/domain_url_add">https://wechaturl.gitbook.io/wechaturl/domain/domain_url_add</a></p>
   * @param urlParam 包含各参数实例对象
   * @return Response
   * @throws DefaultException        参数没有初始化
   * @throws JsonProcessingException Json转对象异常
   */
  public Response<WeiboVO> addUrl(UrlParam urlParam) throws DefaultException, JsonProcessingException {
    CommonUtil.isNotNull(urlParam);
    Map<String, String> paraMap = new HashMap<>();
    paraMap.put("appid", urlParam.getAppid());
    paraMap.put("appkey", urlParam.getAppkey());
    paraMap.put("type", Const.OPERATION_ADD);
    paraMap.put("url", urlParam.getUrl());
    if (StringUtils.isNotEmpty(urlParam.getVisitType())) {
      paraMap.put("visit_type", urlParam.getVisitType());
    }
    if (StringUtils.isNotEmpty(urlParam.getGroupId())) {
      paraMap.put("group_id", urlParam.getGroupId());
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
    return httpClientTemplate.processPost(REQUEST_URL, paraMap, new TypeReference<Response<WeiboVO>>() {
    });
  }

  /**
   * <p>删除域名防封网址</p>
   *
   * <blockquote>
   * <p>使用说明：</p>
   * 调用时，UrlParam对象以下字段必须填值：
   *   <ul>
   *     <li>appid - http://www.wechaturl.us/user/index.html 去免费获取appid</li>
   *     <li>appkey - http://www.wechaturl.us/user/index.html 去免费获取appkey</li>
   *     <li>url - 输入http(s)://开头的网址, 与id二选一
   *               记住,url对应的域名必须提前添加到落地页域名列表,可以到这里去操作；url和id至少一个必须，优先通过id查找
   *               https://www.wechaturl.us/user/index.html#business_management/land_domain</li>
   *     <li>id - 待删除的id编号，与url二选一, 这个值在列表里面可以看到;
   *              url和id至少一个必须，优先通过id查找</li>
   *   </ul>
   *   其中以下字段为选填：
   *   <ul>
   *     <li>groupId - 0(默认分组),2(海外网站),3(国内竞价),4(客户测试) 默认为0
   *                   一般情况你不会使用这个参数，除非你有多个项目;如果你通过下面url方式查找，才会使用group_id</li>
   *   </ul>
   * </blockquote>
   * <p>本API接口对应的会员中心页面 - <a href="https://www.wechaturl.us/user/index.html#business_management/domain_url_list">https://www.wechaturl.us/user/index.html#business_management/domain_url_list</a></p>
   * <p>本API接口对应的文档页面 - <a href="https://wechaturl.gitbook.io/wechaturl/domain/domain_url_delete">https://wechaturl.gitbook.io/wechaturl/domain/domain_url_delete</a></p>
   *
   * @param urlParam 包含各参数实例对象
   * @return Response
   * @throws DefaultException        参数没有初始化
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
    }else{
      if(StringUtils.isEmpty(urlParam.getUrl())){
        throw new DefaultException("url和id，至少要传一个");
      }
      paraMap.put("url", urlParam.getUrl());
    }
    if (StringUtils.isNotEmpty(urlParam.getGroupId())) {
      paraMap.put("group_id", urlParam.getGroupId());
    }
    return httpClientTemplate.processPost(REQUEST_URL, paraMap, new TypeReference<Response<String>>() {
    });
  }

  /**
   * <p>修改防封网址</p>
   *
   * <blockquote>
   * <p>接口使用说明：</p>
   * 调用时，UrlParam对象以下字段必须填值：
   *   <ul>
   *     <li>appid - http://www.wechaturl.us/user/index.html 去免费获取appid</li>
   *     <li>appkey - http://www.wechaturl.us/user/index.html 去免费获取appkey</li>
   *     <li>url - 输入http(s)://开头的网址. 记住,填写的网址对应的域名必须提前添加到落地页域名列表,可以到这里去操作
   *               https://www.wechaturl.us/user/index.html#business_management/land_domain</li>
   *   </ul>
   *   其中以下字段为选填：
   *   <ul>
   *     <li>groupId - 0(默认分组),2(海外网站),3(国内竞价),4(客户测试) - 默认为0</li>
   *     <li>visitType - browser,frame,jump,android_browser - 默认为jump, 如果你不知道它含义请到会员中心页面版‘添加防封网址’查看</li>
   *     <li>newLandDomainUrl - 修改域名,默认不做修改.
   *                            http(s)://开头的域名,如https://xxxxx.com
   *                            记住,修改的新域名必须提前添加到落地页域名列表,可以到这里去操作
   *                            https://www.wechaturl.us/user/index.html#business_management/land_domain</li>
   *     <li>title - 网址的title标签,默认不做修改</li>
   *     <li>keywords - 网址的keywords标签,默认不做修改</li>
   *     <li>description - 网址的description标签,默认不做修改</li>
   *   </ul>
   * </blockquote>
   * <p>本API接口对应的会员中心页面 - <a href="https://www.wechaturl.us/user/index.html#business_management/domain_url_list">https://www.wechaturl.us/user/index.html#business_management/domain_url_list</a></p>
   * <p>本API接口对应的文档页面 - <a href="https://wechaturl.gitbook.io/wechaturl/domain/domain_url_edit">https://wechaturl.gitbook.io/wechaturl/domain/domain_url_edit</a></p>
   * @param urlParam 包含各参数实例对象
   * @return Response
   * @throws DefaultException        参数没有初始化
   * @throws JsonProcessingException Json转对象异常
   */
  public Response updateUrl(UrlParam urlParam) throws DefaultException, JsonProcessingException {
    CommonUtil.isNotNull(urlParam);
    Map<String, String> paraMap = new HashMap<>();
    paraMap.put("appid", urlParam.getAppid());
    paraMap.put("appkey", urlParam.getAppkey());
    paraMap.put("type", Const.OPERATION_EDIT);
    paraMap.put("url", urlParam.getUrl());
    if (StringUtils.isNotEmpty(urlParam.getGroupId())) {
      paraMap.put("group_id", urlParam.getGroupId());
    }
    if (StringUtils.isNotEmpty(urlParam.getVisitType())) {
      paraMap.put("visit_type", urlParam.getVisitType());
    }
    if (StringUtils.isNotEmpty(urlParam.getNewLandDomainUrl())) {
      paraMap.put("new_land_domain_url", urlParam.getNewLandDomainUrl());
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
    return httpClientTemplate.processPost(REQUEST_URL, paraMap, new TypeReference<Response<String>>() {
    });
  }

  /**
   * <p>获取防封网址列表</p>
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
   *     <li>groupId - 0(默认分组),2(海外网站),3(国内竞价),4(客户测试) 不设值则系统默认设为0</li>
   *     <li>url - 输入http(s)://开头的网址.默认不筛选网址
   *               记住,url对应的域名必须提前添加到落地页域名列表,可以到这里去操作
   *               https://www.wechaturl.us/user/index.html#business_management/land_domain</li>
   *     <li>page - 当前页面,默认为1</li>
   *     <li>rows - 每页面获取的条数,默认10,最大是50</li>
   *   </ul>
   * </blockquote>
   * <p>本API接口对应的会员中心页面 -
   * <a href="https://www.wechaturl.us/user/index.html#business_management/domain_url_list">https://www.wechaturl.us/user/index.html#business_management/domain_url_list</a>
   * </p>
   * <p>本API接口对应的文档页面 - <a href="https://wechaturl.gitbook.io/wechaturl/domain/domain_url_list">https://wechaturl.gitbook.io/wechaturl/domain/domain_url_list</a></p>
   * @param urlParam 包含各参数实例对象
   * @return Response
   * @throws DefaultException        参数没有初始化
   * @throws JsonProcessingException Json转对象异常
   */
  public Response<UrlArrayVO<UrlVO>> listUrl(UrlParam urlParam) throws DefaultException, JsonProcessingException {
    CommonUtil.isNotNull(urlParam);
    Map<String, String> paraMap = new HashMap<>();
    paraMap.put("appid", urlParam.getAppid());
    paraMap.put("appkey", urlParam.getAppkey());
    paraMap.put("type", Const.OPERATION_LIST);
    if (StringUtils.isNotEmpty(urlParam.getGroupId())) {
      paraMap.put("group_id", urlParam.getGroupId());
    }
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

  /**
   * <p>获取落地页域名列表</p>
   *
   * <p>通过API只能获取落地页域名.如果需要编辑/添加/删除落地页域名,请到网页版进行操作
   * https://www.wechaturl.us/user/index.html#business_management/land_domain</p>
   *
   * <blockquote>
   * <p>接口使用说明：</p>
   * 调用时，UrlParam对象以下字段必须填值：
   *   <ul>
   *     <li>appid - http://www.wechaturl.us/user/index.html 去免费获取appid</li>
   *     <li>appkey - http://www.wechaturl.us/user/index.html 去免费获取appkey</li>
   *   </ul>
   *   其中以下字段为选填：
   *   <ul>
   *       <li>domain - 如果有domain参数,则只筛选一条域名</li>
   *   </ul>
   * </blockquote>
   * <p>本API接口对应的会员中心页面 - <a href="https://www.wechaturl.us/user/index.html#business_management/land_domain">https://www.wechaturl.us/user/index.html#business_management/land_domain</a></p>
   * <p>本API接口对应的文档页面 - <a href="https://wechaturl.gitbook.io/wechaturl/domain/land_domain_list">https://wechaturl.gitbook.io/wechaturl/domain/land_domain_list</a></p>
   *
   * @param urlParam 其中appid, appkey 是必填字段，domain为选填字段
   * @return Response
   * @throws DefaultException        参数没有初始化
   * @throws JsonProcessingException Json转对象异常
   */
  public Response<UrlArrayVO<UrlVO>> listLandDomain(UrlParam urlParam) throws DefaultException, JsonProcessingException {
    CommonUtil.isNotNull(urlParam);
    Map<String, String> paraMap = new HashMap<>();
    paraMap.put("appid", urlParam.getAppid());
    paraMap.put("appkey", urlParam.getAppkey());
    paraMap.put("type", Const.OPERATION_LIST_LAND_DOMAIN);
    if (StringUtils.isNotEmpty(urlParam.getDomain())) {
      paraMap.put("domain", urlParam.getDomain());
    }
    return httpClientTemplate.processPost(REQUEST_URL, paraMap, new TypeReference<Response<UrlArrayVO<UrlVO>>>() {
    });
  }

  /**
   * <p>超高频域名检测</p>
   *
   * <blockquote>
   * <p>使用说明：</p>
   * 调用时，UrlParam对象以下字段必须填值：
   *   <ul>
   *     <li>appid - http://www.wechaturl.us/user/index.html 去免费获取appid</li>
   *     <li>appkey - http://www.wechaturl.us/user/index.html 去免费获取appkey</li>
   *     <li>url - 输入http(s)://开头的网址</li>
   *   </ul>
   *   其中以下字段为选填：
   *   <ul>
   *     <li>checkType - 'wechat', 'qq' 默认'wechat'</li>
   *   </ul>
   * </blockquote>
   * <p>本API接口对应的会员中心页面 -
   * <a href="https://www.wechaturl.us/user/index.html#business_management/land_domain">https://www.wechaturl.us/user/index.html#business_management/land_domain</a>
   * </p>
   * <p>本API接口对应的文档页面 - <a href="https://wechaturl.gitbook.io/wechaturl/domain/domain_url_highfrequencycheck">https://wechaturl.gitbook.io/wechaturl/domain/domain_url_highfrequencycheck</a></p>
   *
   * @param urlParam 包含各参数实例对象
   * @return Response DomainCheckResultVO
   * @throws DefaultException        参数没有初始化
   * @throws JsonProcessingException Json转对象异常
   */
  public Response<DomainCheckResultVO> highFrequencyCheck(UrlParam urlParam) throws DefaultException, JsonProcessingException {
    CommonUtil.isNotNull(urlParam);
    Map<String, String> paraMap = new HashMap<>();
    paraMap.put("appid", urlParam.getAppid());
    paraMap.put("appkey", urlParam.getAppkey());
    paraMap.put("type", Const.OPERATION_HIGH_FREQUENCY_CHECK);
    paraMap.put("url", urlParam.getUrl());
    if (StringUtils.isNotEmpty(urlParam.getCheckType())) {
      paraMap.put("check_type", urlParam.getCheckType());
    }
    return httpClientTemplate.processPost(REQUEST_URL, paraMap, new TypeReference<Response<DomainCheckResultVO>>() {
    });
  }

  /**
   * <p>动态设置任何URI参数</p>
   *
   * <blockquote>
   * <p>使用说明：</p>
   * 调用时，UrlParam对象以下字段必须填值：
   *   <ul>
   *     <li>appid - http://www.wechaturl.us/user/index.html 去免费获取appid</li>
   *     <li>appkey - http://www.wechaturl.us/user/index.html 去免费获取appkey</li>
   *     <li>requestUri - request_uri必须以'/'开头</li>
   *     <li>groupId - 0(默认分组),2(海外网站),3(国内竞价),4(客户测试)</li>
   *   </ul>
   *   其中以下字段为选填：
   *   <ul>
   *     <li>visitType - browser,frame,jump,android_browser - 默认为jump, 如果你不知道它含义请到会员中心页面版‘添加防封网址’查看</li>
   *     <li>title - 网址的title标签</li>
   *     <li>keywords - 网址的keywords标签</li>
   *     <li>description - 网址的description标签</li>
   *   </ul>
   * </blockquote>
   * <p>本API接口对应的会员中心页面 - <a href="https://www.wechaturl.us/user/index.html#business_management/domain_url_list">https://www.wechaturl.us/user/index.html#business_management/domain_url_list</a></p>
   * <p>本API接口对应的文档页面 - <a href="https://wechaturl.gitbook.io/wechaturl/domain/domain_url_anyurl">https://wechaturl.gitbook.io/wechaturl/domain/domain_url_anyurl</a></p>
   *
   * @param urlParam 包含各参数实例对象
   * @param hideQQMenu 是否隐藏QQ菜单
   * @return Response
   * @throws DefaultException        参数没有初始化
   * @throws JsonProcessingException Json转对象异常
   */
  public String dynamicSetURIParameters(UrlParam urlParam, boolean hideQQMenu) throws DefaultException, JsonProcessingException {
    CommonUtil.isNotNull(urlParam);
    String domainUrl = getDomainUrl(urlParam);
    domainUrl = domainUrl.endsWith("/") ? StringUtils.substring(domainUrl, 0, domainUrl.length() - 1) : domainUrl;
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    String urlParamJsonString = objectMapper.writeValueAsString(urlParam);
    String base64EncodedString = CharacterUtil.base64Encode(urlParamJsonString);
    String url = StringUtils.join(domainUrl, getRandomSymbol(), base64EncodedString);
    String weiboShortUrl = getWechatShortUrl(urlParam, url);
    if(hideQQMenu){
      return weiboShortUrl;
    }
    return  weiboShortUrl + "?_wv=2";
  }

  private String getDomainUrl(UrlParam urlParam) throws DefaultException, JsonProcessingException {
    UrlParam newUrlParam = new UrlParam(Integer.valueOf(urlParam.getAppid()), urlParam.getAppkey());
    newUrlParam.setLayerType("layer_top");
    newUrlParam.setStatus(4000);
    ShortUrlService shortUrlService = new ShortUrlService();
    Response<UrlArrayVO<EntryDomainUrlVO>> response = shortUrlService.listEntryDomainUrl(newUrlParam);
    if(!"1".equals(response.getCode())){
      throw new DefaultException(response.getMessage());
    }
    int size = response.getData().getCount();
    if( size == 0){
      throw new DefaultException("没有找到状态为4000的入口域名，请到https://www.wechaturl.us/user/index.html#business_management/user_short_domain_list 添加一个短网址(子)域名，类型选择<入口域名>");
    }
    int pointer = CommonUtil.getARandomNumber(0, size);
    EntryDomainUrlVO entryDomainUrlVO = response.getData().getList().get(pointer);
    return StringUtils.join(entryDomainUrlVO.getScheme(), entryDomainUrlVO.getDomain());
  }

  private String getWechatShortUrl(UrlParam urlParam, String url) throws DefaultException, JsonProcessingException {
    UrlParam newUrlParam = new UrlParam(Integer.valueOf(urlParam.getAppid()), urlParam.getAppkey(), url);
    ShortUrlService shortUrlService = new ShortUrlService();
    if(StringUtils.isNotEmpty(urlParam.getEntryType())){
      newUrlParam.setEntryType(urlParam.getEntryType());
    }
    Response<ShortUrlVO> response = shortUrlService.transferLongToShortUrl(newUrlParam);
    if(!"1".equals(response.getCode())){
      throw new DefaultException(response.getMessage());
    }
    return response.getData().getShortUrl();
  }

  private String getRandomSymbol(){
    String[] symbols = StringUtils.split(SYMBOLS, ",");
    int pointer = CommonUtil.getARandomNumber(0, symbols.length);
    return symbols[pointer];
  }
}
