package com.ibm.vms;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vms.model.StartProcessInstanceReqVO;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class ProcessIntanceTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper mMapper = new ObjectMapper();

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void StartProcessTest() throws Exception {
		StartProcessInstanceReqVO voStart = new StartProcessInstanceReqVO();
		voStart.setApplyUserId("alex");
		voStart.setBusinessKey("100");
		voStart.setInstanceKey("leaveprocess");

		mockMvc.perform(MockMvcRequestBuilders.post("/processInstance").content(asJsonString(voStart))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}

	public static String asJsonString(final Object obj) {
		try {
			ObjectMapper mapper = new ObjectMapper();
//			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//			ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//			System.out.print(ow.writeValueAsString(obj));
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
