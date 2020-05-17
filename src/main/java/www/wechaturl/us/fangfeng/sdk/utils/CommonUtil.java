package www.wechaturl.us.fangfeng.sdk.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import www.wechaturl.us.fangfeng.sdk.exception.DefaultException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

public class CommonUtil {

  private static final String RESOURCE_FILE = "resource.properties";

  public static void isNotNull(Object o) throws DefaultException {
    if (ObjectUtils.isEmpty(o)) {
      throw new DefaultException(String.format("参数对象为空！"));
    }
  }

  public static String getResource(String key) {
    String value = null;
    InputStream inputStream = null;
    try {
      Properties properties = new Properties();
      inputStream = CommonUtil.class.getClassLoader().getResourceAsStream(RESOURCE_FILE);
      properties.load(inputStream);
      value = properties.getProperty(key);
    } catch (IOException exception) {
      exception.printStackTrace();
    } finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException exception) {
          exception.printStackTrace();
        }
      }
    }
    return value;
  }

  public static String getSDKVersion() {
    return StringUtils.join("java-", getResource("api.version"));
  }

  /**
   * get a random number
   *
   * @param from from which number(inclusive)
   * @param to to witch number(exclusive)
   * @return a random number
   */
  public static int getARandomNumber(int from, int to) {
    return ThreadLocalRandom.current().nextInt(from, to);
  }
}
