package ru.testwork.bincheckerapp.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.testwork.bincheckerapp.data.api.ApiService
import ru.testwork.bincheckerapp.data.datasources.BinCodeLocalDataSource
import ru.testwork.bincheckerapp.data.datasources.BinCodeLocalDataSourceImpl
import ru.testwork.bincheckerapp.data.datasources.BinCodeRemoteDataSource
import ru.testwork.bincheckerapp.data.datasources.BinCodeRemoteDataSourceImpl
import ru.testwork.bincheckerapp.data.repositories.BinCodeHistoryRepositoryImpl
import ru.testwork.bincheckerapp.data.repositories.BinCodeInfoRepository
import ru.testwork.bincheckerapp.data.repositories.BinCodeInfoRepositoryImpl
import ru.testwork.bincheckerapp.data.room.AppRoomDatabase
import ru.testwork.bincheckerapp.domain.IBinCodeInfoInteractor
import ru.testwork.bincheckerapp.domain.IBinCodeInfoInteractorImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GeneralModule {


    @Module
    @InstallIn(SingletonComponent::class)
    object LocalResources {

        @Provides
        @Singleton
        fun provideDb(@ApplicationContext context: Context): AppRoomDatabase {
            return AppRoomDatabase.getInstance(context)
        }

    }

    @Module
    @InstallIn(SingletonComponent::class)
    object RepositoryModule {

        @Singleton
        @Provides
        fun provideBinCodeRepo(
            remoteDataSource: BinCodeRemoteDataSource,
            localDataSource: BinCodeLocalDataSource
        ): BinCodeInfoRepository {
            return BinCodeInfoRepositoryImpl(
                remoteSource = remoteDataSource,
                localSource = localDataSource
            )
        }

        @Singleton
        @Provides
        fun provideBinHistoryRepo(
            localDataSource: BinCodeLocalDataSource
        ): BinCodeHistoryRepositoryImpl {
            return BinCodeHistoryRepositoryImpl(localDataSource)
        }

    }

    @Module
    @InstallIn(SingletonComponent::class)
    object InteractorModule {

        @Singleton
        @Provides
        fun provideBinCodeInteractor(binCodeRepo: BinCodeInfoRepository): IBinCodeInfoInteractor {
            return IBinCodeInfoInteractorImpl(binCodeRepo)
        }
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object DataSourceModule {

        @Singleton
        @Provides
        fun provideRemoteBinCodeDataSource(api: ApiService): BinCodeRemoteDataSource {
            return BinCodeRemoteDataSourceImpl(api)
        }

        @Singleton
        @Provides
        fun provideLocalBinCodeDataSource(appRoomDatabase: AppRoomDatabase): BinCodeLocalDataSource {
            return BinCodeLocalDataSourceImpl(appRoomDatabase.getBinInfoDao())
        }
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object NetworkModule {

        private const val BIN_INFO_BASE_URL = "https://lookup.binlist.net"

        @Provides
        @Singleton
        fun provideRetrofit(
            moshi: Moshi,
            okHttpClient: OkHttpClient
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BIN_INFO_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build()
        }


        @Provides
        @Singleton
        fun provideMoshiAdapter(): Moshi {
            return Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
        }

        @Provides
        @Singleton
        fun provideOkhttpClient(): OkHttpClient {
            val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            return client.build()
        }

        @Provides
        @Singleton
        fun provideApiService(retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }
    }
}
