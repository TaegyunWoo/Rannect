/**
 * author         : 우태균
 * description    : 로그인된 사용자 정보를 컨트롤러에게 넘겨주는 Argument Resolver
 */
package kr.pe.rannect.api.controller.resolver;

import kr.pe.rannect.api.dto.LoginInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginInfoArgumentResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.getParameterType().equals(LoginInfo.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    long memberPk;
    HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();

    memberPk = (long) httpServletRequest.getAttribute("memberPk");
    return LoginInfo.builder()
        .memberPk(memberPk)
        .build();
  }
}
