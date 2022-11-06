package com.exercicio.modulo3.securitys;

import com.exercicio.modulo3.context.ApplicationContextLoad;
import com.exercicio.modulo3.models.UsuarioModel;
import com.exercicio.modulo3.repositorys.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@Service
@Component
public class JwtTokenAutenticationService {

    private static final long EXPERATION_TIME = 24*60*60*2;//Tempo de validade do token
    private static final String SECRET = "SenhaExtremamenteSecretaForte";

    private static final String TOKEN_PREFIX = "Bearer";

    private static final String HEADER_STRING = "Authorization";


    /*Montagem do token */
    public void addAuthentication(HttpServletResponse response, String username) throws IOException {
        String JWT = Jwts.builder()//Aqui é o gerador de TOKEN
                .setSubject(username)//Usuario do parametro
                .setExpiration(new Date(System.currentTimeMillis() +  EXPERATION_TIME))//tempo de expiração do TOKEN
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();//Compactação e criptografia

        String token = TOKEN_PREFIX +" "+ JWT;
        response.addHeader(HEADER_STRING, token);
        response.getWriter().write("{\"Authorization\":\""+token+"\"}");
    }
    /* Retorna USUARIO*/
    public Authentication getAuthentication(HttpServletRequest request){
        String token = request.getHeader(HEADER_STRING);
        if(token != null){
            String user = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody().getSubject();
            if(user != null){
                UsuarioModel usuarioModel = ApplicationContextLoad.getApplicationContext()
                        .getBean(UsuarioRepository.class).buscaUsuarioPorLogin(user);
                if(usuarioModel != null){
                    return new UsernamePasswordAuthenticationToken(
                            usuarioModel.getLogin(),
                            usuarioModel.getSenha(),
                            usuarioModel.getAuthorities());
                }
            }
        }
        return null;
    }


}
