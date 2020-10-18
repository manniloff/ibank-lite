package com.ibank.lite.auth.filter;

import com.ibank.lite.auth.service.LoginDetailsService;
import com.ibank.lite.auth.util.JwtUtil;
import com.ibank.lite.model.User;
import com.ibank.lite.repository.UserRepository;
import com.ibank.lite.util.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    public static long id;
    public static Roles role;
    private final LoginDetailsService loginDetailsService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authorization = httpServletRequest.getHeader("Authorization");

        String email = null;
        String jwt = null;

        if (authorization != null && authorization.startsWith(("Bearer "))) {
            jwt = authorization.substring(7);
            email = jwtUtil.extractUsername(jwt);
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails loginDetails = this.loginDetailsService.loadUserByUsername(email);
            if (jwtUtil.validateToken(jwt, loginDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        loginDetails, null, loginDetails.getAuthorities()
                );
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(httpServletRequest)
                );
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            id = user.get().getId();
            role = user.get().getRoles();
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
