package ecx.mpopijac.restaurants.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import ecx.mpopijac.restaurants.StaticResourceConfiguration;
import ecx.mpopijac.restaurants.models.Article;
import ecx.mpopijac.restaurants.models.Operation;
import ecx.mpopijac.restaurants.models.Restaurant;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleControllerTest {

	@InjectMocks
	private ArticleController articleController;
	
	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext wac;

	@Autowired
	Environment env;

	private String absolutePathToUploadImages;

	@Autowired
	private Filter springSecurityFilterChain;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build();
		String absolutePath = StaticResourceConfiguration.class.getProtectionDomain().getCodeSource().getLocation()
				.toString();
		absolutePath = absolutePath.replace("target/classes/", env.getProperty("upload.file.path"));
		this.absolutePathToUploadImages = absolutePath.replace("file:/", "");
	}

	@Test
	public void crudArticlePage_NoParameters() throws Exception {
		this.mockMvc.perform(get("/crud-article")).andExpect(status().is3xxRedirection());
	}

	@Test
	@WithMockUser("admin")
	public void crudArticlePage_AdminRoleNoParameters() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/crud-article")).andExpect(status().isOk())
				.andExpect(view().name("crud-article")).andReturn();

		List<Article> articles = (List<Article>) result.getModelAndView().getModel().get("articles");
		assertNotEquals(null, articles);
		assertTrue(articles.size() != 0);
	}

	@Test
	@Transactional
	@WithMockUser("admin")
	public void crudArticlePage_AdminRoleOnlyOperationDelete() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/crud-article").param("operation", "DELETE"))
				.andExpect(status().isOk()).andExpect(view().name("crud-article")).andReturn();

		List<Article> articles = (List<Article>) result.getModelAndView().getModel().get("articles");
		assertNotEquals(null, articles);
		assertTrue(articles.size() != 0);
	}

	@Test
	@Transactional
	@WithMockUser("admin")
	public void crudArticlePage_AdminRoleOperationAndWrongId() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/crud-article").param("operation", "DELETE").param("id", "1a"))
				.andExpect(status().isOk()).andExpect(view().name("crud-article")).andReturn();

		List<Article> articles = (List<Article>) result.getModelAndView().getModel().get("articles");
		assertNotEquals(null, articles);
		assertTrue(articles.size() != 0);
	}

	@Test
	@Transactional
	@WithMockUser("admin")
	public void crudArticlePage_AdminRoleGoodBothParameters() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/crud-article").param("operation", "DELETE").param("id", "1"))
				.andExpect(status().isOk()).andExpect(view().name("crud-article")).andReturn();

		List<Article> articles = (List<Article>) result.getModelAndView().getModel().get("articles");
		assertNotEquals(null, articles);
		assertTrue(articles.size() != 0);
	}

	@Test
	@Transactional
	@WithMockUser("admin")
	public void createArticlePage_AdminRole() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/c-article")).andExpect(status().isOk())
				.andExpect(view().name("cu-article")).andReturn();
		List<Restaurant> restaurants = (List<Restaurant>) result.getModelAndView().getModel().get("restaurants");
		assertNotEquals(null, restaurants);
		assertTrue(restaurants.size() != 0);

		Operation operation = (Operation) result.getModelAndView().getModel().get("operation");
		assertEquals(Operation.CREATE, operation);

		Article article = (Article) result.getModelAndView().getModel().get("article");
		assertNotNull(article);

		String heading = (String) result.getModelAndView().getModel().get("heading");
		assertEquals("Add new article", heading);

		String buttonAction = (String) result.getModelAndView().getModel().get("buttonAction");
		assertEquals("Add new article", buttonAction);

	}

	@Test
	@Transactional
	@WithMockUser("admin")
	public void updateArticlePage_AdminRoleNoIdParameter() throws Exception {
		this.mockMvc.perform(get("/u-article")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:crud-article"));
	}

	@Test
	@Transactional
	@WithMockUser("admin")
	public void updateArticlePage_AdminRoleGoodIdParameter() throws Exception {

		MvcResult result = this.mockMvc.perform(get("/u-article").param("id", "2")).andExpect(status().isOk())
				.andExpect(view().name("cu-article")).andReturn();

		List<Restaurant> restaurants = (List<Restaurant>) result.getModelAndView().getModel().get("restaurants");
		assertNotEquals(null, restaurants);
		assertTrue(restaurants.size() != 0);

		Operation operation = (Operation) result.getModelAndView().getModel().get("operation");
		assertEquals(Operation.UPDATE, operation);

		Article article = (Article) result.getModelAndView().getModel().get("article");
		assertNotEquals(null, article);

		String heading = (String) result.getModelAndView().getModel().get("heading");
		assertEquals("Update article", heading);

		String buttonAction = (String) result.getModelAndView().getModel().get("buttonAction");
		assertEquals("Update article", buttonAction);

	}

	@Test
	@Transactional
	@WithMockUser("admin")
	public void addCreateArticlePage_NoParameters() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/crud-article").with(csrf()))
				.andExpect(status().isBadRequest());
	}

	@Test
	@Transactional
	@WithMockUser("admin")
	public void addCreateArticlePage_CreateArticle() throws Exception {
		InputStream inputStream = new BufferedInputStream(
				new FileInputStream(new File(absolutePathToUploadImages + "/spring-boot.png")));
		MockMultipartFile imageLocation = new MockMultipartFile("imageLocation", inputStream);
		this.mockMvc
				.perform(MockMvcRequestBuilders.fileUpload("/crud-article").file(imageLocation).with(csrf())
						.param("operation", "CREATE").param("restaurant", "1").param("description", "description")
						.param("headline", "headline"))
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:crud-article"));
	}

	@Test
	@Transactional
	@WithMockUser("admin")
	public void addCreateArticlePage_UpdateArticleNoParameterId() throws Exception {
		InputStream inputStream = new BufferedInputStream(
				new FileInputStream(new File(absolutePathToUploadImages + "/spring-boot.png")));
		MockMultipartFile imageLocation = new MockMultipartFile("imageLocation", inputStream);
		this.mockMvc
				.perform(MockMvcRequestBuilders.fileUpload("/crud-article").file(imageLocation).with(csrf())
						.param("operation", "UPDATE").param("restaurant", "1").param("description", "description")
						.param("headline", "headline"))
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:crud-article"));

	}

	@Test
	@Transactional
	@WithMockUser("admin")
	public void addCreateArticlePage_UpdateArticle() throws Exception {
		InputStream inputStream = new BufferedInputStream(
				new FileInputStream(new File(absolutePathToUploadImages + "/spring-boot.png")));
		MockMultipartFile imageLocation = new MockMultipartFile("imageLocation", inputStream);
		this.mockMvc
				.perform(MockMvcRequestBuilders.fileUpload("/crud-article").file(imageLocation).with(csrf())
						.param("operation", "UPDATE").param("restaurant", "1").param("description", "description")
						.param("headline", "headline").param("id", "1"))
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:crud-article"));
	}

}
