package com.cooking.cooklikeachef.domain.use_cases

import com.cooking.cooklikeachef.domain.repository.FirebaseAuthRepo
import javax.inject.Inject

class LogIn @Inject constructor(
    private val firebaseAuthRepo: FirebaseAuthRepo
) {
    operator fun invoke(email: String, password: String) {
        firebaseAuthRepo.logIn(email, password)
    }
}