package foi.core.whatever.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Token {

	@Id
	@GeneratedValue
	@NotNull
	@Column(name="token_id")
	private int tokenId;

	@Column(name="yaas_token")
	private String yaasToken;

	public Token() {
		super();
	}

	public int getTokenId() {
		return tokenId;
	}

	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}

	public String getYaasToken() {
		return yaasToken;
	}

	public void setYaasToken(String yaasToken) {
		this.yaasToken = yaasToken;
	}

	
}
