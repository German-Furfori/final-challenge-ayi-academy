package com.finalchallenge.app.controllers;

import com.finalchallenge.app.services.IExternalApiService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@Api(value = "Central Bank of the Argentine Republic API", tags = {"BCRA services"})
@Slf4j
@RequestMapping(value = "/api/bcra", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
public class ExternalApiController {

    IExternalApiService externalApiService;

    @GetMapping(
            value = "/getInflationInfo",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(
            value = "Retrieves data associated to the inflation of Argentina",
            httpMethod = "GET",
            response = Object.class
    )
    public ResponseEntity<?> getInflationInfo() {

        Object inflationInfo;

        inflationInfo = externalApiService.findInflationInfo();

        return ResponseEntity.ok(inflationInfo);
    }

}
