package com.future.my.social.dao;

public interface OAuth2Response {
    /**
     * 소셜 로그인 제공자의 이름을 반환합니다. (예: google, kakao, naver)
     * @return 제공자 이름
     */
    String getProvider();

    /**
     * 제공자에서 부여한 고유 사용자 ID를 반환합니다.
     * @return 제공자의 사용자 ID
     */
    String getProviderId();

    /**
     * 사용자 이메일을 반환합니다.
     * @return 사용자 이메일
     */
    String getEmail();

    /**
     * 사용자의 이름 또는 닉네임을 반환합니다.
     * @return 사용자 이름
     */
    String getName();



}
