package toy.toyproject3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Toyproject3Application {

	public static void main(String[] args) {
		SpringApplication.run(Toyproject3Application.class, args);
	}

}
