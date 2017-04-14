package ecx.mpopijac.restaurants.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ecx.mpopijac.restaurants.models.Comment;
import ecx.mpopijac.restaurants.models.ServiceStatus;
import ecx.mpopijac.restaurants.models.User;
import ecx.mpopijac.restaurants.service.CommentService;
import ecx.mpopijac.restaurants.service.MailService;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private MailService mailService;

	@RequestMapping(value = "/comment-approve", method = RequestMethod.GET)
	public String approveCommentByEmail(HttpServletRequest request, Model model) {
		String hash = request.getParameter("id");
		ServiceStatus status = commentService.approveCommentByHash(hash);
		switch (status) {
		case SUCCESS:
			model.addAttribute("status", "Successfully approved");
			break;
		case ERROR:
			model.addAttribute("status", "The comment has already been approved or the error occurred");
			break;
		case UNKNOWN_ERROR:
			model.addAttribute("status", "Unknown error has occurred");
			break;
		}
		return "comment-approve";
	}

	@RequestMapping(value = "/comments", method = RequestMethod.GET)
	public String commentsPage(HttpServletRequest request, Model model) {
		model.addAttribute("comments", commentService.findAll());
		return "comments";
	}

	@RequestMapping(value = "/comment-reply", method = RequestMethod.GET)
	public String commentReplayPage(HttpServletRequest request, Model model) {
		String commentId = request.getParameter("id");
		try{
			Comment comment = commentService.findById(Integer.parseInt(commentId));
			String messageContent = "\n\nYour comment on article "+comment.getArticle().getHeadline()+":\n> "+comment.getMessageContent().replace("<br/>", "\n>");	
			comment.setMessageContent(messageContent);
			model.addAttribute("comment",comment);
		}catch (Exception e) {
			model.addAttribute("comment", new Comment("",new User(),null));
			System.out.println(e.getMessage());
		}
		return "comment-reply";
	}
	
	@RequestMapping(value = "/comment-reply", method = RequestMethod.POST)
	public String sendMailOnComment(HttpServletRequest request, Model model) {
		String commentId = request.getParameter("id");
		String mailMessage = request.getParameter("message");
		try{
			Comment comment = commentService.findById(Integer.parseInt(commentId));
			mailService.sendReplayEmail(mailMessage, comment.getAuthor());
		}catch (MailException | InterruptedException e) {
			System.out.println("Mail was not sent: "+e.getMessage());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		model.addAttribute("comments",commentService.findAll());
		return "comments";
	}
	
	

	@RequestMapping(value = "/comment-unapprove", method = RequestMethod.POST)
	public String commentUnapproveAction(HttpServletRequest request, Model model) {
		String commentId = request.getParameter("id");
		if (commentId != null && !(commentId.equals(""))) {
			commentService.unapproveCommentById(Integer.parseInt(commentId));
		}
		model.addAttribute("comments", commentService.findAll());
		return "comments";
	}

	@RequestMapping(value = "/comment-approve", method = RequestMethod.POST)
	public String commentApproveAction(HttpServletRequest request, Model model) {
		String commentId = request.getParameter("id");
		if (commentId != null && !(commentId.equals(""))) {
			commentService.approveCommentById(Integer.parseInt(commentId));
		}
		model.addAttribute("comments", commentService.findAll());
		return "comments";
	}

	@RequestMapping(value = "/comment-delete", method = RequestMethod.POST)
	public String commentDeleteAction(HttpServletRequest request, Model model) {
		String commentId = request.getParameter("id");
		if (commentId != null && !(commentId.equals(""))) {
			Comment comment = commentService.findById(Integer.parseInt(commentId));
			if (comment != null) {
				commentService.deleteById(comment.getId());
			}
		}
		model.addAttribute("comments", commentService.findAll());
		return "comments";
	}

}
