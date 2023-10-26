package com.codeplay.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeplay.domain.UserVo;
import com.codeplay.domain.userInformation.dto.UserInformationDto;
import com.codeplay.domain.userInformation.vo.UserInformationResponseVo;
import com.codeplay.domain.userInformation.vo.UserQueryListResponseVo;
import com.codeplay.domain.userInformation.vo.UserQueryResponseVo;
import com.codeplay.mapper.userInformation.UserMapper;
import com.codeplay.service.userInformation.UserInformationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "사용자 회원정보 관리 기능", description = "사용자 회원정보 관리에 필요한 API")
@RestController
@Slf4j
public class UserInformationController {
	@Autowired
	UserInformationService service;

	@Operation(summary = "user 사용자 정보 페이지 조회", description = "회원정보조회 페이지에서 user 의 사용자 정보를 조회할때 사용합니다.")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@GetMapping("/user-information")
	public List<UserInformationResponseVo> get_user_information(@RequestParam int user_no) {
		log.info("user-information에 호출함. user_no: "+user_no);
		List<UserInformationResponseVo> list = new ArrayList();
		UserInformationDto user = service.getUserData(user_no);
		log.info("서비스로부터 받아온 데이터 user: "+user);
		//ResponseVo 객체로 포장
		UserInformationResponseVo vo = new UserInformationResponseVo();
		vo.setDept_name(user.getDept().getDept_name());
		vo.setUser_address(user.getUser().getUser_address());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		vo.setUser_birth_date(format.format(user.getUser().getUser_birth_date()));
		vo.setUser_email(user.getUser().getUser_email());
		vo.setUser_name(user.getUser().getUser_name());
		vo.setUser_no(user.getUser().getUser_no());
		vo.setUser_phone(user.getUser().getUser_phone());
		vo.setUser_position(user.getUser().getUser_position());
		vo.setUser_profile(user.getUser().getUser_profile());
		list.add(vo);
		return list;
	}
	
	@Operation(summary = "user 사용자 정보 테이블 조회", description = "사용자 조회페이지의 user 의 사용자 정보를 테이블로 조회할때 사용합니다. 이름으로검색가능, pagenation가능")
	@Parameter(name = "user_name", description = "유저 개인을 검색하기위한 유저이름")
	@Parameter(name = "page", description = "pagenation에서 보여줄 현재 page number")
	@Parameter(name = "limit", description = "pagenation에서 보여줄 최대 갯수")
	@GetMapping("/user-query")
	public List<UserQueryResponseVo> get_user_query(@RequestParam String user_name, @RequestParam Integer page, @RequestParam Integer limit) {
		log.info("user-query에 호출함. user_name: {} page: {} limit: {}", user_name, page, limit);
		List<UserQueryResponseVo> list = new ArrayList();
		UserQueryResponseVo user = new UserQueryResponseVo();
		user.setDept_name("개발팀");
		user.setUser_birth_date("1960-01-24");
		user.setUser_name("홍길동");
		user.setUser_no(0);
		user.setUser_position("연구원");
		user.setUser_profile("https://picsum.photos/200");
		log.info("서비스로부터 받아온 데이터 user: "+user);
		list.add(user);
		return list;
	}
	@Operation(summary = "조직도 조회", description = "사용자 조회페이지의 조직도를 전체 조회할때 사용합니다. 1단으로 구성됨.")
	@GetMapping("/user-query-list")
	public List<UserQueryListResponseVo> get_user_query_list() {
		log.info("user-query-list에 호출함.");
		List<UserQueryListResponseVo> list = new ArrayList();
		UserQueryListResponseVo user = new UserQueryListResponseVo();
		user.setDept_name("개발팀");
		user.setUser_name("홍길동");
		user.setUser_no(0);
		user.setUser_position("연구원");
		log.info("서비스로부터 받아온 데이터 user: "+user);
		list.add(user);
		return list;
	}

}