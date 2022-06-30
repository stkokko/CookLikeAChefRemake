package com.cooking.cooklikeachef.domain.use_cases

import com.cooking.cooklikeachef.domain.repository.FirebaseAuthRepo
import javax.inject.Inject

class LogOut @Inject constructor(
    private val firebaseAuthRepo: FirebaseAuthRepo
) {
    operator fun invoke() {
        return firebaseAuthRepo.logOut()
    }
}