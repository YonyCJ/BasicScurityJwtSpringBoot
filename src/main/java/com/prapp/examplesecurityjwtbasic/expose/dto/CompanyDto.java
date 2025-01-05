package com.prapp.examplesecurityjwtbasic.expose.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class CompanyDto {

    @Getter
    @Setter
    public static class Response {
        private UUID id;
        private String name;
        private String address;
        private String email;
        private String phone;
        private String ruc;
    }

    @Getter
    @Setter
    public static class Request {
        private UUID id;
        private String name;
        private String address;
        private String email;
        private String phone;
        private String ruc;
    }
}
