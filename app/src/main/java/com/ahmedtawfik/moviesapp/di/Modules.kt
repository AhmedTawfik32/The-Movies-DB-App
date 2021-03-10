package com.ahmedtawfik.moviesapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.ahmedtawfik.moviesapp.BuildConfig
import com.ahmedtawfik.moviesapp.model.local.roomdb.DatabaseRepository
import com.ahmedtawfik.moviesapp.model.local.roomdb.DatabaseRepositoryImp
import com.ahmedtawfik.moviesapp.model.local.roomdb.MovieDatabase
import com.ahmedtawfik.moviesapp.model.local.sharedpref.SharedPreferenceHelper
import com.ahmedtawfik.moviesapp.model.local.sharedpref.SharedPreferenceHelperImp
import com.ahmedtawfik.moviesapp.model.remote.Repository
import com.ahmedtawfik.moviesapp.model.remote.RepositoryImp
import com.ahmedtawfik.moviesapp.model.remote.network.APIService
import com.ahmedtawfik.moviesapp.model.remote.network.NetworkInterceptorConnection
import com.ahmedtawfik.moviesapp.model.remote.network.RemoteRepository
import com.ahmedtawfik.moviesapp.model.remote.network.RemoteRepositoryImp
import com.ahmedtawfik.moviesapp.ui.favoritemovies.FavoriteMoviesViewModel
import com.ahmedtawfik.moviesapp.ui.movieoverview.MovieOverviewViewModel
import com.ahmedtawfik.moviesapp.ui.popularmovies.PopularMoviesViewModel
import com.ahmedtawfik.moviesapp.ui.topratedmovies.TopRatedMoviesViewModel
import com.ahmedtawfik.moviesapp.ui.upcomingmovies.UpcomingMoviesViewModel
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

val repositoryModule = module {
    single<RemoteRepository> { RemoteRepositoryImp(get()) }
    single<SharedPreferenceHelper> { SharedPreferenceHelperImp(get()) }
    single<DatabaseRepository> { DatabaseRepositoryImp(get()) }
    single<Repository> {
        RepositoryImp(
            remoteRepository = get(),
            sharedPreferenceRepository = get(),
            databaseRepository = get()
        )
    }
}

val viewModelModule = module {
    viewModel { PopularMoviesViewModel(get()) }
    viewModel { UpcomingMoviesViewModel(get()) }
    viewModel { TopRatedMoviesViewModel(get()) }
    viewModel { FavoriteMoviesViewModel(get()) }
    viewModel { (movie_id: Int) -> MovieOverviewViewModel(get(), movie_id) }
}

val apiServiceModule = module {
    fun getApiServiceModule(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }
    single { getApiServiceModule(get()) }
}

val retrofitModule = module {

    fun provideCacheFile(context: Context): File {
        return File(context.cacheDir, "okHttp_cache")
    }

    fun provideCache(cacheFile: File): Cache {
        return Cache(cacheFile, 3 * 1024 * 1024)
    }

    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    fun provideNetworkConnInterceptor(context: Context): NetworkInterceptorConnection {
        return NetworkInterceptorConnection(context)
    }

    fun provideHttpClient(
        cache: Cache,
        loggingInterceptor: HttpLoggingInterceptor,
        networkInterceptor: NetworkInterceptorConnection
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(networkInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .client(client)
            .build()
    }

    single { provideCacheFile(androidContext()) }
    single { provideCache(get()) }
    single { provideLoggingInterceptor() }
    single { provideNetworkConnInterceptor(androidContext()) }

    single {
        provideHttpClient(
            cache = get(),
            loggingInterceptor = get(),
            networkInterceptor = get()
        )
    }
    single { provideRetrofit(client = get()) }
}

val databaseModule = module {
    fun getMovieDatabaseInstance(application: Application): MovieDatabase {
        return Room.databaseBuilder(application, MovieDatabase::class.java, "movie_db")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { getMovieDatabaseInstance(androidApplication()) }
}