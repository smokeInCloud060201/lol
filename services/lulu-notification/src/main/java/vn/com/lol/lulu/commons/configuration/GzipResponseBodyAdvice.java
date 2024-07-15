package vn.com.lol.lulu.commons.configuration;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import vn.com.lol.common.dto.response.BaseResponse;
import vn.com.lol.common.utils.JsonUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

@ControllerAdvice
@Component
public class GzipResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, org.springframework.core.MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  org.springframework.http.server.ServerHttpRequest request,
                                  org.springframework.http.server.ServerHttpResponse response) {

        if (body instanceof BaseResponse<?> baseResponse) {
            return compressResponse(baseResponse.getData(), response.getHeaders());
        } else {
            return compressResponse(body, response.getHeaders());
        }
    }

    private ResponseEntity compressResponse(Object body, HttpHeaders headers) {
        String json = JsonUtil.stringify(body);  // Assume JsonUtil is a utility to convert object to JSON string
        byte[] compressedData = compress(json.getBytes());

        headers.set(HttpHeaders.CONTENT_ENCODING, "gzip");
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(compressedData);
    }

    private byte[] compress(byte[] data) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
            gzipOutputStream.write(data);
            gzipOutputStream.finish();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}