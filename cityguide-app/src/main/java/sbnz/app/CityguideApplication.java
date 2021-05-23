package sbnz.app;

import java.util.Scanner;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CityguideApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityguideApplication.class, args);
		waitForShutdownSignal();
	}

	@Bean
	public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("sbnz", "cityguide-kjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10_000);
		return kContainer;
	}
	
	private static void waitForShutdownSignal() {
		System.out.println("Press 'Enter' to terminate");
		Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("Exiting");
        scanner.close();
        System.exit(1);
	}
}
