package com.example.scbchallenge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class ScbchallengeApplicationTests {
	@Autowired
	private MockMvc mvc;
	//private ScbchallengeController controller;

//	@Test
//	void contextLoads() throws Exception {
//		assertThat(controller).isNotNull();
//	}
	
	@Test
	void requiredParameter() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/encodeTxt")).andDo(print()).andExpect(status().isBadRequest());
	}
	
	@Test
	void requiredParameterValue() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/encodeTxt?funnyStr={1}", "")).andDo(print()).andExpect(status().isOk())
			.andExpect(content().string(containsString("Parameter Value is required!")));
	}
	
	@Test
	void acceptEngCapLetter() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/encodeTxt?funnyStr=VGhlIFdvcmxk")).andDo(print()).andExpect(status().isOk())
			.andExpect(content().string(containsString("English Capital Letter can be acceptable only")));
	}
	
	@Test
	void encodeTxt1WhiteSpace() throws Exception {
		//Text is "THE WORLD"
		mvc.perform(MockMvcRequestBuilders.get("/encodeTxt?funnyStr=VEhFIFdPUkxE")).andDo(print()).andExpect(status().isOk())
			.andExpect(content().string(containsString("ZGxyb3cxZWh0")));
	}
	
	@Test
	void encodeTxtMultiWhiteSpace() throws Exception {
		//Text is "A HEN  HAS  MANY   CHICKS"
		mvc.perform(MockMvcRequestBuilders.get("/encodeTxt?funnyStr=QSBIRU4gIEhBUyAgTUFOWSAgIENISUNLUw==")).andDo(print()).andExpect(status().isOk())
			.andExpect(content().string(containsString("c2tjaWhjM3luYW0yc2FoMm5laDFh")));
	}


}
