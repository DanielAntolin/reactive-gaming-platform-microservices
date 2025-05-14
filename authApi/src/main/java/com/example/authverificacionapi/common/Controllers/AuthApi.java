package com.example.authverificacionapi.common.Controllers;

import com.example.authverificacionapi.common.constans.ApiPathConstants;
import com.example.authverificacionapi.common.dtos.LoginRequest;
import com.example.authverificacionapi.common.dtos.TokenResponse;
import com.example.authverificacionapi.common.dtos.UserRequest;
import com.example.authverificacionapi.common.entities.UserModel;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasRole;


@RequestMapping(ApiPathConstants.V1_ROOT_PATH + ApiPathConstants.AUTH_ROOT_PATH)

public interface AuthApi {
    @PostMapping(value = "/register")
    ResponseEntity<UserModel>createUser(@RequestBody @Valid UserRequest userRequest);
    @PostMapping(value = "/login")
    ResponseEntity<TokenResponse>loginUser(@RequestBody LoginRequest loginRequest);

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("searchAdminId/"+ "/{id}")
    ResponseEntity<UserModel> getUserId(@RequestAttribute(name = "X-User-Id")String userId,
                                     @RequestAttribute(name = "X-User-Role") String userRole,@PathVariable Long id);

    @GetMapping("/search"+ "/{id}")
    ResponseEntity<UserModel> getUser(@RequestAttribute(name = "X-User-Id")String userId ,
                                      @RequestAttribute(name = "X-User-Role") String userRole,@PathVariable Long id );

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("searchAdminAll")
    ResponseEntity<List<UserModel>> getUsers(@RequestAttribute(name = "X-User-Id")String userId , @RequestAttribute(name = "X-User-Role") String userRole);

    @DeleteMapping("delete"+ "/{id}")
    ResponseEntity<UserModel> deleteUser(@RequestAttribute(name = "X-User-Id")String userId, @RequestAttribute(name = "X-User-Role") String userRole,@PathVariable Long id );

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("deleteAdmin" + "/{id}")
    ResponseEntity<UserModel> deleteUserAdmin(@RequestAttribute(name = "X-User-Id")String userId ,
                                              @RequestAttribute(name = "X-User-Role") String userRole, @PathVariable Long id );
    @PutMapping("/editUsuario"+ "/{id}")
    ResponseEntity<UserModel> updateUser(@RequestAttribute(name = "X-User-Id")String userId,
                                         @RequestAttribute(name = "X-User-Role") String userRole, @RequestBody @Valid UserRequest userRequest,@PathVariable Long id );
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editUsuarioAdmin"+ "/{id}")
    ResponseEntity<UserModel> updateUserId(@RequestAttribute(name = "X-User-Id")String userId,
                                           @RequestAttribute(name = "X-User-Role") String userRole,@PathVariable Long id, @RequestBody @Valid UserRequest userRequest );


}
