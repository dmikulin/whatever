package ecx.mpopijac.restaurants.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ecx.mpopijac.restaurants.models.Article;
import ecx.mpopijac.restaurants.models.Operation;
import ecx.mpopijac.restaurants.models.Restaurant;
import ecx.mpopijac.restaurants.service.ArticleService;
import ecx.mpopijac.restaurants.service.FileService;
import ecx.mpopijac.restaurants.service.RestaurantService;
import ecx.mpopijac.restaurants.service.UserService;

@Controller
public class ArticleController {
	
	@Autowired
	private FileService fileService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/crud-article", method = RequestMethod.GET)
	public String crudArticlePage(HttpServletRequest request, Model model) {
		String operation = request.getParameter("operation");
		if (operation != null){
			Operation operationEnum = Operation.valueOf(operation);
			if(operationEnum == Operation.DELETE){
				try{
					int id =Integer.parseInt(request.getParameter("id"));
					articleService.deleteById(id);
				}catch(NumberFormatException e){
					System.err.println("String does not contain a parsable integer"+e.getMessage());
				}catch(Exception e){
					System.err.println(e.getMessage());
				}
			}
		}
		List<Article> articles = articleService.findAll();
		model.addAttribute("articles", articles);
		return "crud-article";
	}

	@RequestMapping(value = "/c-article", method = RequestMethod.GET)
	public String createArticlePage(Model model) {
		model.addAttribute("heading", "Add new article");
		model.addAttribute("buttonAction", "Add new article");
		model.addAttribute("article", new Article());
		model.addAttribute("operation", Operation.CREATE);
		List<Restaurant> restaurants = restaurantService.findAll();
		model.addAttribute("restaurants", restaurants);
		return "cu-article";
	}

	@RequestMapping(value = "/u-article", method = RequestMethod.GET)
	public String updateArticlePage(HttpServletRequest request, Model model) {
		model.addAttribute("heading", "Update article");
		model.addAttribute("buttonAction", "Update article");
		Article article;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			article = articleService.findById(id);
			article.setDescription(article.getDescriptionAsPlainText());
			model.addAttribute("article",article);
		}catch(NumberFormatException e){
			System.err.println("String does not contain a parsable integer"+e.getMessage());
			return "redirect:crud-article";
		}catch(Exception e){
			System.err.println(e.getMessage());
			return "redirect:crud-article";
		}
		model.addAttribute("operation", Operation.UPDATE);
		List<Restaurant> restaurants = restaurantService.findAll();
		model.addAttribute("restaurants", restaurants);
		return "cu-article";
	}

	// Fetch data and create/update article
	@RequestMapping(value = "/crud-article", method = RequestMethod.POST)
	public String addUpdateCreateArticlePage(HttpServletRequest request, Model model,
			@RequestParam("imageLocation") MultipartFile image) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		Operation operation = Operation.valueOf(request.getParameter("operation"));
		switch (operation) {
		case CREATE: {
			Article article = new Article();
			article.setHeadline(request.getParameter("headline"));
			article.setDescriptionAsHtml(request.getParameter("description"));
			article.setImageLocation(fileService.saveFile(image));
			article.setAuthor(userService.findByUsername(username));
			article.setRestaurant(restaurantService.findById(Integer.parseInt(request.getParameter("restaurant"))));
			articleService.save(article);
			break;
		}
		case UPDATE:
			Article article = new Article();
			article.setHeadline(request.getParameter("headline"));
			article.setDescriptionAsHtml(request.getParameter("description"));
			article.setId(Integer.parseInt(request.getParameter("id")));
			String filename = image.getOriginalFilename();
			if (filename != null && !filename.equals("")) {
				article.setImageLocation(fileService.saveFile(image));
			} else {
				article.setImageLocation(articleService.findById(article.getId()).getImageLocation());
			}
			article.setAuthor(userService.findByUsername(username));
			article.setRestaurant(restaurantService.findById(Integer.parseInt(request.getParameter("restaurant"))));
			articleService.update(article);
			break;
		}
		return "redirect:crud-article";
	}
}
