/**
 * author         : 우태균
 * description    : JPA Configuration이 DDL AUTO인 경우, DB 테이블 및 칼럼 네이밍 전략.
 *                  모두 대문자 + 언더바 조합으로 생성함.
 */
package kr.pe.rannect.api.utils.jpa;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.util.Locale;

public class UpperCaseNamingStrategy extends CamelCaseToUnderscoresNamingStrategy {
  @Override
  protected Identifier getIdentifier(String name, boolean quoted, JdbcEnvironment jdbcEnvironment) {
    return new Identifier(name.toUpperCase(Locale.ROOT), quoted);
  }
}
