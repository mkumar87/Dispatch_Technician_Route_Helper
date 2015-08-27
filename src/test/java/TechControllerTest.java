package org.springframework.samples.mvc.simple;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.verizon.dispatch.route.web.controllers.DispatchController;

import org.junit.Test;

public class TechControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(TechControllerTest.class);

	/*	@Test
	public void indexTest() throws Exception {
		logger.info("Inside updateTest");
		standaloneSetup(new DispatchController()).build()
		.perform(get("/"))
		.andExpect(content().string(""));
	}*/
	@Test
	public void loginTest() throws Exception {
		logger.info("Inside loginTest");
		standaloneSetup(new DispatchController()).build()
		.perform(post("/login"))
		.andExpect(content().string(""));
	}


}