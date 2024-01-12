package com.br.srm.emprestimo.api.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Enumeration;

@Component
@Slf4j
public class LoggerInterceptor implements HandlerInterceptor  {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logRequest(request, null);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logResponse(request, response, null);
    }

    public void logRequest(HttpServletRequest request, Object body) {

        String bodyText = null;

        try {
            if (Strings.isNotBlank(bodyText)) {
                ObjectMapper mapper = new ObjectMapper();
                bodyText = mapper.writeValueAsString(body);
            }
        } catch (JsonProcessingException e) {
            bodyText = "No Body";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\n\nREQUEST ---> %s %s HTTP/1.1\n\n", request.getMethod(), request.getRequestURL()));

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            sb.append(String.format("%s: %s\n", key, value));
        }

        sb.append(String.format("\n%s", Strings.isNotBlank(bodyText) ? bodyText : "No Body"));
        sb.append(String.format("\n\n---> END REQUEST HTTP (%s-byte body)\n\n", Strings.isNotBlank(bodyText) ? bodyText.length() : 0));

        log.info(sb.toString());
    }

    public void logResponse(HttpServletRequest request, HttpServletResponse response, Object body) {

        String bodyText = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            bodyText = mapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            bodyText = "No Body";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\n\nRESPONSE <--- HTTP/1.1 %s (%sms)\n\n", response.getStatus(), 0));

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            sb.append(String.format("%s: %s\n", key, value));
        }

        sb.append(String.format("\n%s", Strings.isNotBlank(bodyText) ? bodyText : "No Body"));
        sb.append(String.format("\n\n<--- END RESPONSE HTTP (%s-byte body)\n\n", Strings.isNotBlank(bodyText) ? bodyText.length() : 0));

        log.info(sb.toString());
    }
}