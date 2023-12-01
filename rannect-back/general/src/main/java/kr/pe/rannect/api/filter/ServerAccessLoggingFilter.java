/**
 * author         : 우태균
 * description    : 서버 접속 기록 로깅
 */
package kr.pe.rannect.api.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Slf4j
public class ServerAccessLoggingFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    String reqRemoteAddr = httpServletRequest.getRemoteAddr();
    String requestURI = httpServletRequest.getRequestURI();
    log.info("[Access] User({}) request ({})", reqRemoteAddr, requestURI);

    chain.doFilter(request, response);
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
  }

  @Override
  public void destroy() {
    Filter.super.destroy();
  }
}
