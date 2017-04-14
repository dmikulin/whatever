package ecx.mpopijac.restaurants.repository;

import java.util.List;

import ecx.mpopijac.restaurants.models.Article;
import ecx.mpopijac.restaurants.models.Comment;
import ecx.mpopijac.restaurants.models.User;

public interface CommentRepository {
	Comment save(Comment comment);

	List<Comment> findAll();

	List<Comment> findAllApprovedCommentsByArticle(Article article);

	Comment findById(int id);

	List<Comment> findByAuthor(User user);

	int approveCommentByHash(String hash);

	int deleteById(int id);

	int unapproveCommentById(int id);

	int approveCommentById(int id);
}
