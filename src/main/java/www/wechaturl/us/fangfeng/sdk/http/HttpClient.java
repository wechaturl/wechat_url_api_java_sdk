package www.wechaturl.us.fangfeng.sdk.http;


import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.retry.Retry;
import io.vavr.control.Try;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import www.wechaturl.us.fangfeng.sdk.utils.CommonUtil;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;

public class HttpClient {

  private static final int CONNECTION_REQUEST_TIMEOUT = 30000;
  private static final int SOCKET_TIMEOUT = 50000;
  private static final String UTF_8 = "UTF-8";

  private Logger LOG = LoggerFactory.getLogger(HttpClient.class);

  public HttpClient() {
  }

  public String doGet(String url, Map<String, String> paraMap) {
    SSLContext sslContext = null;
    try {
      sslContext = new SSLContextBuilder()
              .loadTrustMaterial(null, (certificate, authType) -> true)
              .build();
    } catch (Exception e) {
      LOG.error(e.getMessage());
      e.printStackTrace();
    }
    CloseableHttpClient client = HttpClients.custom()
            .setSSLContext(sslContext)
            .setSSLHostnameVerifier(new NoopHostnameVerifier())
            .build();
    CloseableHttpResponse response = null;
    String result = "";
    try {
      URIBuilder uriBuilder = new URIBuilder(url);
      uriBuilder.setParameters(convert(paraMap));
      HttpGet httpGet = new HttpGet(uriBuilder.build());
      httpGet.setConfig(getDefaultRequestConfig());
      response = client.execute(httpGet);
      if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
        HttpEntity entity = response.getEntity();
        result = EntityUtils.toString(entity, UTF_8);
      }
    } catch (Exception e) {
      LOG.error(e.getMessage());
      e.printStackTrace();
    } finally {
      if (Objects.nonNull(response)) {
        try {
          response.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (Objects.nonNull(client)) {
        try {
          client.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return result;
  }

  public String doPost(String url, Map<String, String> paraMap) {
    SSLContext sslContext = null;
    try {
      sslContext = new SSLContextBuilder()
              .loadTrustMaterial(null, (certificate, authType) -> true)
              .build();
    } catch (Exception e) {
      LOG.error(e.getMessage());
      e.printStackTrace();
    }
    CloseableHttpClient client = HttpClients.custom()
            .setSSLContext(sslContext)
            .setSSLHostnameVerifier(new NoopHostnameVerifier())
            .build();
    String result = null;
    CloseableHttpResponse response = null;
    try {
      HttpPost httpPost = new HttpPost(url);
      httpPost.setConfig(getDefaultRequestConfig());
      httpPost.setEntity(new UrlEncodedFormEntity(convert(paraMap), UTF_8));
      response = executeCircuitBreaker("Post service", () -> {
        try {
          return client.execute(httpPost);
        } catch (IOException e) {
          e.printStackTrace();
        }
        return null;
      }, null);
      if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
        HttpEntity entity = response.getEntity();
        result = EntityUtils.toString(entity, UTF_8);
      }
    } catch (Exception e) {
      LOG.error(">>>>>>> https post failed <<<<<<<<< ");
      LOG.error(e.getMessage());
      e.printStackTrace();
    } finally {
      if (Objects.nonNull(response)) {
        try {
          response.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (Objects.nonNull(client)) {
        try {
          client.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return result;
  }

  private RequestConfig getDefaultRequestConfig() {
    return RequestConfig.custom()
            .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
            .setSocketTimeout(SOCKET_TIMEOUT)
            .build();
  }

  private List<NameValuePair> convert(Map<String, String> paraMap) {
    List<NameValuePair> nameValuePairs = new ArrayList<>();
    if (ObjectUtils.isEmpty(paraMap)) {
      return nameValuePairs;
    }
    Set<String> keySet = paraMap.keySet();
    for (String key : keySet) {
      nameValuePairs.add(new BasicNameValuePair(key, paraMap.get(key)));
    }
    return nameValuePairs;
  }

  private CloseableHttpResponse executeCircuitBreaker(String circuitBreakerName, Supplier<CloseableHttpResponse> supplier, Supplier<CloseableHttpResponse> fallback) {
    CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults(circuitBreakerName);
    Supplier<CloseableHttpResponse> decoratedSupplier = CircuitBreaker.decorateSupplier(circuitBreaker, supplier);
    Retry retry = Retry.ofDefaults(circuitBreakerName);
    Supplier<CloseableHttpResponse> retrySupplier = Retry.decorateSupplier(retry, decoratedSupplier);
    if (fallback == null) {
      return Try.ofSupplier(retrySupplier).get();
    } else {
      return Try.ofSupplier(retrySupplier).recover(e -> {
        LOG.warn(String.format("Service %s is not available, calling fallback!", circuitBreakerName));
        return fallback.get();
      }).get();
    }
  }

}
