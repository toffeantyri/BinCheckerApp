package ru.testwork.bincheckerapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.testwork.bincheckerapp.data.datasources.BinCodeLocalDataSource
import ru.testwork.bincheckerapp.data.datasources.BinCodeLocalDataSourceImpl
import ru.testwork.bincheckerapp.data.datasources.BinCodeRemoteDataSource
import ru.testwork.bincheckerapp.data.datasources.BinCodeRemoteDataSourceImpl
import ru.testwork.bincheckerapp.data.repositories.BinCodeInfoRepository
import ru.testwork.bincheckerapp.data.repositories.BinCodeInfoRepositoryImpl
import ru.testwork.bincheckerapp.domain.IBinCodeInfoInteractor
import ru.testwork.bincheckerapp.domain.IBinCodeInfoInteractorImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GeneralModule {


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
        fun provideRemoteBinCodeDataSource(): BinCodeRemoteDataSource {
            return BinCodeRemoteDataSourceImpl()
        }

        @Singleton
        @Provides
        fun provideLocalBinCodeDataSource(): BinCodeLocalDataSource {
            return BinCodeLocalDataSourceImpl()
        }
    }

}
