package sbnz.app.security;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserClaims {

	private Map<String, String> claims = new HashMap<String, String>();
	
	public Set<String> getClaims() {
		return claims.keySet();
	}
	
	public String getClaimValue(String claim) {
		return claims.get(claim);
	}
	
	public void setClaimValue(String claim, String value) {
		claims.put(claim, value);
	}
}
