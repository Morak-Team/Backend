package com.example.seoulpublicdata2025backend.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoTokenResponseDto {

    // 역직렬화를 위해 JsonProperty를 이용
    @JsonProperty("token_type") // 토큰 타입
    public String tokenType;
    @JsonProperty("access_token")   // 사용자 엑세스 토큰 값
    public String accessToken;
    @JsonProperty("id_token")   // ID 토큰 값
    public String idToken;
    @JsonProperty("expires_in") // 엑세스 토큰과 ID 토큰의 만료 시간(초)
    public String expiresIn;
    @JsonProperty("refresh_token")  // 사용자 리프레시 토큰 값
    public String refreshToken;
    @JsonProperty("refresh_token_expires_in")   // 리프레시 토큰 만료 시간(초)
    public String refreshTokenExpiresIn;
    @JsonProperty("scope")  // 인증된 사용자의 정보 조회 권한 범위
    public String scope;
}
