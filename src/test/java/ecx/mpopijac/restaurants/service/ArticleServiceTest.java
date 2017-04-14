package ecx.mpopijac.restaurants.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ecx.mpopijac.restaurants.models.Article;
import ecx.mpopijac.restaurants.models.Comment;
import ecx.mpopijac.restaurants.models.Restaurant;
import ecx.mpopijac.restaurants.models.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ArticleServiceTest {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private UserService userService;
	
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void save_SaveNull(){
		Article article = articleService.save(null);
		assertNull(article);
	}
	
	@Test
	public void save_SavePartOfParameters(){
		Article articleSave = new Article();
		articleSave.setHeadline("headline");
		articleSave.setDescription("description");
		articleSave.setImageLocation("./image.jpg");
		Article article = articleService.save(articleSave);
		assertEquals(articleSave, article);
	}
	
	@Test
	public void save_SaveAllParametersEmptyObjects(){
		Article articleSave = new Article();
		articleSave.setHeadline("headline");
		articleSave.setDescription("description");
		articleSave.setImageLocation("./image.jpg");
		articleSave.setAuthor(new User());
		articleSave.setComments(new ArrayList<Comment>());
		articleSave.setRestaurant(new Restaurant());
		Article article = articleService.save(articleSave);
		assertEquals(articleSave, article);
	}
	
	
	@Test
	public void findAll(){
		List<Article> articles = articleService.findAll();
		assertTrue(articles.size()!= 0);
	}
	
	@Test
	public void findById_IdNegativeNumber(){
		articleService.findById(-1);
	}
	
	@Test
	public void findById_IdNotExist(){
		Article article = articleService.findById(99999);
		assertEquals(null, article);
	}
	
	@Test
	public void findById_Exist(){
		Article article = articleService.findById(1);
		assertNotNull(article);
	}
	
	@Test
	public void delete_NullObject(){
		articleService.delete(null);
	}
	
	@Test
	public void delete_IdNotSet(){
		Article article = new Article();
		article.setDescription("description");
		article.setHeadline("headline");
		article.setImageLocation("imageLocation");
		articleService.delete(article);
	}
	
	@Test
	public void delete_IdSetProperly(){
		Article article = new Article();
		article.setId(3);
		articleService.delete(article);
	}
	
	@Test
	public void delete_IdSetWrongNegative(){
		Article article = new Article();
		article.setId(-1);
		articleService.delete(article);
	}
	
	@Test
	public void delete_IdSetWrongZero(){
		Article article = new Article();
		article.setId(0);
		articleService.delete(article);
	}
	
	@Test
	public void delete_IdSetWrongNotExist(){
		Article article = new Article();
		article.setId(99999);
		articleService.delete(article);
	}
	
	@Test
	public void findByAuthor_AuthorNull(){
		List<Article> articles = articleService.findByAuthor(null);
		assertTrue(articles.size()==0);
	}
	
	@Test
	public void findByAuthor_OnlyId(){
		User author = new User();
		author.setId(1);
		List<Article> articles = articleService.findByAuthor(author);
		assertNotNull(articles);
		assertTrue(articles.size()!=0);
	}
	
	@Test
	public void findByAuthor_AllParameters(){
		User author = userService.findById(1);
		List<Article> articles = articleService.findByAuthor(author);
		assertNotNull(articles);
		assertTrue(articles.size()!=0);
	}
	
	@Test
	public void findByAuthor_OnlyIdNotSet(){
		User user = userService.findById(1);
		User author = new User();
		author.setEmail(user.getEmail());
		author.setFirstName(user.getFirstName());
		author.setLastName(user.getLastName());
		author.setPassword(user.getPassword());
		author.setUsername(user.getUsername());
		author.setRole(user.getRole());
		List<Article> articles = articleService.findByAuthor(author);
		assertNotNull(articles);
		assertTrue(articles.size()!=0);
	}
	
	@Test
	public void findByAuthor_OnlyEmailSet(){
		User user = userService.findById(1);
		User author = new User();
		author.setEmail(user.getEmail());
		List<Article> articles = articleService.findByAuthor(author);
		assertNotNull(articles);
		assertTrue(articles.size()!=0);
	}
	
	@Test
	public void findByAuthor_OnlyUsernameSet(){
		User user = userService.findById(1);
		User author = new User();
		author.setUsername(user.getUsername());
		List<Article> articles = articleService.findByAuthor(author);
		assertNotNull(articles);
		assertTrue(articles.size()!=0);
	}
	
	
}
