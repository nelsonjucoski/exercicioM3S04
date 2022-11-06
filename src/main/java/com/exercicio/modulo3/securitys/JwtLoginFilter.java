package com.exercicio.modulo3.securitys;

import com.exercicio.modulo3.models.UsuarioModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * Configurando gerenciador de autenticação
     */
    protected JwtLoginFilter(String url, AuthenticationManager authenticationManager) {
        /**Obriga a autenticar a URL do parametro */
        super(new AntPathRequestMatcher(url));

        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        UsuarioModel usuarioModel = new ObjectMapper()
                .readValue(request.getInputStream(), UsuarioModel.class);

        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                usuarioModel.getLogin(), usuarioModel.getSenha()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
       new JwtTokenAutenticationService().addAuthentication(response, authResult.getName());
    }
}
