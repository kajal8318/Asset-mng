package com._eq.asset_management_system.security.service.impl;

import com._eq.asset_management_system.employee.entity.Employee;
import com._eq.asset_management_system.security.service.FirebaseAuthenticationService;
import com._eq.asset_management_system.user.dto.CreateUserRequestDto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import org.springframework.stereotype.Service;

@Service
public class FirebaseAuthenticationServiceImpl implements FirebaseAuthenticationService {

    @Override
    public String createUser(String email, String password) {

        try {

            UserRecord.CreateRequest createRequest =
                    new UserRecord.CreateRequest()
                            .setEmail(email)
                            .setPassword(password);

            UserRecord userRecord =
                    FirebaseAuth.getInstance().createUser(createRequest);

            return userRecord.getUid();

        } catch (FirebaseAuthException e) {
            throw new RuntimeException("Failed to create Firebase user", e);
        }
    }

    @Override
    public FirebaseToken verifyToken(String token) {

        try {

            return FirebaseAuth.getInstance()
                    .verifyIdToken(token);

        } catch (FirebaseAuthException e) {
            throw new RuntimeException("Invalid Firebase Token", e);
        }

    }

    @Override
    public void deleteUser(String uid) {

        try {

            FirebaseAuth.getInstance()
                    .deleteUser(uid);

        } catch (FirebaseAuthException e) {
            throw new RuntimeException("Unable to delete Firebase user", e);
        }

    }
}