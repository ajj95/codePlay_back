package com.codeplay.security;

import ch.qos.logback.core.pattern.ConverterUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@Slf4j
@Configuration
public class CustomAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        log.debug("3.CustomLoginSuccessHandler");

        // [STEP1] 사용자와 관련된 정보를 모두 조회합니다.
        UserDto userDto = ((UserDetailsDto) authentication.getPrincipal()).getUserDto();

        // [STEP2] 조회한 데이터를 JSONObject 형태로 파싱을 수행합니다.
        JSONObject userVoObj = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(userDto));


        HashMap<String, Object> responseMap = new HashMap<>();

        JSONObject jsonObject;

        // [STEP3-1] 사용자의 상태가 '휴면 상태' 인 경우 응답 값으로 전달 할 데이터
//        if (userDto.getUserSt().equals("D")) {
//            responseMap.put("userInfo", userVoObj);
//            responseMap.put("resultCode", 9001);
//            responseMap.put("token", null);
//            responseMap.put("failMsg", "휴면 계정입니다.");
//            jsonObject = new JSONObject(responseMap);
//        }
//
//        // [STEP3-2] 사용자의 상태가 '휴면 상태'가 아닌 경우 응답 값으로 전달 할 데이터
//        else {
            // 1. 일반 계정일 경우 데이터 세팅
            responseMap.put("userInfo", userVoObj);
            responseMap.put("resultCode", 200);
            responseMap.put("failMsg", null);
            jsonObject = new JSONObject(responseMap);
//
//            // TODO: 추후 JWT 발급에 사용 할 예정
//            // String token = TokenUtils.generateJwtToken(userVo);
//            // jsonObject.put("token", token);
//            // response.addHeader(AuthConstants.AUTH_HEADER, AuthConstants.TOKEN_TYPE + " " + token);
//        }

        // [STEP4] 구성한 응답 값을 전달합니다.
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        printWriter.print(jsonObject);  // 최종 저장된 '사용자 정보', '사이트 정보' Front 전달
        printWriter.flush();
        printWriter.close();
    }
}
