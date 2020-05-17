package www.wechaturl.us.fangfeng.sdk.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import www.wechaturl.us.fangfeng.sdk.exception.DefaultException;
import www.wechaturl.us.fangfeng.sdk.http.HttpClient;
import www.wechaturl.us.fangfeng.sdk.utils.CommonUtil;
import www.wechaturl.us.fangfeng.sdk.utils.UrlUtil;
import www.wechaturl.us.fangfeng.sdk.vo.ShieldCloudCheckResponse;
import www.wechaturl.us.fangfeng.sdk.vo.UrlParam;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ShieldService {

  private final ObjectMapper objectMapper = new ObjectMapper();
  private final HttpClient httpClient = new HttpClient();

  private String shieldUrl = UrlUtil.getShieldCloudCrawlerCheckUrl();

  public ShieldService(){
  }

  /**
   * 本功能的作用是屏蔽厂商的云端检测功能
   *
   * <p>使用说明：</p>
   * 调用时，UrlParam对象以下字段必须填值：
   *   <ul>
   *     <li>appid - http://www.wechaturl.us/user/index.html 去免费获取appid</li>
   *     <li>appkey - http://www.wechaturl.us/user/index.html 去免费获取appkey</li>
   *     <li>ip - ip地址，目前只支持ipV4</li>
   *   </ul>
   * @param urlParam 参数对象，详见使用说明
   * @param dynamicParam 动态参数
   * @return ShieldCloudCheckResponse
   * @throws DefaultException 通常是参数没有初始化
   * @throws JsonProcessingException 通常是参数没有初始化
   */
  public ShieldCloudCheckResponse shieldCloudCrawlerCheck(UrlParam urlParam, Map<String, String> dynamicParam) throws DefaultException, JsonProcessingException {
    CommonUtil.isNotNull(urlParam);
    Map<String, String> paraMap = new HashMap<>();
    paraMap.put("appid", urlParam.getAppid());
    paraMap.put("appkey", urlParam.getAppkey());
    paraMap.put("ip", urlParam.getIp());
    Set<String> keys = dynamicParam.keySet();
    for(String key : keys){
      if(StringUtils.isNotEmpty(dynamicParam.get(key))){
        paraMap.put(key, dynamicParam.get(key));
      }
    }
    String res = httpClient.doPost(shieldUrl, paraMap);
    ShieldCloudCheckResponse response = objectMapper.readValue(res, new TypeReference<ShieldCloudCheckResponse>(){});
    response.setOriginalResponseBody(res);
    return response;
  }
}
