package com.amos.velatechjavainterviewassigment;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
classes = VelatechJavaInterviewAssigmentApplication.class)
@AutoConfigureMockMvc
public class VelatechJavaInterviewAssigmentApplicationTests {

	@Autowired
	MockMvc  mockMvc;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getCardStatsTest() throws Exception {

		Gson gson = new Gson();
		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.get("/api/card-scheme/stats?start=1&limit=3")
				.contentType(MediaType.APPLICATION_JSON)
		).andExpect(status().isOk())
				.andExpect(content().json("{\"success\":true}"));

		System.out.println(gson.toJson(resultActions.andReturn().getResponse().getContentAsString()));
	}
}
