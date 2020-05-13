package www.wechaturl.us.fangfeng.sdk.utils;

public class UrlUtil {
  private static final String DOMAIN_URL_MONITOR_CYCLE_CHECK_URL = "domain.url.monitor.cycle.check.url";
  private static final String DOMAIN_URL_MONITOR_SINGLE_CHECK_URL = "domain.url.monitor.single.check.url";
  private static final String DOMAIN_URL_PROTECT_URL = "domain.url.protect.url";
  private static final String SINGLE_URL_PROTECT_URL = "single.url.protect.url";
  private static final String SHORT_URL_ENTRY_DOMAIN_URL = "short.url.entry.domain.url";
  private static final String SHORT_URL_LONG_TO_SHORT_URL = "short.url.long.to.short.url";
  private static final String SHIELD_CLOUD_CRAWLER_CHECK_URL = "shield.cloud.crawler.check.url";

  public static String getDomainUrlMonitorCycleCheckUrlUrl(){
    return CommonUtil.getResource(DOMAIN_URL_MONITOR_CYCLE_CHECK_URL);
  }

  public static String getDomainUrlMonitorSingleCheckUrl(){
    return CommonUtil.getResource(DOMAIN_URL_MONITOR_SINGLE_CHECK_URL);
  }

  public static String getDomainUrlProtectUrl(){
    return CommonUtil.getResource(DOMAIN_URL_PROTECT_URL);
  }

  public static String getSingleUrlProtectUrl(){
    return CommonUtil.getResource(SINGLE_URL_PROTECT_URL);
  }

  public static String getShortUrlEntryDomainUrl(){
    return CommonUtil.getResource(SHORT_URL_ENTRY_DOMAIN_URL);
  }

  public static String getShortUrlLongToShortUrl(){
    return CommonUtil.getResource(SHORT_URL_LONG_TO_SHORT_URL);
  }

  public static String getShieldCloudCrawlerCheckUrl(){
    return CommonUtil.getResource(SHIELD_CLOUD_CRAWLER_CHECK_URL);
  }
}
