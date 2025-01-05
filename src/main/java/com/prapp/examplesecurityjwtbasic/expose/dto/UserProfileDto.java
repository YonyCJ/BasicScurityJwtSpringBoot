package com.prapp.examplesecurityjwtbasic.expose.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;


public class UserProfileDto {

    @Getter
    @Setter
    public static class Response {
        private UUID id;
        private UserDto.Response user;
        private Date expirationDate;
        private Integer state;
        private List<ProfileDto.Response> profiles;
    }

    @Getter
    @Setter
    public static class Request {
        private UUID id;
        private UserDto.Response user;
        private ProfileDto.Response profile;
        private Date expirationDate;
        private Integer state;
    }
}
