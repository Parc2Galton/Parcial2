package com.example.parcial.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.server.ServerWebExchange;


@ControllerAdvice
public class WebAdvice {

    @ModelAttribute("isDevserver")
    public Boolean getIsDevserver(ServerWebExchange exchange) {
        // Accede a los headers a través de ServerWebExchange en lugar de HttpServletRequest
        return "1".equals(exchange.getRequest().getHeaders().getFirst("X-Devserver"));
    }
}
