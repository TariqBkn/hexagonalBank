package com.hexa.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexa.api.wrapper.request.AccountActionRequest;
import com.hexa.domain.exceptions.InsufficientFundsException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BankApplicationTests {
		@Autowired
		private MockMvc mockMvc;

		@Test
		void testPerformAccountAction_deposit() throws Exception {
			int accountId = 1;
			long amount = 100;
			AccountActionRequest accountActionRequest = new AccountActionRequest(amount);

			mockMvc.perform(patch("/v2/account/{id}/deposit", accountId)
							.contentType(MediaType.APPLICATION_JSON)
							.content(asJsonString(accountActionRequest)))
					.andExpect(status().isOk());
		}

		@Test
		void testPerformAccountAction_withdraw() throws Exception {
			int accountId = 1;
			long amount = 20;
			AccountActionRequest accountActionRequest = new AccountActionRequest(amount);

			mockMvc.perform(patch("/v2/account/{id}/withdrawal", accountId)
							.contentType(MediaType.APPLICATION_JSON)
							.content(asJsonString(accountActionRequest)))
					.andExpect(status().isOk());
		}

		@Test
		void testPerformAccountAction_withdraw_when_insufficientFundsException() throws Exception {
			int accountId = 1;

			mockMvc.perform(patch("/v2/account/{id}/withdrawal", accountId)
							.contentType(MediaType.APPLICATION_JSON)
							.content(new InsufficientFundsException().getMessage()))
					.andExpect(status().isBadRequest());
		}

		@Test
		void testGetTransactionsHistory() throws Exception {
			mockMvc.perform(MockMvcRequestBuilders
							.get("/v2/account/{id}/transaction", 1)
							.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
		}

		private static String asJsonString(final Object obj) {
			try {
				return new ObjectMapper().writeValueAsString(obj);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
