package ma.veviosys.recordsmanager;

import ma.veviosys.recordsmanager.entities.AppRole;
import ma.veviosys.recordsmanager.entities.AppUser;
import ma.veviosys.recordsmanager.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class AutheApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutheApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner start(AccountService accountService){
		return  args -> {
//			accountService.addNewRole(new AppRole(null,"USER"));
			accountService.addNewRole(new AppRole(null,"ADMIN"));
//			accountService.addNewRole(new AppRole(null,"MANAGER"));
//			accountService.addNewUser(new AppUser(null,"user1","1234",new ArrayList<>()));
			accountService.addNewUser(new AppUser(null,"admin","1234",new ArrayList<>()));
//			accountService.addNewUser(new AppUser(null,"manager","1234",new ArrayList<>()));
//			accountService.addRoleToUser("user1","USER");
//			accountService.addRoleToUser("admin","USER");
			accountService.addRoleToUser("admin","ADMIN");
//			accountService.addRoleToUser("admin","MANAGER");
//			accountService.addRoleToUser("manager","MANAGER");
//			accountService.addRoleToUser("manager","user");
		};
	};

}
