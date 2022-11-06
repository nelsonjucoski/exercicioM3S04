package com.exercicio.modulo3.securitys;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtApiAutenticacaoFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /**Estabelece a autenticaçõa para a requisição */
        Authentication authentication = new JwtTokenAutenticationService()
                .getAuthentication((HttpServletRequest) request);
        /**Coloca o processo de autenticação no spring */
        SecurityContextHolder.getContext().setAuthentication(authentication);
        /**Continua o processo */
        chain.doFilter(request, response);

    }
}
