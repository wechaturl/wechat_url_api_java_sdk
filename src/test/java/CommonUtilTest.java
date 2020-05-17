import org.junit.Assert;
import org.junit.Test;
import www.wechaturl.us.fangfeng.sdk.utils.CommonUtil;

public class CommonUtilTest {

  @Test
  public void getResourceTest() {
    String version = CommonUtil.getResource("api.version");
    Assert.assertEquals("1.0.0", version);
  }

  @Test
  public void getSDKVersionTest() {
    String version = CommonUtil.getSDKVersion();
    Assert.assertEquals("java-1.0.0", version);
  }

  @Test
  public void getAnRandomNumberTest() {
    for (int i = 0; i < 1000; i++) {
      Assert.assertEquals(1, CommonUtil.getARandomNumber(1, 2));
      Assert.assertTrue(CommonUtil.getARandomNumber(0, 6) >= 0
              && CommonUtil.getARandomNumber(0, 6) < 6);
    }
  }

  @Test
  public void test() {
  }
}
