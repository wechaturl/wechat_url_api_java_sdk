## api完整教程镜像版 https://wechaturl.gitbook.io/

# 这里是不死鸟微信防封杀系统免费版 java-sdk

>  如果你需要使用php-sdk,请到https://github.com/wechaturl/wechat_url_api

### 我们的官方网站:https://www.wechaturl.us , 为了使你更快更轻松的使用本API,可以先简单浏览api各个模块.[点我查看](https://wechaturl.gitbook.io/ "点我查看")


本sdk提供的服务类（接口）支持对应的API, 详情如下:
1. SingleUrlProtectService 任意网址防封api
2. DomainUrlProtectService 域名防封api
3. DomainUrlMonitorService 域名监控api
4. ShortUrlService 短网址api
5. ShieldService 屏蔽云端爬虫检测

#### 代码示例
#### 1. 任意网址防封API(SingleUrlProtectService)使用示例
##### 1.1 添加防封网址(addUrl)
      UrlParam urlParam = new UrlParam(your_app_id, your_app_key, url_you_want_to_add);
      SingleUrlProtectService singleUrlService = new SingleUrlProtectService();
      Response<WeiboVO> response = singleUrlService.addUrl(urlParam);
##### 1.2 修改防封网址(updateUrl) 
      UrlParam urlParam = new UrlParam(your_app_id, your_app_id, url_you_want_to_update);
      // example to update title and description
      urlParam.setTitle("testing").setDescription("for testing");
      SingleUrlProtectService singleUrlService = new SingleUrlProtectService();
      Response response = singleUrlService.updateUrl(urlParam);
##### 1.3 删除防封网址(deleteUrl) 
      UrlParam urlParam = new UrlParam(your_app_id, your_app_id, url_you_want_to_detele);
      SingleUrlProtectService singleUrlService = new SingleUrlProtectService();
      Response response = singleUrlService.deleteUrl(urlParam);
##### 1.4 获取防封网址列表(listUrl)
      UrlParam urlParam = new UrlParam(your_app_id, your_app_id);
      SingleUrlProtectService singleUrlService = new SingleUrlProtectService();
      Response<UrlArrayVO<UrlVO>> response = singleUrlService.listUrl(urlParam);
#### 2. 域名防封API(DomainUrlProtectService)使用示例 
##### 2.1 添加防封网址(addUrl)
      UrlParam urlParam = new UrlParam(your_app_id, your_app_key, url_you_want_to_add, groud_id);
      urlParam.setVisitType("browser").setTitle("test").setDescription("for testing use only");
      DomainUrlProtectService domainUrlProtectService = new DomainUrlProtectService();
      Response<WeiboVO> response = domainUrlProtectService.addUrl(urlParam);      
##### 2.2 修改防封网址(updateUrl)
      UrlParam urlParam = new UrlParam(your_app_id, your_app_key, url_you_want_to_update, groud_id);
      urlParam.setKeywords("test").setVisitType("jump");
      DomainUrlProtectService domainUrlService = new DomainUrlProtectService();
      Response response = domainUrlService.updateUrl(urlParam);
##### 2.3 删除防封网址(deleteUrl)
      UrlParam urlParam = new UrlParam(your_app_id, your_app_key, url_you_want_to_delete, groud_id);
      DomainUrlProtectService domainUrlService = new DomainUrlProtectService();
      Response response = domainUrlService.deleteUrl(urlParam);
##### 2.4 获取防封网址列表(listUrl)      
      UrlParam urlParam = new UrlParam(your_app_id, your_app_key);
      DomainUrlProtectService domainUrlService = new DomainUrlProtectService();
      Response<UrlArrayVO<UrlVO>> response = domainUrlService.listUrl(urlParam);
##### 2.5 获取落地页域名列表(listLandDomain)
      UrlParam urlParam = new UrlParam(your_app_id, your_app_key);
      DomainUrlProtectService domainUrlService = new DomainUrlProtectService();
      Response<UrlArrayVO<UrlVO>> response = domainUrlService.listLandDomain(urlParam);
##### 2.6 超高频域名检测(highFrequencyCheck)     
      UrlParam urlParam = new UrlParam(your_app_id, your_app_key, url_you_want_to_check);
      DomainUrlProtectService domainUrlProtectService = new DomainUrlProtectService();
      Response<DomainCheckResultVO> response = domainUrlProtectService.highFrequencyCheck(urlParam);
##### 2.7 动态设置任何URI参数(dynamicSetURIParameters)
      DomainUrlProtectService domainUrlProtectService = new DomainUrlProtectService();
      UrlParam urlParam = new UrlParam(VALID_APPID, VALID_APPKEY);
      urlParam.setGroupId(4);
      urlParam.setRequestUri("/user_test");
      String shortUrl = domainUrlProtectService.dynamicSetURIParameters(urlParam, false);      
#### 3. 域名监控API(DomainUrlMonitorService)使用示例
##### 3.1 添加-网址自动监控(addUrl) 
      UrlParam urlParam = new UrlParam(your_app_id, your_app_key, url_you_want_to_add);
      DomainUrlMonitorService urlService = new DomainUrlMonitorService();
      Response response = urlService.addUrl(urlParam);      
