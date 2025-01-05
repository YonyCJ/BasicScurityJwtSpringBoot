package com.prapp.examplesecurityjwtbasic.expose;

import com.prapp.examplesecurityjwtbasic.business.UserService;
import com.prapp.examplesecurityjwtbasic.expose.dto.UserDto;
import com.prapp.examplesecurityjwtbasic.security.JsonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto.Response> register(@Valid @RequestBody UserDto.Request request) {
        UserDto.Response dto = userService.register(request);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<JsonResponse> login(@RequestBody final UserDto.LoginRequest request){
        JsonResponse dto = userService.login(request);
        return ResponseEntity.ok(dto);
    }

}
