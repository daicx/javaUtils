package com.lenovo.javautils.utils;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by fengyiwei on 20/03/17.
 */
public class HttpClient {
    private static final Logger LOG = LoggerFactory.getLogger(HttpClient.class);

    private static volatile CloseableHttpClient httpClient = null;

    // 编码格式,发送编码格式统一用UTF-8
    private static final String ENCODING = "UTF-8";

    // 设置连接超时时间,单位毫秒
    private static final int CONNECT_TIMEOUT = 5000;

    // 请求响应超时时间,单位毫秒
    private static final int SOCKET_TIMEOUT = 5000;

    static {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        httpClientBuilder.setMaxConnTotal(10);
        httpClientBuilder.setConnectionManager(new PoolingHttpClientConnectionManager(1, TimeUnit.MINUTES));
        httpClientBuilder.setDefaultRequestConfig(RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build());
        httpClient = httpClientBuilder.build();
    }

    /**
     * Get请求
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String doGet(String url, Map<String, String> params) throws IOException {
        List<NameValuePair> nameValuePairs = Collections.emptyList();

        if (params != null) {
            nameValuePairs = Lists.newArrayList();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        RequestBuilder requestBuilder = RequestBuilder.get(url).addParameters(nameValuePairs.toArray(new NameValuePair[0]));
        requestBuilder.setCharset(StandardCharsets.UTF_8);
        HttpUriRequest httpUriRequest = requestBuilder.build();

        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpUriRequest);
            return EntityUtils.toString(httpResponse.getEntity(), ENCODING);
        } catch (IOException e) {
            LOG.error("[HttpUtil doGet] error url:{}, params:{}", url, params, e);
            throw e;
        }
    }

    public static String doGet(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
        List<NameValuePair> nameValuePairs = Collections.emptyList();

        if (params != null) {
            nameValuePairs = Lists.newArrayList();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        RequestBuilder requestBuilder = RequestBuilder.get(url)
                .addParameters(nameValuePairs.toArray(new NameValuePair[0]));
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            requestBuilder.addHeader(entry.getKey(), entry.getValue());
        }
        requestBuilder.setCharset(Charset.forName("UTF-8"));
        HttpUriRequest httpUriRequest = requestBuilder.build();

        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpUriRequest);
            return EntityUtils.toString(httpResponse.getEntity(), ENCODING);
        } catch (IOException e) {
            LOG.error("[HttpUtil doGet] error url:{}, params:{}", url, params, e);
            throw e;
        }
    }
    /**
     * Post请求
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String doPost(String url, Map<String, String> params) throws IOException {
        List<NameValuePair> nameValuePairs = Collections.emptyList();

        if (params != null) {
            nameValuePairs = Lists.newArrayList();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        RequestBuilder requestBuilder = RequestBuilder.post(url).addParameters(nameValuePairs.toArray(new NameValuePair[0]));
        requestBuilder.setCharset(StandardCharsets.UTF_8);
        HttpUriRequest httpUriRequest = requestBuilder.build();

        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpUriRequest);
            return EntityUtils.toString(httpResponse.getEntity(), ENCODING);
        } catch (IOException e) {
            LOG.error("[HttpUtil doPost] error url:{}, params:{}", url, params, e);
            throw e;
        }
    }

    public static HttpResponse doPost(String host, String path,
                                      Map<String, String> headers,
                                      Map<String, String> querys,
                                      String body)
            throws Exception {
        org.apache.http.client.HttpClient httpClient = wrapClient(host, path);
        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        if (headers!=null && headers.size()>0) {
            for (Map.Entry<String, String> e : headers.entrySet()) {
                request.addHeader(e.getKey(), e.getValue());
            }
        }
        if (StringUtils.isNotBlank(body)) {
            request.setEntity(new StringEntity(body, "utf-8"));
        }
        return httpClient.execute(request);
    }

    /**
     * 获取 HttpClient
     *
     * @param host
     * @param path
     * @return
     */
    private static org.apache.http.client.HttpClient wrapClient(String host, String path) {
        org.apache.http.client.HttpClient httpClient = HttpClientBuilder.create().build();
        if ((StringUtils.isNotBlank(host) && host.startsWith("https://")) || StringUtils.isBlank(host) && StringUtils.isNotBlank(path) && path.startsWith("https://")) {
            return sslClient();
        }
        return httpClient;
    }

    /**
     * 在调用SSL之前需要重写验证方法，取消检测SSL
     * 创建ConnectionManager，添加Connection配置信息
     *
     * @return HttpClient 支持https
     */
    private static org.apache.http.client.HttpClient sslClient() {
        try {
            // 在调用SSL之前需要重写验证方法，取消检测SSL
            X509TrustManager trustManager = new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] xcs, String str) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] xcs, String str) {
                }
            };
            SSLContext ctx = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
            ctx.init(null, new TrustManager[]{trustManager}, null);
            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(ctx, NoopHostnameVerifier.INSTANCE);
            // 创建Registry
            RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                    .setExpectContinueEnabled(Boolean.TRUE).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                    .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", socketFactory).build();
            // 创建ConnectionManager，添加Connection配置信息
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            CloseableHttpClient closeableHttpClient = HttpClients.custom().setConnectionManager(connectionManager)
                    .setDefaultRequestConfig(requestConfig).build();
            return closeableHttpClient;
        } catch (KeyManagementException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 构建请求的 url
     *
     * @param host
     * @param path
     * @param querys
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String buildUrl(String host, String path, Map<String, String> querys) throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        if (!StringUtils.isBlank(host)) {
            sbUrl.append(host);
        }
        if (!StringUtils.isBlank(path)) {
            sbUrl.append(path);
        }
        if (null != querys) {
            StringBuilder sbQuery = new StringBuilder();
            for (Map.Entry<String, String> query : querys.entrySet()) {
                if (0 < sbQuery.length()) {
                    sbQuery.append("&");
                }
                if (StringUtils.isBlank(query.getKey()) && !StringUtils.isBlank(query.getValue())) {
                    sbQuery.append(query.getValue());
                }
                if (!StringUtils.isBlank(query.getKey())) {
                    sbQuery.append(query.getKey());
                    if (!StringUtils.isBlank(query.getValue())) {
                        sbQuery.append("=");
                        sbQuery.append(URLEncoder.encode(query.getValue(), "utf-8"));
                    }
                }
            }
            if (0 < sbQuery.length()) {
                sbUrl.append("?").append(sbQuery);
            }
        }
        return sbUrl.toString();
    }

    /**
     * 读取inputStream 到 string
     *
     * @param input
     * @param encoding
     * @return
     * @throws IOException
     */
    public static String inputStream2String(InputStream input, String encoding)
            throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input,
                encoding));
        StringBuilder result = new StringBuilder();
        String temp = null;
        while ((temp = reader.readLine()) != null) {
            result.append(temp);
        }
        return result.toString();
    }
}
