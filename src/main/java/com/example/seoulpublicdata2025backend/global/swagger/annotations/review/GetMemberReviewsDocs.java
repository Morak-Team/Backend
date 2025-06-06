package com.example.seoulpublicdata2025backend.global.swagger.annotations.review;

import com.example.seoulpublicdata2025backend.domain.review.dto.ReviewDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        summary = "회원 리뷰 조회",
        description = "로그인된 사용자의 kakaoId를 쿠키에서 자동 추출하여 해당 회원이 작성한 리뷰 목록을 조회합니다."
)
@ApiResponse(
        responseCode = "200",
        description = "회원 리뷰 조회 성공",
        content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = ReviewDto.class)),
                examples = @ExampleObject(
                        name = "회원 리뷰 조회 예시",
                        description = "현재 로그인된 사용자가 작성한 리뷰 목록",
                        value = """
                                [
                                  {
                                    "companyId": 1,
                                    "kakaoId": 1001,
                                    "review": "좋아요!",
                                    "temperature": 88.0,
                                    "reviewCategories": ["CLEAN", "KIND"]
                                  },
                                  {
                                    "companyId": 2,
                                    "kakaoId": 1001,
                                    "review": "조금 별로였어요.",
                                    "temperature": 74.3,
                                    "reviewCategories": ["REASONABLE_PRICE"]
                                  }
                                ]
                                """
                )
        )
)
@ApiResponse(
        responseCode = "404",
        description = "해당 회원을 찾을 수 없음",
        content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                        name = "회원 없음 예시",
                        description = "쿠키로 식별한 kakaoId에 해당하는 회원이 존재하지 않는 경우",
                        value = """
                                {
                                  "status": 404,
                                  "code": "USER_NOT_FOUND",
                                  "message": "해당 회원을 찾을 수 없습니다.",
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
                        name = "회원 리뷰 조회 실패 - 서버 오류",
                        description = "서버 오류로 인해 요청 처리에 실패한 경우",
                        value = """
                                {
                                  "status": 500,
                                  "code": "INTERNAL_SERVER_ERROR",
                                  "message": "서버에 문제가 발생했습니다. 관리자에게 문의하세요.",
                                  "errors": [],
                                  "time": "2025-04-22T10:12:34"
                                }
                                """
                )
        )
)
public @interface GetMemberReviewsDocs {
}
