package com.prapp.examplesecurityjwtbasic.expose.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class ProfileDto {

    @Getter
    @Setter
    public static class Response {
        private UUID id;
        private String code;
        private String name;
        private String description;
        private Boolean isDefault;
    }

    @Getter
    @Setter
    public static class Request {
        private UUID id;
        private String code;
        private String name;
        private String description;
        private Boolean isDefault;
    }
}
