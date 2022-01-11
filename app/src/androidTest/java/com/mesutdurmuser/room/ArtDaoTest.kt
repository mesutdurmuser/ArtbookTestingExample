package com.mesutdurmuser.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.mesutdurmuser.artbooktestingexample.getOrAwaitValue
import com.mesutdurmuser.artbooktestingexample.roomdb.Art
import com.mesutdurmuser.artbooktestingexample.roomdb.ArtDao
import com.mesutdurmuser.artbooktestingexample.roomdb.ArtDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

//Aşağıda belirttiğimiz small test ile bu testin bir unit test olduğunu belirtiyoruz. Belirtmezsekte bir sorun çıkmıyor fakat doğru olan belirtmek.
//ExperimentalCoroutinesApi
@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ArtDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("testDatabase")
    lateinit var database: ArtDatabase

    private lateinit var dao : ArtDao

    //@Before'u ve altındaki setup fonksiyonunu sınıfımızın içinde birden fazla yerde kullanacağımız işlemleri tek seferde yazıp istediğimiz yerde uygulayabilmek için kullanıyoruz.
    //@Before standarttır altındaki setup fonksiyon ismini istediğimiz şekilde değiştirebiliriz. Fakat sektör standartında genellikle setup olarak adlandırılmaktadır.
    @Before
    fun setup(){
        //inMemoryDatabaseBuilder geçici bir databasedir ve testlerde kullanılır. Aşağıda burada kullanacağımız database i ve dao yu oluşturuyor (initialize ediyoruz).
     /*   database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), ArtDatabase::class.java
        ).allowMainThreadQueries().build()*/

        hiltRule.inject()
        dao = database.artDao()
    }

    //@After before ile birlikte kullanılan yöntem. Sonrasında ne yapılacağını belirttiğimiz yerdir. Aşağıda işlem bittikten sonra database imizi kapatıyoruz.
    @After
    fun teardown(){
        database.close()
    }

    //aşağıdaki metodlar dao'muzda suspend fonksiyon olarak yazıldığı için coroutine'in runblocking blogunu kullanıyoruz.
    @Test
    fun insertArtTesting() = runBlockingTest {
        val exampleArt = Art("Mona Lisa","Da Vinci",1700,"test.com",1)
        dao.insertArt(exampleArt)

        val list = dao.observeArts().getOrAwaitValue()
        assertThat(list).contains(exampleArt)

    }

    @Test
    fun deleteArtTesting() = runBlockingTest {
        val exampleArt = Art("Mona Lisa","Da Vinci",1700,"test.com",1)
        dao.insertArt(exampleArt)
        dao.deleteArt(exampleArt)

        val list = dao.observeArts().getOrAwaitValue()
        assertThat(list).doesNotContain(exampleArt)

    }
}