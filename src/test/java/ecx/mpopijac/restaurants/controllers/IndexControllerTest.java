package ecx.mpopijac.restaurants.controllers;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ecx.mpopijac.restaurants.models.Article;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexControllerTest {

	@InjectMocks
	private IndexController indexController;

	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext wac;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void indexPageTest() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
		MvcResult result = this.mockMvc.perform(get("/index")).andExpect(status().isOk())
				.andExpect(view().name("index")).andReturn();

		List<Article> articles = (List<Article>) result.getModelAndView().getModel().get("articles");
		assertTrue(articles.size() != 0);

	}
}
