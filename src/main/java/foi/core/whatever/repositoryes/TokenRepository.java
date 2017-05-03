package foi.core.whatever.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import foi.core.whatever.model.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {

	Token findByTokenId(int tokenId);
	
}
