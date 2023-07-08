package com.bp.ensayo.util;

import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

public class HttpUtil {
    private HttpUtil() {
    }

    public static HttpStatus determineHttpStatus(Throwable error) {
        if (error instanceof ResponseStatusException responseStatusException) {
            HttpStatus httpStatus = HttpStatus.resolve(responseStatusException.getStatusCode().value());
            if (httpStatus != null) {
                return httpStatus;
            }
        }
        return getResponseStatusAnnotation(error).getValue("code", HttpStatus.class).orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static String determineMessage(Throwable error) {
        if (error instanceof BindingResult) {
            return error.getMessage();
        }
        if (error instanceof ResponseStatusException responseStatusException) {
            return responseStatusException.getReason();
        }
        String reason = getResponseStatusAnnotation(error).getValue("reason", String.class).orElse("");
        if (StringUtils.hasText(reason)) {
            return reason;
        }
        return (error.getMessage() != null) ? error.getMessage() : "";
    }

    public static MergedAnnotation<ResponseStatus> getResponseStatusAnnotation(Throwable error) {
        return MergedAnnotations
                .from(error.getClass(), MergedAnnotations.SearchStrategy.TYPE_HIERARCHY)
                .get(ResponseStatus.class);
    }
}
