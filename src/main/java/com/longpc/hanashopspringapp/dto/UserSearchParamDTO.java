package com.longpc.hanashopspringapp.dto;

import lombok.Data;

@Data
public class UserSearchParamDTO implements BaseSearchParamDTO{
    private String username;
    private String roleId;
    private String fullname;
}
