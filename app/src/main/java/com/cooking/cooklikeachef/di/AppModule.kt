package com.cooking.cooklikeachef.di

import com.cooking.cooklikeachef.data.repository.FirebaseAuthRepoImpl
import com.cooking.cooklikeachef.data.repository.FirebaseCloudFirestoreRepoImpl
import com.cooking.cooklikeachef.domain.repository.FirebaseAuthRepo
import com.cooking.cooklikeachef.domain.repository.FirebaseCloudFirestoreRepo
import com.cooking.cooklikeachef.domain.use_cases.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCookLikeAChefFirebaseCloudFirestore(): FirebaseFirestore {
        return Firebase.firestore
    }

    @Provides
    @Singleton
    fun provideCookLikeAChefFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }

    @Provides
    @Singleton
    fun provideCookLikeAChefFirebaseCloudFirestoreRepo(
        db: FirebaseFirestore
    ): FirebaseCloudFirestoreRepo {
        return FirebaseCloudFirestoreRepoImpl(db)
    }

    @Provides
    @Singleton
    fun provideCookLikeAChefFirebaseAuthRepo(
        auth: FirebaseAuth
    ): FirebaseAuthRepo {
        return FirebaseAuthRepoImpl(auth)
    }

    @Provides
    @Singleton
    fun provideGetRecipesUseCase(firebaseCloudFirestoreRepo: FirebaseCloudFirestoreRepo): GetRecipes {
        return GetRecipes(firebaseCloudFirestoreRepo)
    }

    @Provides
    @Singleton
    fun provideGetLatestRecipesUseCase(firebaseCloudFirestoreRepo: FirebaseCloudFirestoreRepo): GetLatestRecipes {
        return GetLatestRecipes(firebaseCloudFirestoreRepo)
    }

    @Provides
    @Singleton
    fun provideGetFavouriteRecipesUseCase(firebaseCloudFirestoreRepo: FirebaseCloudFirestoreRepo): GetFavouriteRecipes {
        return GetFavouriteRecipes(firebaseCloudFirestoreRepo)
    }

    @Provides
    @Singleton
    fun provideLogInUseCase(firebaseAuthRepo: FirebaseAuthRepo): LogIn {
        return LogIn(firebaseAuthRepo)
    }

    @Provides
    @Singleton
    fun provideRegisterUseCase(firebaseAuthRepo: FirebaseAuthRepo): Register {
        return Register(firebaseAuthRepo)
    }

    @Provides
    @Singleton
    fun provideLogOutUseCase(firebaseAuthRepo: FirebaseAuthRepo): LogOut {
        return LogOut(firebaseAuthRepo)
    }

    @Provides
    @Singleton
    fun provideUpdateFavouriteRecipeUseCase(): UpdateFavouriteRecipe {
        return UpdateFavouriteRecipe()
    }

    @Provides
    @Singleton
    fun provideUpdateRecipeCommentUseCase(): UpdateRecipeComment {
        return UpdateRecipeComment()
    }

}