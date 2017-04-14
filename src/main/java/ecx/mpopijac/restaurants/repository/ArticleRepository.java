package ecx.mpopijac.restaurants.repository;

import java.util.List;

import ecx.mpopijac.restaurants.models.Article;
import ecx.mpopijac.restaurants.models.User;

public interface ArticleRepository {
	Article save(Article article);

	List<Article> findAll();

	Article findById(int id);

	int deleteById(int id);

	int update(Article article);

	List<Article> findByAuthor(User author);
}
