package foi.core.whatever.services;

import java.util.List;

import foi.core.whatever.model.Token;

public interface TokenService {

	Token save(Token token);

	List<Token> findAll();
	
	Token findByTokenId(int tokenId);
	
}
