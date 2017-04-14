package ecx.mpopijac.restaurants.service;

import java.util.List;

import ecx.mpopijac.restaurants.models.Article;
import ecx.mpopijac.restaurants.models.Comment;
import ecx.mpopijac.restaurants.models.ServiceStatus;
import ecx.mpopijac.restaurants.models.User;

public interface CommentService {
	Comment save(Comment comment);

	List<Comment> findAll();

	List<Comment> findAllApprovedCommentsByArticle(Article article);

	Comment findById(int id);

	List<Comment> findByAuthor(User user);

	ServiceStatus approveCommentByHash(String hash);

	ServiceStatus delete(Comment comment);
	
	ServiceStatus deleteById(int id);

	ServiceStatus unapproveCommentById(int id);

	ServiceStatus approveCommentById(int id);
}
