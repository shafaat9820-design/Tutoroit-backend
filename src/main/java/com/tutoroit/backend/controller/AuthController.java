// package com.tutoroit.backend.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import com.tutoroit.backend.dto.LoginRequest;
// import com.tutoroit.backend.dto.RegisterRequest;
// import com.tutoroit.backend.model.User;
// import com.tutoroit.backend.service.AuthService;

// /**
//  * AuthController
//  * Register & Login APIs
//  */
// @RestController
// @RequestMapping("/api/auth")
// @CrossOrigin(origins = "http://localhost:5173")
// public class AuthController {

//     @Autowired
//     private AuthService authService;

//     /**
//      * REGISTER API
//      */
//     @PostMapping("/register")
//     public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
//         try {
//             authService.register(request);
//             return ResponseEntity.ok("Registration successful");
//         } catch (RuntimeException e) {
//             return ResponseEntity
//                     .status(HttpStatus.CONFLICT) // 409
//                     .body(e.getMessage());
//         }
//     }

//     /**
//      * LOGIN API
//      */
//     @PostMapping("/login")
//     public ResponseEntity<?> login(@RequestBody LoginRequest request) {
//         try {
//             User user = authService.login(request);
//             return ResponseEntity.ok(user); // 200
//         } catch (RuntimeException e) {
//             return ResponseEntity
//                     .status(HttpStatus.UNAUTHORIZED) // 401



package com.tutoroit.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tutoroit.backend.dto.LoginRequest;
import com.tutoroit.backend.dto.RegisterRequest;
import com.tutoroit.backend.model.User;
import com.tutoroit.backend.service.AuthService;

import java.util.HashMap;
import java.util.Map;

/**
 * AuthController
 * Register & Login APIs
 */
@RestController
@RequestMapping("/api/auth")

// ✅ FIX 1: Allow BOTH local + deployed frontend (CORS fix)
// @CrossOrigin(
//     origins = {
//         "http://localhost:5173",
//         "https://tutoroit-frontend.vercel.app"
//     }
// )

@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * REGISTER API
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            authService.register(request);

            // ✅ FIX 2: Return proper JSON instead of plain string
            Map<String, String> response = new HashMap<>();
            response.put("message", "Registration successful");

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT) // 409
                    .body(e.getMessage());
        }
    }

    /**
     * LOGIN API
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            User user = authService.login(request);

            // ✅ FIX 3: Do NOT return full User entity (security best practice)
            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("name", user.getName());
            response.put("email", user.getEmail());
            response.put("role", user.getRole());

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED) // 401
                    .body(e.getMessage());
        }
    }
}

//                     .body(e.getMessage());
//         }
//     }
// }
