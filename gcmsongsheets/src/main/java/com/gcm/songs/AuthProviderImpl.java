package com.gcm.songs;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;

import com.zc.auth.AuthHeaderProvider;

@Configuration

public class AuthProviderImpl implements AuthHeaderProvider {
	private HttpServletRequest request;

	public AuthProviderImpl(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public String getHeaderValue(String s) {
		return request.getHeader(s);
	}
}