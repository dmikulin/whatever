package ecx.mpopijac.restaurants.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ecx.mpopijac.restaurants.models.Article;
import ecx.mpopijac.restaurants.models.Comment;
import ecx.mpopijac.restaurants.models.User;
import ecx.mpopijac.restaurants.service.ArticleService;
import ecx.mpopijac.restaurants.service.CommentService;
import ecx.mpopijac.restaurants.service.MailService;
import ecx.mpopijac.restaurants.service.UserService;

@Controller
public class PublishedArticleController {

	@Autowired
	ArticleService articleService;

	@Autowired
	UserService userService;

	@Autowired
	CommentService commentService;

	@Autowired
	MailService mailService;

	@RequestMapping(value = "/published-article", method = RequestMethod.GET)
	public String publishedArticlePage(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		if (id == null || id.equals("")) {
			return "index";
		}
		Article article = articleService.findById(Integer.parseInt(id));
		model.addAttribute("article", article);
		model.addAttribute("comments", commentService.findAllApprovedCommentsByArticle(article));
		setUserDataToModel(model);
		return "published-article";
	}

	@RequestMapping(value = "/published-article", method = RequestMethod.POST)
	public String saveCommentPublishedArticlePage(HttpServletRequest request, Model model) {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String commentText = request.getParameter("comment");
		int articleId = Integer.parseInt(request.getParameter("id"));
		User user = userService.findByEmail(email);
		if (user == null) {
			user = new User(firstName, lastName, email);
			userService.save(user);
			user = userService.findByEmail(email);
		}
		Article article = articleService.findById(articleId);
		Comment comment = new Comment(commentText, user, article);
		commentService.save(comment);
		try {
			mailService.sendCommentEmail(user, comment);
		} catch (MailException | InterruptedException e) {
			System.out.println("Mail was not sent: " + e.getMessage());
		}
		model.addAttribute("article", article);
		List<Comment> comments = commentService.findAllApprovedCommentsByArticle(article);
		model.addAttribute("comments", comments);

		setUserDataToModel(model);
		return "published-article";
	}

	@RequestMapping(value = "/delete-comment", method = RequestMethod.POST)
	public String deleteCommentArticlePage(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		if (id == null || id.equals("")) {
			return "index";
		}
		Comment comment = commentService.findById(Integer.parseInt(id));
		if (comment == null) {
			return "index";
		}
		commentService.delete(comment);

		model.addAttribute("article", comment.getArticle());
		model.addAttribute("comments", commentService.findAllApprovedCommentsByArticle(comment.getArticle()));
		setUserDataToModel(model);
		return "published-article";
	}

	@RequestMapping(value = "/unapprove-comment", method = RequestMethod.POST)
	public String unapproveCommentArticlePage(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		if (id == null || id.equals("")) {
			return "index";
		}
		Comment comment = commentService.findById(Integer.parseInt(id));
		commentService.unapproveCommentById(comment.getId());

		model.addAttribute("article", comment.getArticle());
		model.addAttribute("comments", commentService.findAllApprovedCommentsByArticle(comment.getArticle()));
		setUserDataToModel(model);
		return "published-article";
	}

	private void setUserDataToModel(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if (!username.equals("anonymousUser")) {
			User user = userService.findByUsername(username);
			model.addAttribute("firstName", user.getFirstName());
			model.addAttribute("lastName", user.getLastName());
			model.addAttribute("email", user.getEmail());
		} else {
			model.addAttribute("firstName", "");
			model.addAttribute("lastName", "");
			model.addAttribute("email", "");
		}
	}
}
