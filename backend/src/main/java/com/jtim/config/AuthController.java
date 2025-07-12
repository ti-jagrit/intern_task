package com.jtim.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	
	@Autowired
	JwtUtils jwtUtils;
	
@PostMapping("api/login")
public ResponseEntity<?> login(@RequestBody Map<String, Object> req){
	
	if(req.get("username").equals("admin") && req.get("password").equals("admin")) {
		String token=jwtUtils.generateTokenFromUserName(req.get("username").toString());
		return (ResponseEntity<?>) ResponseEntity.ok("Toekn:"+token);
	}
	return ResponseEntity.ok("Error" );
	
}
	
}