##### 3.2 修改-网址自动监控(updateUrl)  
      UrlParam urlParam = new UrlParam(your_app_id, your_app_key, url_you_want_to_update);
      // 修改监控频率(300)并让其立即生效
      urlParam.setFrequency(300).setIsMonitor(1);
      DomainUrlMonitorService urlService = new DomainUrlMonitorService();
      Response response = urlService.updateUrl(urlParam);     
##### 3.3 删除-网址自动监控(deleteUrl)   
      UrlParam urlParam = new UrlParam(your_app_id, your_app_key, url_you_want_to_detele);
      DomainUrlMonitorService urlService = new DomainUrlMonitorService();
      Response response = urlService.deleteUrl(urlParam);
##### 3.4 监控列表-网址自动监控(listUrl)    
      UrlParam urlParam = new UrlParam(your_app_id, your_app_key);
      DomainUrlMonitorService urlService = new DomainUrlMonitorService();
      Response<UrlArrayVO<DomainUrlMonitorVO>> response = urlService.listUrl(urlParam);
##### 3.5 支持频率-网址自动监控(listFrequency)    
      UrlParam urlParam = new UrlParam(your_app_id, your_app_key);
      DomainUrlMonitorService urlMonitorService = new DomainUrlMonitorService();
      Response<List<Integer>> response = urlMonitorService.listFrequency(urlParam);
##### 3.6 单次检测-网址实时监控(singleUrlCheck)
      UrlParam urlParam = new UrlParam(your_app_id, your_app_key, url_you_want_to_check);
      DomainUrlMonitorService urlMonitorService = new DomainUrlMonitorService();
      Response<DomainCheckResultVO> response = urlMonitorService.singleUrlCheck(urlParam);   
#### 4. 短网址API(ShortUrlService) 使用示例 
##### 4.1 获取入口域名池列表(listEntryDomainUrl)
      UrlParam urlParam = new UrlParam(your_app_id, your_app_key);
      ShortUrlService shortUrlService = new ShortUrlService();
      Response<UrlArrayVO<EntryDomainUrlVO>> response = shortUrlService.listEntryDomainUrl(urlParam);
##### 4.2 长网址转短网址(ShortUrlService)
      // 转微信短网址
      UrlParam urlParam = new UrlParam(your_app_id, your_app_key, url_you_want_to_transfer);
      ShortUrlService shortUrlService = new ShortUrlService();
      Response<ShortUrlVO> response = shortUrlService.transferLongToShortUrl(urlParam);     
      // 转微博短网址
      UrlParam urlParam = new UrlParam(your_app_id, your_app_key, url_you_want_to_transfer);
      urlParam.setEntryType("weibourl");
      ShortUrlService shortUrlService = new ShortUrlService();
      Response<ShortUrlVO> response = shortUrlService.transferLongToShortUrl(urlParam);
#### 5. 屏蔽云端爬虫检测API(ShieldService)使用示例
##### 5.1 屏蔽云端爬虫检测(shieldCloudCrawlerCheck)
      ShieldService shieldService = new ShieldService();
      UrlParam urlParam = new UrlParam(VALID_APPID, VALID_APPKEY);
      urlParam.setIp(your_ip_address);
      Map<String, String> dynamicParams = new HashMap<>();
      // 动态参数根据实际情况传相应的值，帮助api更精准识别服务器
      dynamicParams.put("test_ip", your_test_ip_address);
      dynamicParams.put("HTTP_CF_VISITOR", cloud_flare_scheme); //cloud flare scheme
      dynamicParams.put("HTTP_CF_CONNECTING_IP", cloud_flare_ip); //cloud flare ip
      dynamicParams.put("HTTP_X_REAL_IP", get_from_nginx); //nginx
      dynamicParams.put("HTTP_X_FORWARDED_FOR", get_from_nginx); //nginx
      dynamicParams.put("HTTP_X_CLIENT_SCHEME", aliyun_cdn_scheme); //aliyun cdn scheme
      dynamicParams.put("HTTP_ALI_CDN_REAL_IP", aliyun_cdn_ip); //aliyun cdn ip
      dynamicParams.put("REMOTE_ADDR", remote_address); //原生的ip
      dynamicParams.put("HTTP_X_PHOENIX_SCHEME", phonix_scheme); //不死鸟自定义scheme
      dynamicParams.put("HTTP_CLIENTIP", phonix_ip); //不死鸟自定义ip
      dynamicParams.put("HTTP_USER_AGENT", user_agent); //user-agent
      dynamicParams.put("HTTP_REFERER", http_referer); //来路
      ShieldCloudCheckResponse response = shieldService.shieldCloudCrawlerCheck(urlParam, dynamicParams);
      
### 和我们一起交流

> Telegram: @weixinfangfeng :https://t.me/weixinfangfeng

> skype: https://join.skype.com/invite/wU9pVGpzmpQs
