package ecx.mpopijac.restaurants.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ecx.mpopijac.restaurants.models.Article;
import ecx.mpopijac.restaurants.service.ArticleService;

@Controller
public class IndexController {

	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = "/*", method = RequestMethod.GET)
	public String indexPage(Model model) {
		List<Article> articles = articleService.findAll();
		model.addAttribute("articles", articles);
		return "index";
	}

}
