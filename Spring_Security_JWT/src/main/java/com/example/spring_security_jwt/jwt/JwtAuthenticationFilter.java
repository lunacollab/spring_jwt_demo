package com.example.spring_security_jwt.jwt;

import com.example.spring_security_jwt.security.CustomUserDetailsService;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    JwtTokenProvider jwtTokenProvider;
    CustomUserDetailsService customUserDetailsService;

    String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            try{
              String jwt = getJwtFromRequest(request);
              if(StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
                  String userName = jwtTokenProvider.getUserNameFromJwt(jwt);
                  UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
                  if(userDetails != null) {
                      UsernamePasswordAuthenticationToken authentication =
                              new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                      SecurityContextHolder.getContext().setAuthentication(authentication);
                  }
              }
            }catch(Exception e){
              log.error("fail on set user authentication",e);
            }
            filterChain.doFilter(request, response);
    }
}
