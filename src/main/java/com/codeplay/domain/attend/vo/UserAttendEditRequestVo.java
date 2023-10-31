package com.codeplay.domain.attend.vo;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAttendEditRequestVo {
	private String attendedit_title;
	private String attendedit_reason;
	private Integer attendedit_kind;
	private Time attendedit_start_time;
	private Time attendedit_end_time;
	private Integer attendapp_user_no;
}