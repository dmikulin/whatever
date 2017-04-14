package ecx.mpopijac.restaurants.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ecx.mpopijac.restaurants.models.Article;
import ecx.mpopijac.restaurants.models.User;

@Repository("articleRepository")
public class ArticleRepositoryImpl implements ArticleRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Article save(Article article) {
		try{
			em.persist(article);
			em.flush();
			return article;
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Article> findAll() {
		try{
			return em.createQuery("select a from Article a").getResultList();
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public Article findById(int id) {
		return em.find(Article.class, id);
	}

	@Override
	public int deleteById(int id) {
		try{
			Query query = em.createQuery("delete from Article a where a.id=:id");
			query.setParameter("id", id);
			return query.executeUpdate();
		}catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int update(Article article) {
		try{
			Query query = em.createQuery("update Article a set a.headline=:headline, a.imageLocation=:imageLocation, a.description=:description, a.restaurant=:restaurant, a.author=:author where a.id=:id");
			query.setParameter("headline", article.getHeadline());
			query.setParameter("imageLocation", article.getImageLocation());
			query.setParameter("description", article.getDescription());
			query.setParameter("restaurant", article.getRestaurant());
			query.setParameter("author", article.getAuthor());
			query.setParameter("id", article.getId());
			return query.executeUpdate();
		}catch (Exception e) {
			return -1;
		}
	}

	@Override
	public List<Article> findByAuthor(User author) {
		try{
			Query query = em.createQuery("select a from Article a where a.author=:author");
			query.setParameter("author", author);
			return query.getResultList();
		}catch (Exception e) {
			return null;
		}
	}

}
