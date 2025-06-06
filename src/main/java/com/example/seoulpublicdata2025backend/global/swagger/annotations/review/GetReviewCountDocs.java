package com.example.seoulpublicdata2025backend.global.swagger.annotations.review;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        summary = "리뷰 개수 조회",
        description = """
                - 특정 회사 ID를 제공하면 해당 회사의 리뷰 수를 반환합니다.  
                - 회사 ID 없이 호출하면 로그인된 회원의 리뷰 수를 반환합니다.
                """
)
@Parameters({
        @Parameter(name = "companyId", description = "회사 ID (회사 리뷰 수 조회 시에만 사용)", example = "1", required = false)
})
@ApiResponse(
        responseCode = "200",
        description = "리뷰 수 조회 성공",
        content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                        name = "리뷰 수 조회 성공 예시",
                        description = "조회 결과로 리뷰 수를 정수로 반환",
                        value = """
                                {
                                  "count": 3
                                }
                                """
                )
        )
)
@ApiResponse(
        responseCode = "404",
        description = "해당 회원 또는 회사를 찾을 수 없음",
        content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                        value = """
                                {
                                  "status": 404,
                                  "code": "USER_NOT_FOUND",
                                  "message": "해당 회원 또는 회사를 찾을 수 없습니다.",
                                  "errors": [],
                                  "time": "2025-04-22T10:12:34"
                                }
                                """
                )
        )
)
@ApiResponse(
        responseCode = "500",
        description = "서버 내부 에러",
        content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                        value = """
                                {
                                  "status": 500,
                                  "code": "INTERNAL_SERVER_ERROR",
                                  "message": "서버에 문제가 발생했습니다.",
                                  "errors": [],
                                  "time": "2025-04-22T10:12:34"
                                }
                                """
                )
        )
)
public @interface GetReviewCountDocs {
}
