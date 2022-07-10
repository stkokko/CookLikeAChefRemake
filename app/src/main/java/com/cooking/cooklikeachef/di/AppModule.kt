package com.cooking.cooklikeachef.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.cooking.cooklikeachef.data.repository.local.DataStoreRepoImpl
import com.cooking.cooklikeachef.data.repository.remote.FirebaseAuthRepoImpl
import com.cooking.cooklikeachef.data.repository.remote.FirebaseCloudFirestoreRepoImpl
import com.cooking.cooklikeachef.domain.repository.local.DataStoreRepo
import com.cooking.cooklikeachef.domain.repository.remote.FirebaseAuthRepo
import com.cooking.cooklikeachef.domain.repository.remote.FirebaseCloudFirestoreRepo
import com.cooking.cooklikeachef.domain.use_cases.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

private const val USER_PREFERENCES_NAME = "user_preferences"

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCookLikeAChefDataStore(dataStore: DataStore<Preferences>) : DataStoreRepo {
        return DataStoreRepoImpl(dataStore)
    }

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
    fun provideLogInUseCase(firebaseAuthRepo: FirebaseAuthRepo, dataStoreRepo: DataStoreRepo): LogIn {
        return LogIn(firebaseAuthRepo, dataStoreRepo)
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

    @Provides
    @Singleton
    fun provideGetUserLoginStateUseCase(dataStoreRepo: DataStoreRepo) : GetUserLoginState {
        return GetUserLoginState(dataStoreRepo)
    }

    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(SharedPreferencesMigration(context, USER_PREFERENCES_NAME)),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { context.preferencesDataStoreFile(USER_PREFERENCES_NAME) }
        )
    }

}