package ecx.mpopijac.restaurants.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ecx.mpopijac.restaurants.models.Article;
import ecx.mpopijac.restaurants.models.Comment;
import ecx.mpopijac.restaurants.models.User;

@Repository("commentRepository")
public class CommentRepositoryImpl implements CommentRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Comment save(Comment comment) {
		try{
			em.persist(comment);
			em.flush();
			return comment;
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public List<Comment> findAll() {
		try{
			return em.createQuery("select c from Comment c").getResultList();
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public Comment findById(int id) {
		return em.find(Comment.class, id);
	}

	@Override
	public List<Comment> findByAuthor(User user) {
		try{
			Query query = em.createQuery("select c from Comment c where c.author.id = :id");
			query.setParameter("id", user.getId());
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Comment> findAllApprovedCommentsByArticle(Article article) {
		try {
			Query query = em.createQuery("select c from Comment c where c.approved = :approved and c.article=:article");
			query.setParameter("approved", true);
			query.setParameter("article", article);
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int approveCommentByHash(String hash) {
		try {
			Query query = em.createQuery("update Comment c set c.approved=:true where c.hash=:hash and c.approved <> :true");
			query.setParameter("hash", hash);
			query.setParameter("true", true);
			return query.executeUpdate();
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int deleteById(int id) {
		try {
			Query query = em.createQuery("delete from Comment c where c.id=:id");
			query.setParameter("id", id);
			return query.executeUpdate();
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int unapproveCommentById(int id) {
		try {
			Query query = em.createQuery("update Comment c set c.approved=:true where c.id=:id and c.approved <> :true");
			query.setParameter("true", false);
			query.setParameter("id", id);
			return query.executeUpdate();
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int approveCommentById(int id) {
		try {
			Query query = em.createQuery("update Comment c set c.approved=:true where c.id=:id and c.approved <> :true");
			query.setParameter("id", id);
			query.setParameter("true", true);
			return query.executeUpdate();
		} catch (Exception e) {
			return -1;
		}
	}

}
