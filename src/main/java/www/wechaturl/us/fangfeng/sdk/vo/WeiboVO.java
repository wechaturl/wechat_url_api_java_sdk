package www.wechaturl.us.fangfeng.sdk.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeiboVO {
  @JsonProperty("weibo_shorturl")
  private String weiboShortUrl;

  public String getWeiboShortUrl() {
    return weiboShortUrl;
  }

  public void setWeiboShortUrl(String weiboShortUrl) {
    this.weiboShortUrl = weiboShortUrl;
  }
}
