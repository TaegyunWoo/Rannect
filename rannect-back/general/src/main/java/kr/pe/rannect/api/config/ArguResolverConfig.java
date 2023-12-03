/**
 * author         : 우태균
 * description    : Argument Resolver 관련 Configuration
 */
package kr.pe.rannect.api.config;

import kr.pe.rannect.api.controller.resolver.LoginInfoArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class ArguResolverConfig implements WebMvcConfigurer {
  private final LoginInfoArgumentResolver loginInfoArgumentResolver;

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(loginInfoArgumentResolver);
  }
}
