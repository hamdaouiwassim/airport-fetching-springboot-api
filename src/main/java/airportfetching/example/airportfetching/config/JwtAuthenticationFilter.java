package airportfetching.example.airportfetching.config;

import airportfetching.example.airportfetching.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;
    @Value("${secret.jwt}")
    private  String secretKey;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                    String authorisationHeader = request.getHeader(AUTHORIZATION);

                    if(authorisationHeader != null && authorisationHeader.startsWith("Bearer ")){
                        try{
                            String token = authorisationHeader.substring("Bearer ".length());
                            Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
                            JWTVerifier verifier = JWT.require(algorithm).build();
                            DecodedJWT decoderJwt = verifier.verify(token);
                            String username = decoderJwt.getSubject();
                            userRepository.findByEmail(username).orElseThrow(()-> new Exception("Invalid Token ...") );
                            String[] roles= decoderJwt.getClaim("roles").asArray(String.class);
                            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                            Arrays.stream(roles).forEach(role->authorities.add(new SimpleGrantedAuthority(role)));
                            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,null,authorities);
                            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                            filterChain.doFilter(request,response);
                        }catch(Exception e){
                                ErrorResponse errorResponse = new ErrorResponse(FORBIDDEN,e.getMessage());
                                response.setContentType(APPLICATION_JSON_VALUE);
                                response.setStatus(errorResponse.getStatusCodeValue());
                                new ObjectMapper().writeValue(response.getOutputStream(),errorResponse);
                        }
                    }else{
                        filterChain.doFilter(request,response);
                    }
    }
}
