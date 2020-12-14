package com.fonty.resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
@AutoConfigureMockMvc
class OauthApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void allUsersCanAccessAll() throws Exception {
		mvc.perform(get("/all")).andExpect(status().is(200));
	}

	@Test
	void AnyUserCanNotAccessAdmin() throws Exception {
		mvc.perform(get("/admin")).andExpect(status().is(401));
	}

	@Test
	@WithMockUser(roles = {"ADMIN"})
	void AuthenticatedUserCanAccessAdmin() throws Exception {
		mvc.perform(get("/admin")).andExpect(status().is(401));
	}

	@Test
	@WithUserDetails("benoit@fonty.email")
	void AuthenticatedWithUserDetailUserCanAccessAdmin() throws Exception {
		mvc.perform(get("/admin")).andExpect(status().is(401));
	}

	@Test
	public void testCors() throws Exception {
		mvc.perform(
				options("/all")
						.header("Access-Control-Request-Method", "POST")
						.header("Origin", "www.titi.com")
		)
				.andExpect(status().isOk())
				.andExpect(header().string("Access-Control-Allow-Origin", "*"))
				.andExpect(header().string("Access-Control-Allow-Methods", "GET,POST"));
	}

}
