package com.example.authverificacionapi.common.Controllers.Impl;

import com.example.authverificacionapi.Services.AuthService;
import com.example.authverificacionapi.Services.JwtService;
import com.example.authverificacionapi.common.Controllers.AuthApi;
import com.example.authverificacionapi.common.dtos.LoginRequest;
import com.example.authverificacionapi.common.dtos.TokenResponse;
import com.example.authverificacionapi.common.dtos.UserRequest;
import com.example.authverificacionapi.common.entities.UserModel;
import com.example.authverificacionapi.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Component
@RestController
public class AuthController implements AuthApi {
    private final AuthService authService;
    private UserRepository userRepository;
    private JwtService jwtService;
    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity<UserModel> createUser(UserRequest userRequest) {
        return ResponseEntity.ok(authService.createUser(userRequest));
    }

    @Override
    public ResponseEntity<TokenResponse> loginUser(LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.loginUser(loginRequest.getEmail(), loginRequest.getPassword()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<UserModel> getUserId(String userId, String userRole,Long idBuscar) {
        System.out.println("El role del usuario que ha solcitado "+ userRole);

        if(userRole.equals("ROLE_ADMIN")){
            return ResponseEntity.ok(authService.getUser(idBuscar));
        }
        else{
            return  ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<UserModel> getUser(String userId,String userRole, Long id) {
       if(userId.equals(id.toString())) {
           return ResponseEntity.ok(authService.getUser(id));
       }
       else {
           return  ResponseEntity.notFound().build();
       }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<List<UserModel>> getUsers(String userId, String userRole) {
        if(userRole.equals("ROLE_ADMIN")){
            return ResponseEntity.ok(authService.getAllUsers(userId));
        }
        else {
            return  ResponseEntity.notFound().build();
        }

    }

    @Override
    public ResponseEntity<UserModel> deleteUser(String userId, String userRole, Long id) {
        if(userId.equals(id.toString())) {
            authService.deleteUser(userId);
            return ResponseEntity.ok().build();
        }else{
            return  ResponseEntity.notFound().build();
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<UserModel> deleteUserAdmin(String userId,String userRole, Long id) {
        if(userRole.equals("ROLE_ADMIN")){
            authService.deleteUserAdmin(userId, id);
            return ResponseEntity.ok().build();
        }else{
            return  ResponseEntity.notFound().build();
        }

    }

    @Override
    public ResponseEntity<UserModel> updateUser(String userId,String userRole, UserRequest userRequest, Long id) {
        if(userId.equals(id.toString())) {
            authService.updateUser(userId, userRequest);
            return ResponseEntity.ok().build();
        }
        else{
            return  ResponseEntity.notFound().build();
        }

    }
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<UserModel> updateUserId(String userId,String userRole, Long id, UserRequest userRequest) {
        if(userRole.equals("ROLE_ADMIN")){
            authService.updateUserAdmin(userId, id, userRequest);
            return ResponseEntity.ok().build();
        }
        else{
            return  ResponseEntity.notFound().build();
        }

    }


}
