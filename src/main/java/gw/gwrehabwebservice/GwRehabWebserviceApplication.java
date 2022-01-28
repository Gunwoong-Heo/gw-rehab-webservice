package gw.gwrehabwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GwRehabWebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GwRehabWebserviceApplication.class, args);
	}

}
