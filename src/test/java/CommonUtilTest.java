import org.junit.Assert;
import org.junit.Test;
import www.wechaturl.us.fangfeng.sdk.utils.CommonUtil;

public class CommonUtilTest {

  @Test
  public void getResourceTest(){
    String version = CommonUtil.getResource("api.version");
    Assert.assertEquals("1.0.0", version);
  }

  @Test
  public void getSDKVersionTest(){
    String version = CommonUtil.getSDKVersion();
    Assert.assertEquals("java-1.0.0", version);
  }
}
