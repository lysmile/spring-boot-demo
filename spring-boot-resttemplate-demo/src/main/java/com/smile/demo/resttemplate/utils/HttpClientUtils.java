package com.smile.demo.resttemplate.utils;

import com.alibaba.fastjson.JSON;
import com.smile.demo.resttemplate.entity.CodeResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Http请求工具类
 * @author smile
 */
@Component
@Slf4j
public class HttpClientUtils {

    private final RestTemplate restTemplate;

    public HttpClientUtils(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Get
     */
    public String get(String uri, Map<String, String> param, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", token);
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        StringBuilder url = new StringBuilder();
        url.append(uri).append("?");
        param.forEach((k, v) -> url.append(k).append("=").append(v).append("&"));
        String getUrl = url.substring(0, url.length() - 1);
        ResponseEntity<CodeResult> response = restTemplate.exchange(getUrl, HttpMethod.GET, requestEntity, CodeResult.class);
        return parseCodeResult(response);
    }

    /**
     * post方式请求接口
     * - 参数形式：form-data
     */
    public String postFormData(String url, MultiValueMap<String, Object> paramMap, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", token);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        //用HttpEntity封装整个请求报文
        HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(paramMap, headers);
        ResponseEntity<CodeResult> response = restTemplate.postForEntity(url, files, CodeResult.class);
        return parseCodeResult(response);
    }

    /**
     * post方式请求接口
     * - 参数形式：json
     */
    public String postJson(String url, String param, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("token", token);
        HttpEntity<String> entity = new HttpEntity<>(param, headers);
        ResponseEntity<CodeResult> response = restTemplate.postForEntity(url, entity, CodeResult.class);
        return parseCodeResult(response);
    }

    /**
     * 解析云端服务接口返回值
     */
    public String parseCodeResult(ResponseEntity<CodeResult> response) {
        if (response.getStatusCodeValue() != HttpStatus.OK.value()) {
            throw new RuntimeException("网络请求失败,状态码:" + response.getStatusCodeValue());
        }
        CodeResult codeResult = response.getBody();
        assert codeResult != null;
        if (!codeResult.getSuccess()) {
            log.error("调用后台服务接口错误,返回值:{}", JSON.toJSON(codeResult));
            throw new RuntimeException("调用后台服务接口错误， 错误信息：" + codeResult.getMsg());
        }
        return JSON.toJSONString(codeResult.getData());
    }
}
