package umc.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
// JpaAuditing(감시, 감사 기능) 사용이 가능하도록 추가
// 엔티티 객체가 생성되거나 변경될 때 자동으로 값을 등록하게 한다
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
