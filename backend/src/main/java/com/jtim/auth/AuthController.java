package com.jtim.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jtim.config.JwtUtils;
import com.jtim.utils.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AuthController {

	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private PasswordEncoder encoder;

	@PostMapping("api/login")
	public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
		String password="$2a$10$/1DxZxs5DlkyrxhKGJoX6uhdZBR2DAG0nMExSr9GVEDRpbdiefjmG";
		log.info("login Request recived {} ",request.getUsername());
		if ("admin".equals(request.getUsername()) && encoder.matches(request.getPassword(),password)) {
			String token = jwtUtils.generateTokenFromUserName(request.getUsername());
			LoginResponse response = new LoginResponse(token, "Login successful");
			return ResponseEntity.ok(new ApiResponse<>(200, "Login successful", response));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ApiResponse<>(401, "Invalid username or password", null));
		}
	}
}
