import org.junit.Assert;
import org.junit.Test;
import www.wechaturl.us.fangfeng.sdk.utils.UrlUtil;

public class UrlUtilTest {

  @Test
  public void getDomainUrlTest(){
    Assert.assertEquals("https://wechaturl.us", UrlUtil.getDomain());
  }

  @Test
  public void getDomainUrlMonitorCycleCheckUrlUrlTest(){
    Assert.assertEquals("https://wechaturl.us/api/UrlCycleCheck.json", UrlUtil.getDomainUrlMonitorCycleCheckUrlUrl());
  }

  @Test
  public void getDomainUrlMonitorSingleCheckUrlTest(){
    Assert.assertEquals("https://wechaturl.us/api/UrlCheck.json", UrlUtil.getDomainUrlMonitorSingleCheckUrl());
  }

  @Test
  public void getDomainUrlProtectUrlTest(){
    Assert.assertEquals("https://wechaturl.us/api/DomainShortUrl.json", UrlUtil.getDomainUrlProtectUrl());
  }

  @Test
  public void getSingleUrlProtectUrlTest(){
    Assert.assertEquals("https://wechaturl.us/api/SingleShortUrl.json", UrlUtil.getSingleUrlProtectUrl());
  }

  @Test
  public void getShortUrlEntryDomainUrlTest(){
    Assert.assertEquals("https://wechaturl.us/api/ShortDomain.json", UrlUtil.getShortUrlEntryDomainUrl());
  }

  @Test
  public void getShortUrlLongToShortUrlTest(){
    Assert.assertEquals("https://wechaturl.us/api/Long2ShortUrl.json", UrlUtil.getShortUrlLongToShortUrl());
  }

  @Test
  public void getShieldCloudCrawlerCheckUrl(){
    Assert.assertEquals("https://wechaturl.us/api/CheckIp.json", UrlUtil.getShieldCloudCrawlerCheckUrl());
  }
}
