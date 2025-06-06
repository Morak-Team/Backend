package com.example.seoulpublicdata2025backend.domain.member.controller;

import com.example.seoulpublicdata2025backend.domain.member.util.CookieFactory;
import com.example.seoulpublicdata2025backend.domain.member.dto.KakaoIdStatusDto;
import com.example.seoulpublicdata2025backend.domain.member.dto.KakaoUserInfoResponseDto;
import com.example.seoulpublicdata2025backend.domain.member.service.KakaoService;
import com.example.seoulpublicdata2025backend.domain.member.service.MemberService;
import com.example.seoulpublicdata2025backend.domain.member.type.MemberStatus;
import com.example.seoulpublicdata2025backend.global.auth.jwt.JwtProvider;
import com.example.seoulpublicdata2025backend.global.swagger.annotations.member.KakaoLoginCheckDocs;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "카카오 로그인 API")
public class KakaoAuthController {

    private final KakaoService kakaoService;
    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    private static final String FRONT_BASEURL = "https://morak.site";

    @GetMapping("/auth/login/kakao")
    @KakaoLoginCheckDocs
    public ResponseEntity<Void> checkMemberSignUp(
            @RequestParam("code") String code
    ) {
        String accessToken = kakaoService.getAccessTokenFromKakao(code);
        KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);
        Long kakaoId = userInfo.getId();

        KakaoIdStatusDto kakaoIdStatusDto = memberService.initMember(kakaoId);
        String token = jwtProvider.createToken(kakaoId, kakaoIdStatusDto.getStatus());

        ResponseCookie accessCookie = CookieFactory.createCookie("access", token);
        ResponseCookie kakaoIdCookie = CookieFactory.createCookie("kakaoId", kakaoId.toString());
        List<ResponseCookie> cookies = List.of(accessCookie, kakaoIdCookie);

        HttpHeaders headers = setHttpHeaders(cookies, kakaoIdStatusDto);
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    private static HttpHeaders setHttpHeaders(
            List<ResponseCookie> cookies, KakaoIdStatusDto kakaoIdStatusDto
    ) {
        HttpHeaders headers = new HttpHeaders();
        for (ResponseCookie cookie : cookies) {
            headers.add(HttpHeaders.SET_COOKIE, cookie.toString());
        }
        if (kakaoIdStatusDto.getStatus().equals(MemberStatus.MEMBER)) {
            String redirectUrl = UriComponentsBuilder.fromUriString(FRONT_BASEURL)
                    .build()
                    .toUriString();
            headers.setLocation(URI.create(redirectUrl));
        } else if (kakaoIdStatusDto.getStatus().equals(MemberStatus.PRE_MEMBER)) {
            String redirectUrl = UriComponentsBuilder.fromUriString(FRONT_BASEURL + "/signup")
                    .build()
                    .toUriString();
            headers.setLocation(URI.create(redirectUrl));
        }
        return headers;
    }
}
