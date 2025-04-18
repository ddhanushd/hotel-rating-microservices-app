package com.microservices.gateway.models;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthResponse {

	private String userId;
	private String accessToken;
	private String refreshToken;

	private long expireAt;

	private Collection<String> authorities;

}
