package ecx.mpopijac.restaurants.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ecx.mpopijac.restaurants.models.Article;
import ecx.mpopijac.restaurants.models.Comment;
import ecx.mpopijac.restaurants.models.ServiceStatus;
import ecx.mpopijac.restaurants.models.User;
import ecx.mpopijac.restaurants.repository.CommentRepository;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;

	@Transactional
	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}

	@Transactional
	public List<Comment> findAll() {
		return commentRepository.findAll();
	}

	@Transactional
	public Comment findById(int id) {
		return commentRepository.findById(id);
	}

	@Transactional
	public List<Comment> findByAuthor(User user) {
		return commentRepository.findByAuthor(user);
	}

	@Transactional
	public List<Comment> findAllApprovedCommentsByArticle(Article article) {
		return commentRepository.findAllApprovedCommentsByArticle(article);
	}

	@Transactional
	public ServiceStatus approveCommentByHash(String hash) {
		return ServiceStatus.returnStatus(commentRepository.approveCommentByHash(hash));
	}

	@Transactional
	public ServiceStatus delete(Comment comment) {
		return ServiceStatus.returnStatus(commentRepository.deleteById(comment.getId()));
	}

	@Transactional
	public ServiceStatus unapproveCommentById(int id) {
		return ServiceStatus.returnStatus(commentRepository.unapproveCommentById(id));
	}

	@Transactional
	public ServiceStatus approveCommentById(int id) {
		return ServiceStatus.returnStatus(commentRepository.approveCommentById(id));
	}

	@Transactional
	public ServiceStatus deleteById(int id) {
		return ServiceStatus.returnStatus(commentRepository.deleteById(id));
	}

}
