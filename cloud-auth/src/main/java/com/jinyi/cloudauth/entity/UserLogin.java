package com.jinyi.cloudauth.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserLogin {
    private String userLoginId;

    private String currentPassword;

    private Boolean isSystem;

    private Boolean enabled;

    private String partyId;

    private String disabledBy;

    private Timestamp disabledDatetime;

    private Boolean hasLoggedOut;

    private Boolean requirePasswordChange;
}
