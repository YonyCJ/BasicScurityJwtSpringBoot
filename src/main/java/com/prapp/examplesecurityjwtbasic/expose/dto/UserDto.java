package com.prapp.examplesecurityjwtbasic.expose.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

public class UserDto {

    @Getter
    @Setter
    public static class Response {
        private UUID id;
        private String username;
        private String email;
        private String password;
    }

    @Getter
    @Setter
    public static class Request {
        @NotNull
        @NotBlank(message = "El nombre de usuario no puede estar vacío ni ser nulo.")
        private String username;
        @NotNull
        @NotBlank
        @Email(message = "Debe proporcionar un correo electrónico válido.")
        private String email;
        @NotNull
        @NotBlank
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres.")
        private String password;
        private List<UserProfileDto.Request> profiles;
    }
    @Getter
    @Setter
    public static class LoginRequest {
        private String username;
        private String email;
        private String password;
    }

}