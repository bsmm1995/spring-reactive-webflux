package com.bp.ensayo.config;

import com.bp.ensayo.util.HttpUtil;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        errorAttributes.put("path", request.path());
        Throwable error = getError(request);
        HttpStatus errorStatus = HttpUtil.determineHttpStatus(error);
        errorAttributes.put("status", errorStatus.value());
        errorAttributes.put("message", HttpUtil.determineMessage(error));
        return errorAttributes;
    }
}
