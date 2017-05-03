package foi.core.whatever.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import foi.core.whatever.model.Token;
import foi.core.whatever.repositoryes.TokenRepository;

@Service("tokenService")
public class TokenServiceImpl implements TokenService {
	
	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public Token save(Token token) {
		return tokenRepository.save(token);
	}

	@Override
	public List<Token> findAll() {
		return tokenRepository.findAll();
	}

	@Override
	public Token findByTokenId(int tokenId) {
		return tokenRepository.findByTokenId(tokenId);
	}

	
}
