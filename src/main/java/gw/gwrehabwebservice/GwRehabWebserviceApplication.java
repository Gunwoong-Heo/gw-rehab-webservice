package gw.gwrehabwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// HelloControllerTest 를 위해 주석처리(`@EnableJpaAuditing`이 `@SpringBootApplication`과 함께 있다보니, `@WebMvcTest`에서도 스캔)
// `JpaConfig` 생성 후 `JpaConfig`에 `@EnableJpaAuditing`추가   
//@EnableJpaAuditing
@SpringBootApplication
public class GwRehabWebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GwRehabWebserviceApplication.class, args);
	}

}
