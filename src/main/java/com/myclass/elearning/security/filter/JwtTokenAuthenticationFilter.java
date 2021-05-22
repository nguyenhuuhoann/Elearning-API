package com.myclass.elearning.security.filter;

import com.myclass.elearning.exception.BadCredentialException;
import com.myclass.elearning.security.service.impl.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static com.myclass.elearning.security.util.TokenUtil.getUsernameFromJWT;
import static com.myclass.elearning.security.util.TokenUtil.validateToken;





@AllArgsConstructor
@Slf4j
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private static final String BAD_CREDENTIAL_MESSAGE = "Bad credentials.";

    public static final String AUTH_HEADER_KEY = "Authorization";

    public static final String BEARER_PREFIX = "Bearer ";

    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && validateToken(jwt)) {
                String username = getUsernameFromJWT(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            log.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTH_HEADER_KEY);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.replace(BEARER_PREFIX, Strings.EMPTY);
        }
        throw new BadCredentialException(BAD_CREDENTIAL_MESSAGE);
    }


}
