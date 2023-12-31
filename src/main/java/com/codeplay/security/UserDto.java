package com.codeplay.security;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {
    private Integer user_no;
    private Integer dept_no;
    private String user_name;
    private String user_email;
    private String user_password;
    private boolean user_password_is_temp;

    private List<Integer> page_no;

    @Builder
    public UserDto(Integer user_no, Integer dept_no, String user_name, String user_email, String user_password, boolean user_password_is_temp) {
        this.user_no = user_no;
        this.dept_no = dept_no;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_password_is_temp = user_password_is_temp;
    }

    public List<Integer> getPage_no() {
        return page_no;
    }

    public void setPage_no(List<Integer> page_no) {
        this.page_no = page_no;
    }
}
