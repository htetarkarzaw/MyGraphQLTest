package com.htetarkarzaw.mygraphqltest.di

import com.apollographql.apollo3.ApolloClient
import com.htetarkarzaw.mygraphqltest.data.ApolloCountryClient
import com.htetarkarzaw.mygraphqltest.domain.usecase.GetCountriesUsecase
import com.htetarkarzaw.mygraphqltest.domain.usecase.GetCountryUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()
    }

    @Provides
    @Singleton
    fun providesCountryClient(apolloClient: ApolloClient): ApolloCountryClient {
        return ApolloCountryClient(apolloClient)
    }

    @Provides
    @Singleton
    fun providesGetCountriesUsecase(apolloCountryClient: ApolloCountryClient): GetCountriesUsecase {
        return GetCountriesUsecase(apolloCountryClient)
    }

    @Provides
    @Singleton
    fun providesGetCountryUsecase(apolloCountryClient: ApolloCountryClient): GetCountryUsecase {
        return GetCountryUsecase(apolloCountryClient)
    }
}