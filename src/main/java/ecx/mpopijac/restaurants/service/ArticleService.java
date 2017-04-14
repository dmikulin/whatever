package ecx.mpopijac.restaurants.service;

import java.util.List;

import ecx.mpopijac.restaurants.models.Article;
import ecx.mpopijac.restaurants.models.ServiceStatus;
import ecx.mpopijac.restaurants.models.User;

public interface ArticleService {
	Article save(Article article);

	List<Article> findAll();

	Article findById(int id);

	ServiceStatus delete(Article article);
	
	ServiceStatus deleteById(int id);

	ServiceStatus update(Article article);
	
	List<Article> findByAuthor(User author);
}
