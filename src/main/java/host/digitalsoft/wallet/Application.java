package host.digitalsoft.wallet;

import host.digitalsoft.wallet.service.Test;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

	@Autowired
	Test t;

	public static void main(String[] args) {
		log.debug("===== STARTING THE APPLICATION =====");
		SpringApplication.run(Application.class, args);
		log.debug("===== APPLICATION END =====");
	}

	@Override
	public void run(String... args) throws Exception {
		log.debug("##### EXECUTING : command line runner #####");
		t.test();
	}

}
