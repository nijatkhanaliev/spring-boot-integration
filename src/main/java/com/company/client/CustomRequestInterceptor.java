package com.company.client;

import com.company.utils.WebUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomRequestInterceptor implements RequestInterceptor {

    private final WebUtils webUtils;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String langValue = webUtils.getLangFromHeader();
        requestTemplate.header("lang", langValue);
    }
}
