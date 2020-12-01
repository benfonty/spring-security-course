package com.example.first;

import javax.servlet.*;
import java.io.IOException;

public class MySecurityFilter implements Filter {

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        System.out.println("before");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("after");
    }


}
