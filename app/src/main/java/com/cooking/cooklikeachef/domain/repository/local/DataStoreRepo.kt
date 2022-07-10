package com.cooking.cooklikeachef.domain.repository.local

interface DataStoreRepo {

    suspend fun setUserLoginState(isLoggedIn: Boolean)

    suspend fun getUserLoginState() : Boolean

}