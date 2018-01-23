package com.test.service.configuration;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

public abstract class AntPathRequestMatcherWrapper implements RequestMatcher {

    private AntPathRequestMatcher  delegate;

    public AntPathRequestMatcherWrapper(String pattern) {
        this.delegate = new AntPathRequestMatcher (pattern,null);
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        if (precondition(request)) {
            return delegate.matches(request);
        }
        return false;
    }

    protected abstract boolean precondition(HttpServletRequest request);

}
