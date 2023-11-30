/**
 * author         : 우태균
 * description    : 인터셉터 Configuration
 */
package kr.pe.rannect.api.config;

import kr.pe.rannect.api.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
  private final AuthInterceptor authInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    //인가 인터셉터
    registry.addInterceptor(authInterceptor)
        .order(0)
        .addPathPatterns("/**")
        .excludePathPatterns("/swagger-ui/**",
            "/swagger/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/",
            "/members",
            "/members/sign-in",
            "/error");
  }
}
