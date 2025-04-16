package com.example.seoulpublicdata2025backend.global.exception.customException;

import com.example.seoulpublicdata2025backend.global.exception.errorCode.ErrorCode;

public class KakaoApiException extends AuthenticationException {
    public KakaoApiException(ErrorCode errorCode) {
        super(errorCode);
    }
}
