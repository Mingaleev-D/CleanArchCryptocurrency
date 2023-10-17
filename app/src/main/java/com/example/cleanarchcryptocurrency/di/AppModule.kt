package com.example.cleanarchcryptocurrency.di

import com.example.cleanarchcryptocurrency.data.remote.APiService
import com.example.cleanarchcryptocurrency.data.remote.APiService.Companion.BASE_URL
import com.example.cleanarchcryptocurrency.data.repository.CoinRepositoryImpl
import com.example.cleanarchcryptocurrency.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

   @Provides
   fun provideBaseUrl(): String {
      return BASE_URL
   }

   @Provides
   @Singleton
   fun provideLoggingInterceptor(): HttpLoggingInterceptor {
      return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
   }

   @Provides
   @Singleton
   fun provideOkHttpClient(logger: HttpLoggingInterceptor): OkHttpClient {
      val okHttpClient = OkHttpClient.Builder()
      okHttpClient.addInterceptor(logger)
      okHttpClient.callTimeout(30, TimeUnit.SECONDS)
      okHttpClient.connectTimeout(30, TimeUnit.SECONDS)
      okHttpClient.writeTimeout(30, TimeUnit.SECONDS)
      okHttpClient.readTimeout(30, TimeUnit.SECONDS)
      val okHttp = okHttpClient.build()
      return okHttp
   }

   @Provides
   @Singleton
   fun provideRetrofit(
       baseUrl: String,
       okHttpClient: OkHttpClient
   ): Retrofit {
      return Retrofit.Builder()
          .baseUrl(baseUrl)
          .addConverterFactory(GsonConverterFactory.create())
          .client(okHttpClient)
          .build()
   }

   @Provides
   @Singleton
   fun provideProductsService(retrofit: Retrofit): APiService {
      return retrofit.create(APiService::class.java)
   }

   @Provides
   @Singleton
   fun provideCoinRepository(api: APiService): CoinRepository {
      return CoinRepositoryImpl(api)
   }
}