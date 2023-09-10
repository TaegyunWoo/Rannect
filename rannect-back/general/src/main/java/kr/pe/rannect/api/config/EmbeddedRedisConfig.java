/**
 * author         : 우태균
 * description    : 내장 Redis 실행, 중단 관리 Config
 */
package kr.pe.rannect.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisExecProvider;
import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;
import redis.embedded.util.OS;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

@Slf4j
@Profile("local") //local properties 사용시, 활성화
@Configuration
public class EmbeddedRedisConfig {
  @Value("${spring.redis.port}")
  private int redisPort;
  @Value("${spring.redis.redis-m1-location}")
  private String redisForM1Location;

  private RedisServer redisServer;

  /**
   * 내장 Redis 실행 메서드
   * @throws IOException
   */
  @PostConstruct
  public void redisServer() throws IOException, URISyntaxException {
    RedisServerBuilder builder = RedisServer.builder();

    if (isArmMac()) { //만약 실행 환경이 Mac M1이라면
      RedisExecProvider redisExecProvider = RedisExecProvider.defaultProvider();

      redisExecProvider.override(OS.MAC_OS_X, redisForM1Location); //MAC OS용 Redis Server 실행 파일 변경
      builder.redisExecProvider(redisExecProvider); //적용
    }

    redisServer = RedisServer.builder()
        .port(redisPort)
        .setting("maxmemory 128M") //메모리 최대 크기
        .setting("appendonly no") //AOF OFF
        .setting("save \"\"") //RDB OFF
        .build();
    redisServer.start();
  }

  /**
   * 내장 Redis 중단 메서드
   */
  @PreDestroy
  public void stopRedis() {
    if (redisServer != null) {
      redisServer.stop();
    }
  }

  /**
   * 실행 환경이 Mac OS M1 인지 확인하는 메서드
   * @return
   */
  private boolean isArmMac() {
    return (Objects.equals(System.getProperty("os.arch"), "aarch64") ||
        Objects.equals(System.getProperty("os.arch"), "x86_64")) &&
        Objects.equals(System.getProperty("os.name"), "Mac OS X");
  }
}
