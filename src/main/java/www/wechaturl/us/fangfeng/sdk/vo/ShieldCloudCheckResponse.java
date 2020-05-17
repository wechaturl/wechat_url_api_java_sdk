package www.wechaturl.us.fangfeng.sdk.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


/**
 * Response for api 'shieldCloudCrawlerCheck'
 */
public class ShieldCloudCheckResponse extends Response implements Serializable {
  private static final long serialVersionUID = 1L;

  private String code;
  private String msg;
  private String originalResponseBody;
  private String ip;
  private String message;
  @JsonProperty("search_range")
  private List<String> searchRange;
  @JsonProperty("show_error_url")
  private String showErrorUrl;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  @Override
  public String getOriginalResponseBody() {
    return originalResponseBody;
  }

  @Override
  public void setOriginalResponseBody(String originalResponseBody) {
    this.originalResponseBody = originalResponseBody;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<String> getSearchRange() {
    return searchRange;
  }

  public void setSearchRange(List<String> searchRange) {
    this.searchRange = searchRange;
  }

  public String getShowErrorUrl() {
    return showErrorUrl;
  }

  public void setShowErrorUrl(String showErrorUrl) {
    this.showErrorUrl = showErrorUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ShieldCloudCheckResponse)) return false;
    ShieldCloudCheckResponse that = (ShieldCloudCheckResponse) o;
    return Objects.equals(getCode(), that.getCode()) &&
            Objects.equals(getMsg(), that.getMsg()) &&
            Objects.equals(getIp(), that.getIp()) &&
            Objects.equals(getMessage(), that.getMessage()) &&
            Objects.equals(getSearchRange(), that.getSearchRange()) &&
            Objects.equals(getShowErrorUrl(), that.getShowErrorUrl());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCode(), getMsg(), getIp(), getMessage(), getSearchRange(), getShowErrorUrl());
  }
}
