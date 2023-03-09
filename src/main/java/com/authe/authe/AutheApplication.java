package com.authe.authe;

import com.authe.authe.entities.AppRole;
import com.authe.authe.entities.AppUser;
import com.authe.authe.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.ArrayList;

@SpringBootApplication

public class AutheApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutheApplication.class, args);
	}

	@Bean
	CommandLineRunner start(AccountService accountService){
		return  args -> {
			accountService.addNewRole(new AppRole(null,"USER"));
			accountService.addNewRole(new AppRole(null,"ADMIN"));
//			accountService.addNewRole(new AppRole(null,"MANAGER"));
//			accountService.addNewUser(new AppUser(null,"user1","1234",new ArrayList<>()));
			accountService.addNewUser(new AppUser(null,"admin","","yns@k.com","younes","1234", new ArrayList<>()));
			accountService.addNewUser(new AppUser(null,"user","","ismail@k.com","ismail","1234",new ArrayList<>()));
			accountService.addNewUser(new AppUser(null,"u","","achraf@k.com","achraf","1234",new ArrayList<>() ));
//			accountService.addNewUser(new AppUser(null,"manager","1234",new ArrayList<>()));
//			accountService.addRoleToUser("user1","USER");
//			accountService.addRoleToUser("admin","USER");
			accountService.addRoleToUser("admin","ADMIN");
			accountService.addRoleToUser("admin","USER");

			accountService.addRoleToUser("u","USER");
			accountService.addRoleToUser("user","USER");
//			accountService.addRoleToUser("admin","MANAGER");
//			accountService.addRoleToUser("manager","MANAGER");
//			accountService.addRoleToUser("manager","user");
		};
	};

}
