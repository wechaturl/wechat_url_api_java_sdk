package www.wechaturl.us.fangfeng.sdk.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import www.wechaturl.us.fangfeng.sdk.exception.DefaultException;

import java.io.IOException;
import java.util.Properties;

public class CommonUtil {

  private static final String RESOURCE_FILE = "resource.properties";

  public static void isNotNull(Object o) throws DefaultException {
    if (ObjectUtils.isEmpty(o)) {
      throw new DefaultException(String.format("参数对象为空！"));
    }
  }

  public static String getResource(String key) {
    try {
      Properties properties = new Properties();
      properties.load(CommonUtil.class.getClassLoader().getResourceAsStream(RESOURCE_FILE));
      return properties.getProperty(key);
    } catch (IOException exception) {
      exception.printStackTrace();
    }
    return key;
  }

  public static String getSDKVersion(){
    return StringUtils.join("java-", getResource("api.version"));
  }
}
