package com.example.authverificacionapi.Services;

import com.example.authverificacionapi.common.dtos.TokenResponse;
import com.example.authverificacionapi.common.dtos.UserRequest;
import com.example.authverificacionapi.common.entities.UserModel;
import org.apache.catalina.User;

import java.util.List;

public interface AuthService {
    UserModel createUser(UserRequest userRequest);
    TokenResponse loginUser(String email, String password);
    UserModel getUser( Long userId);
    List<UserModel> getAllUsers(String token);
    void deleteUser( String token);
    void deleteUserAdmin( String token, Long idElim);
    void updateUser(String token, UserRequest userRequest);
    void updateUserAdmin(String token, Long idUser, UserRequest userRequest);
}
