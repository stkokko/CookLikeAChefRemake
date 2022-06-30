package com.cooking.cooklikeachef.domain.use_cases

import com.cooking.cooklikeachef.domain.repository.FirebaseAuthRepo
import javax.inject.Inject

class Register @Inject constructor(
    private val firebaseAuthRepo: FirebaseAuthRepo
){
    operator fun invoke(email: String, password: String, confirmPassword: String) {
        return firebaseAuthRepo.register(email, password, confirmPassword)
    }
}