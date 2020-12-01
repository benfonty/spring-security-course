package com.example.first;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@SpringBootTest
class FirstApplicationTests {

	@Test
	void testPasswordEncoders() {
		System.out.println(new BCryptPasswordEncoder().encode ("tatapouet"));
		System.out.println(new Pbkdf2PasswordEncoder().encode ("tatapouet"));
		System.out.println(new SCryptPasswordEncoder().encode ("tatapouet"));
	}

}
