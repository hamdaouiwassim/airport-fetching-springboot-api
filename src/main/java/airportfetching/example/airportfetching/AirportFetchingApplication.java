package airportfetching.example.airportfetching;

import airportfetching.example.airportfetching.model.Role;
import airportfetching.example.airportfetching.model.User;
import airportfetching.example.airportfetching.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaRepositories
public class AirportFetchingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportFetchingApplication.class, args);
	}
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args-> {
			userService.saveRole(new Role(null,"ROLE_ADMIN","this is user"));
			userService.saveRole(new Role(null,"ROLE_USER","this is user"));


//			userService.saveUser(new User((long) 1,"92045389" ,"wassim","hamdaoui","hamdqaoui", "hamdaoui@gmail.com" ,"123456789",new HashSet<>()));
//
//			userService.addToUser("hamdaoui@gmail.com","ROLE_USER");
		};
	}

}
