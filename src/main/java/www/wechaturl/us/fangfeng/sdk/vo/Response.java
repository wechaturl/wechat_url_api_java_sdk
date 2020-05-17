package www.wechaturl.us.fangfeng.sdk.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class Response<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String message;
  @JsonProperty("weibo_shorturl")
  private String weiboShortUrl;
  private String originalResponseBody;
  @JsonProperty("next_get_date")
  private String nextGetDate;
  @JsonProperty("next_get_time")
  private long nextGetTime;
  @JsonProperty("wechat_notification_result")
  private WechatNotificationResultVO wechatNotificationResult;
  private T data;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getWeiboShortUrl() {
    return weiboShortUrl;
  }

  public void setWeiboShortUrl(String weiboShortUrl) {
    this.weiboShortUrl = weiboShortUrl;
  }

  public String getOriginalResponseBody() {
    return originalResponseBody;
  }

  public void setOriginalResponseBody(String originalResponseBody) {
    this.originalResponseBody = originalResponseBody;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getNextGetDate() {
    return nextGetDate;
  }

  public Response<T> setNextGetDate(String nextGetDate) {
    this.nextGetDate = nextGetDate;
    return this;
  }

  public long getNextGetTime() {
    return nextGetTime;
  }

  public Response<T> setNextGetTime(long nextGetTime) {
    this.nextGetTime = nextGetTime;
    return this;
  }

  public WechatNotificationResultVO getWechatNotificationResult() {
    return wechatNotificationResult;
  }

  public Response<T> setWechatNotificationResult(WechatNotificationResultVO wechatNotificationResult) {
    this.wechatNotificationResult = wechatNotificationResult;
    return this;
  }
}
