package com.future.my.social.dao;

import java.util.Map;

public class GoogleResponse implements OAuth2Response {
	
	// json 형식을 담을 map(구글은 json형식으로 옴)
	private final Map<String, Object> attribute;
	
	public GoogleResponse(Map<String, Object> attrbute) {
		this.attribute = attrbute;
	}

	@Override
	public String getProvider() {
		return "google";
	}

	@Override
	public String getProviderId() {
		
		return attribute.get("sub").toString();
	}

	@Override
	public String getEmail() {
		
		return attribute.get("email").toString();
	}

	@Override
	public String getName() {
		
		return attribute.get("name").toString();
	}

}
