package com._eq.asset_management_system.security.service;

import com.google.firebase.auth.FirebaseToken;
import com._eq.asset_management_system.user.dto.CreateUserRequestDto;

public interface FirebaseAuthenticationService {

    String createUser(String email, String password);

    FirebaseToken verifyToken(String token);

    void deleteUser(String uid);

}