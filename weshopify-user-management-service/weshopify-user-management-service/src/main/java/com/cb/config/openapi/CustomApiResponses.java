package com.cb.config.openapi;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "400", description = "Failure"),
        @ApiResponse(responseCode = "404", description = "Invalid Request"),
        @ApiResponse(responseCode = "403", description = "Unauthorized / Invalid Token")
})
public @interface CustomApiResponses {
}
