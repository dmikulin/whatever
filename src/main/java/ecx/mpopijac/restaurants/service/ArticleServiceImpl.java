package ecx.mpopijac.restaurants.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ecx.mpopijac.restaurants.models.Article;
import ecx.mpopijac.restaurants.models.ServiceStatus;
import ecx.mpopijac.restaurants.models.User;
import ecx.mpopijac.restaurants.repository.ArticleRepository;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleRepository articleRepository;

	@Transactional
	public Article save(Article article) {
		return articleRepository.save(article);
	}

	@Transactional
	public List<Article> findAll() {
		return articleRepository.findAll();
	}

	@Transactional
	public Article findById(int id) {
		return articleRepository.findById(id);
	}

	@Transactional
	public ServiceStatus delete(Article article) {
		return ServiceStatus.returnStatus(articleRepository.deleteById(article.getId()));
	}

	@Transactional
	public ServiceStatus update(Article article) {
		return ServiceStatus.returnStatus(articleRepository.update(article));
	}

	@Transactional
	public List<Article> findByAuthor(User author) {
		return articleRepository.findByAuthor(author);
	}

	@Transactional
	public ServiceStatus deleteById(int id) {
		return ServiceStatus.returnStatus(articleRepository.deleteById(id));
	}

}
