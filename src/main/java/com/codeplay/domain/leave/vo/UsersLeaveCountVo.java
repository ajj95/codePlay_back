package com.codeplay.domain.leave.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor 
public class UsersLeaveCountVo {
	private Integer dept_no;
	private String dept_name;
	private Integer user_no;
	private String dept_designated_date;
	private String user_name;
	private String user_position;
	private Integer leave_no;
	private Integer leave_year;
	private Integer leave_total;
	private Integer leave_use;
	private Integer leave_remain;
	

}