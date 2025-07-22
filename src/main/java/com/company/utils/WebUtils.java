package com.company.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebUtils {

    private final HttpServletRequest request;

    public String getLangFromHeader(){
       return request.getHeader("lang");
    }


}
