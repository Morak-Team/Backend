package com.example.seoulpublicdata2025backend.global.swagger.annotations.member;

import com.example.seoulpublicdata2025backend.domain.member.dto.SignupRequestDto;
import com.example.seoulpublicdata2025backend.domain.member.dto.SignupResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        summary = "회원가입",
        description = "kakaoId를 제외한 나머지 개인정보를 입력받아 회원가입 합니다. 유효한 accessToken을 포함한 쿠키를 포함해야 합니다."
)
@Parameters({
        @Parameter(name = "name", description = "사용자 이름",example = "홍길동"),
        @Parameter(name = "location", description = "사용자가 사는 동네 주소", example = "서울특별시 강남구"),
        @Parameter(name = "role", description = "소비자라면 CONSUMER, 사장님이면 CORPORATE", example = "CONSUMER"),
        @Parameter(name = "profileColor", description = "기본 프로필의 색깔 표기 (\"gray\", \"pink\", \"blue\", \"orange\") ", example = "pink")
})
@RequestBody(
        description = "회원가입 요청 데이터",
        required = true,
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = SignupRequestDto.class),
                examples = @ExampleObject(
                        name = "회원가입 요청 예시",
                        description = "성공적인 회원가입 요청 예시",
                        value = """
                                {
                                  "name": "홍길동",
                                  "location": "서울특별시 강남구",
                                  "role": "CONSUMER",
                                  "profileColor": "gray"
                                }
                                """
                )
        )
)
@ApiResponse(
        responseCode = "200",
        description = "회원가입에 성공. 본문 없이 Set-Cookie 헤더에 accessToken을 포함합니다.",
        content = @Content(schema = @Schema(hidden = true))
)
@ApiResponse(
        responseCode = "400",
        description = "요청 데이터가 유효하지 않음 (입력값 중에 null 값이 있는 경우)",
        content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                        name = "회원가입 실패 예시",
                        description = "입력 값 중 null 값이 있는 경우",
                        value = """
                                {
                                  "status": 400,
                                  "code": "INVALID_INPUT_VALUE",
                                  "message": "입력값이 올바르지 않습니다.",
                                  "errors": [
                                    {
                                      "field": "name",
                                      "value": "",
                                      "reason": "이름은 필수입니다."
                                    },
                                    {
                                      "field": "location",
                                      "value": "",
                                      "reason": "위치는 필수입니다."
                                    }
                                  ],
                                  "time": "2025-04-15T10:12:34"
                                }
                                """
                )
        )
)
@ApiResponse(responseCode = "401", description = "유효하지 않은 토큰입니다.",
        content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                        name = "INVALID_TOKEN",
                        value = """
                                {
                                  "status": 401,
                                  "code": "INVALID_TOKEN",
                                  "message": "유효하지 않은 토큰입니다.",
                                  "errors": [],
                                  "time": "2025-04-24T13:30:00"
                                }
                                """
                )
        )
)
@ApiResponse(
        responseCode = "404",
        description = "존재하지 않는 kakaoId",
        content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                        name = "회원가입 실패 - 존재하지 않는 kakaoId",
                        description = "존재하지 않는 카카오 ID로 가입을 시도했을 때",
                        value = """
                                {
                                  "status": 404,
                                  "code": "MEMBER_NOT_FOUND",
                                  "message": "사용자를 찾을 수 없습니다.",
                                  "errors": [],
                                  "time": "2025-04-15T10:12:34"
                                }
                                """
                )
        )
)
@ApiResponse(
        responseCode = "409",
        description = "이미 가입된 회원",
        content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                        name = "회원가입 실패 - 중복 회원",
                        description = "이미 존재하는 카카오 ID로 가입을 시도했을 때",
                        value = """
                                {
                                  "status": 409,
                                  "code": "DUPLICATE_MEMBER",
                                  "message": "이미 가입된 사용자입니다.",
                                  "errors": [],
                                  "time": "2025-04-15T10:12:34"
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
                        name = "회원가입 실패 - 서버 오류",
                        description = "서버 오류로 인해 회원가입에 실패한 경우",
                        value = """
                                {
                                  "status": 500,
                                  "code": "INTERNAL_SERVER_ERROR",
                                  "message": "서버에 오류가 발생했습니다.",
                                  "errors": [],
                                  "time": "2025-04-15T10:12:34"
                                }
                                """
                )
        )
)


public @interface SignUpDocs {
}
