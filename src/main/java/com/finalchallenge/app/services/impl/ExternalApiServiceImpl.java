package com.finalchallenge.app.services.impl;

import com.finalchallenge.app.services.IExternalApiService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static com.finalchallenge.app.constants.ApiString.API_TOKEN;
import static com.finalchallenge.app.constants.ApiString.API_URL;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class ExternalApiServiceImpl implements IExternalApiService { // If the API goes down, I handle the exception on the GlobalHandlerException

    @Override
    public Object findInflationInfo() {

        WebClient client = WebClient.builder()
                .baseUrl(API_URL)
                .defaultHeader(HttpHeaders.AUTHORIZATION, API_TOKEN)
                .build();

        List inflationInfo = client.get()
                .retrieve()
                .bodyToMono(List.class)
                .block();

        assert inflationInfo != null;
        return inflationInfo.get(inflationInfo.size() - 1);

    }

}
