package com.franciscosouza.memcached.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetSocketAddress;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import net.spy.memcached.MemcachedClient;

/**
 * Servlet Filter implementation class MemcachedFilter
 */
public class MemcachedFilter implements Filter {

    private MemcachedClient mmc;

    static class MemcachedHttpServletResponseWrapper extends HttpServletResponseWrapper {

        private StringWriter sw = new StringWriter();

        public MemcachedHttpServletResponseWrapper(HttpServletResponse response) {
            super(response);
        }

        public PrintWriter getWriter() throws IOException {
            return new PrintWriter(sw);
        }

        public ServletOutputStream getOutputStream() throws IOException {
            throw new UnsupportedOperationException();
        }

        public String toString() {
            return sw.toString();
        }
    }

    /**
     * Default constructor.
     */
    public MemcachedFilter() {
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        MemcachedHttpServletResponseWrapper wrapper = new MemcachedHttpServletResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, wrapper);
        
        HttpServletRequest inRequest = (HttpServletRequest) request;
        HttpServletResponse inResponse = (HttpServletResponse) response;
        
        String content = wrapper.toString();
        
        PrintWriter out = inResponse.getWriter();
        out.print(content);
        
        String key = inRequest.getRequestURI();
        mmc.delete(key);
        
        if (!inRequest.getMethod().equals("POST")) {
            mmc.set(key, 5, content);
        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        try {
            mmc = new MemcachedClient(new InetSocketAddress("localhost", 11211));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

}
