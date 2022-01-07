package com.mesutdurmuser.artbooktestingexample.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mesutdurmuser.artbooktestingexample.R
import com.mesutdurmuser.artbooktestingexample.api.RetrofitAPI
import com.mesutdurmuser.artbooktestingexample.repo.ArtRepository
import com.mesutdurmuser.artbooktestingexample.repo.ArtRepositoryInterface
import com.mesutdurmuser.artbooktestingexample.roomdb.ArtDao
import com.mesutdurmuser.artbooktestingexample.roomdb.ArtDatabase
import com.mesutdurmuser.artbooktestingexample.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //Burada inject edilmesini istediğimiz fonksiyonumuzu yazıyoruz. Bu sayede istediğimiz her yerde Room u kullanabileceğiz.
    //Başına singleten ve provides olacağını belirttik. Sonrasında Context e ulaşabilmek için @ApplicationContext dedik.
    //Bu fonksiyon aracılığı ile DAO'muza ulaşabiliyoruz.
    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context : Context) = Room.databaseBuilder(context, ArtDatabase::class.java, "ArtbookDB").build()

    //Bu fonksiyon sayesinde DAO'muzu inject edebileceğiz.
    @Singleton
    @Provides
    fun injectDao(database: ArtDatabase) = database.artDao()

    //Retrofit kullanmak için gereken fonksiyonumuzu oluşturuyoruz. Burada Room ve Retrofit kullanımları için kendi sayfalarındaki kullanım şekillerine bakabilirsiniz.
    //Ayrıca converter (dönüştürücü) olarak Gson kullanıyoruz. Burada onuda belirtiyoruz.
    @Singleton
    @Provides
    fun injectRetrofitAPI() : RetrofitAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RetrofitAPI::class.java)
    }

    @Singleton
    @Provides
    fun injectNormalRepo(dao : ArtDao, api:RetrofitAPI) = ArtRepository(dao, api) as ArtRepositoryInterface

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
        )
}