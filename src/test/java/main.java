import com.fasterxml.jackson.core.JsonProcessingException;
import www.wechaturl.us.fangfeng.sdk.exception.DefaultException;
import www.wechaturl.us.fangfeng.sdk.service.DomainUrlMonitorService;
import www.wechaturl.us.fangfeng.sdk.service.DomainUrlProtectService;
import www.wechaturl.us.fangfeng.sdk.service.ShieldService;
import www.wechaturl.us.fangfeng.sdk.service.ShortUrlService;
import www.wechaturl.us.fangfeng.sdk.service.SingleUrlProtectService;
import www.wechaturl.us.fangfeng.sdk.vo.DomainUrlMonitorVO;
import www.wechaturl.us.fangfeng.sdk.vo.Response;
import www.wechaturl.us.fangfeng.sdk.vo.UrlArrayVO;
import www.wechaturl.us.fangfeng.sdk.vo.UrlParam;


public class main {
    public static void main(String[] args) throws DefaultException, JsonProcessingException {
        System.out.println("Hello GOOGLE  Java");

        DomainUrlMonitorService DomainUrl= new DomainUrlMonitorService();


        UrlParam parme=new UrlParam(12345678,"GetAppKeyAtThe:https://www.wechaturl.us");


        Response<UrlArrayVO<DomainUrlMonitorVO>> res=DomainUrl.listUrl(parme);

        System.out.println("返回的code:"+res.getCode());
        System.out.println("返回的message:"+res.getMessage());
        System.out.println("返回的data:"+res.getData());


    }

}
