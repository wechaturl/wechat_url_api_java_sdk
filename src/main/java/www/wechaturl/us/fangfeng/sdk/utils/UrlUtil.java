package www.wechaturl.us.fangfeng.sdk.utils;

public class UrlUtil {
  private static final String DOMAIN = "domain";
  private static final String DOMAIN_URL_MONITOR_CYCLE_CHECK_URI = "domain.url.monitor.cycle.check.uri";
  private static final String DOMAIN_URL_MONITOR_SINGLE_CHECK_URI = "domain.url.monitor.single.check.uri";
  private static final String DOMAIN_URL_PROTECT_URI = "domain.url.protect.uri";
  private static final String SINGLE_URL_PROTECT_URI = "single.url.protect.uri";
  private static final String SHORT_URL_ENTRY_DOMAIN_URI = "short.url.entry.domain.uri";
  private static final String SHORT_URL_LONG_TO_SHORT_URI = "short.url.long.to.short.uri";
  private static final String SHIELD_CLOUD_CRAWLER_CHECK_URI = "shield.cloud.crawler.check.uri";

  public static String getDomain(){
    return CommonUtil.getResource(DOMAIN);
  }

  public static String getDomainUrlMonitorCycleCheckUrlUrl(){
    return getDomain() + CommonUtil.getResource(DOMAIN_URL_MONITOR_CYCLE_CHECK_URI);
  }

  public static String getDomainUrlMonitorSingleCheckUrl(){
    return getDomain() + CommonUtil.getResource(DOMAIN_URL_MONITOR_SINGLE_CHECK_URI);
  }

  public static String getDomainUrlProtectUrl(){
    return getDomain() + CommonUtil.getResource(DOMAIN_URL_PROTECT_URI);
  }

  public static String getSingleUrlProtectUrl(){
    return getDomain() + CommonUtil.getResource(SINGLE_URL_PROTECT_URI);
  }

  public static String getShortUrlEntryDomainUrl(){
    return getDomain() + CommonUtil.getResource(SHORT_URL_ENTRY_DOMAIN_URI);
  }

  public static String getShortUrlLongToShortUrl(){
    return getDomain() + CommonUtil.getResource(SHORT_URL_LONG_TO_SHORT_URI);
  }

  public static String getShieldCloudCrawlerCheckUrl(){
    return getDomain() + CommonUtil.getResource(SHIELD_CLOUD_CRAWLER_CHECK_URI);
  }
}
