package com.board.test.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

public class naverLogin {

	private final static String CLIENT_ID = "O8qsNWUCU97qfj7SPNpT";
	private final static String CLIENT_SECRET = "HTxWiJ0K7y";
	private final static String REDIRECT_URI = "http://localhost:8882/callback.do";
	private final static String SESSION_STATE = "oauth_state";

	private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";

	/* 네이버 아이디로 인증 URL 생성 method */
	public String getAuthorizationUrl(HttpSession session) {

		/* 세션 유효성 검증을 위하여 난수를 생성 */
		String state = generateRandomString();
		/* 생성한 난수 값을 session에 저장 */
		setSession(session, state);

		/* Scribe에서 제공하는 인증 URL 생성 기능을 이용하여 네아로 인증 URL 생성 */
		OAuth20Service oauthService = new ServiceBuilder().apiKey(CLIENT_ID).apiSecret(CLIENT_SECRET)
				.callback(REDIRECT_URI).state(state).build(naverLoginApi.instance());

		return oauthService.getAuthorizationUrl();

	}

	/* 네이버아이디로 callback 처리 및 accesstoken 획득 method */
	public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws IOException {
		/* callback으로 전달받은 세션검증용 난수값과 세션에 저장되어있는 값이 일치하는 지 확인 */
		String sessionState = getSession(session);

		OAuth2AccessToken accessToken = null;

		if (StringUtils.pathEquals(sessionState, state)) {
			OAuth20Service oauthService = new ServiceBuilder().apiKey(CLIENT_ID).apiSecret(CLIENT_SECRET)
					.callback(REDIRECT_URI).state(state).build(naverLoginApi.instance());

			accessToken = oauthService.getAccessToken(code);

		}

		return accessToken;
	}

	/* 세션 유효성 검증을 위한 난수 생성기 */
	private String generateRandomString() {
		return UUID.randomUUID().toString();
	}

	/* http session에 데이터 저장 */
	private void setSession(HttpSession session, String state) {
		session.setAttribute(SESSION_STATE, state);
	}

	/* http sessions에서 데이터 가져오기 */
	private String getSession(HttpSession session) {
		return (String) session.getAttribute(SESSION_STATE);
	}

	/* Access Token을 이용하여 네이버 사용자 프로필 API를 호출 */
	public String getUserProfile(OAuth2AccessToken oauthToken) throws IOException {

		OAuth20Service oauthService = new ServiceBuilder().apiKey(CLIENT_ID).apiSecret(CLIENT_SECRET)
				.callback(REDIRECT_URI).build(naverLoginApi.instance());

		OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauthService);
		oauthService.signRequest(oauthToken, request);
		Response response = request.send();
		return response.getBody();
	}
}
