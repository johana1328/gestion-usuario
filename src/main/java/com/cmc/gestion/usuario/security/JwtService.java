package com.cmc.gestion.usuario.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtService {
	private static final String AUTHORITIES = "authorities";
	private int EXPIRATION_TIME;
	private String SECRET_KEY;
	private String token;
	
	public JwtService(String apiKey, int expiration,String token) {
		this.SECRET_KEY=apiKey;
		this.EXPIRATION_TIME=expiration;
		this.token=token;
	}

	public String createToken(UserDetail userDetails) {
		String username = userDetails.getUsername();
		List<String> authorities = userDetails.getAuthorities();
		return Jwts.builder().setSubject(username).claim(AUTHORITIES, authorities)
				.addClaims(getAdditionalInfo(userDetails.getAditionalInfo()))
				//.setClaims(getAdditionalInfo(userDetails.getAditionalInfo()))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}

	public Boolean hasTokenExpired(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration()
				.before(new Date());
	}

	public Boolean validateToken(UserDetail userDetails) {
		String username = extractUsername();
		return (userDetails.getUsername().equals(username) && !hasTokenExpired(token));

	}

	public String extractUsername() {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
	}

	
	public List<SimpleGrantedAuthority> getAuthorities() {
		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		@SuppressWarnings("unchecked")
		List<String> roles=(List<String>)claims.get(AUTHORITIES);
		List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
		for(String in :roles) {
			updatedAuthorities.add(new SimpleGrantedAuthority(in));
		}
		return updatedAuthorities ;
	}
	
	private Map<String,Object> getAdditionalInfo(Map<UserProperty,Object> aditionalInfo){
		Map<String, Object> additionalInfo= new HashMap<String, Object>();
		for (Map.Entry<UserProperty,Object> entry : aditionalInfo.entrySet()) {
			additionalInfo.put(entry.getKey().toString(), entry.getValue());
		}
		return additionalInfo;
	}
	
	public UserRequest getUserRequest() {
		UserRequest user = new UserRequest();
		Map<UserProperty, Object> propiedUser = new HashMap<UserProperty, Object>();
		Claims claims= Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		String usuario=claims.getSubject();
		propiedUser.put(UserProperty.NOMBRE, usuario);
		for(UserProperty property : UserProperty.values()) {
			String propiedad=property.toString();
			if(claims.containsKey(property.toString())) {
				propiedUser.put(property, claims.get(propiedad));
			}
		}
		 return user;
	} 

}
