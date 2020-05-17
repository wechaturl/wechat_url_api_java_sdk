package www.wechaturl.us.fangfeng.sdk.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import www.wechaturl.us.fangfeng.sdk.utils.CommonUtil;
import www.wechaturl.us.fangfeng.sdk.vo.Response;

import java.util.Map;

public class HttpClientTemplate {

  private HttpClient httpClient = new HttpClient();
  private ObjectMapper objectMapper = new ObjectMapper();

  public HttpClientTemplate(){
  }

  public <T> Response<T> processPost(String url, Map<String, String> params, TypeReference<Response<T>> valueType){
    params.put("api_version", CommonUtil.getSDKVersion());
    String result = httpClient.doPost(url, params);
    try {
      Response<T> response = objectMapper.readValue(result, valueType);
      response.setOriginalResponseBody(result);
      return response;
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return new Response<>();
  }
}
