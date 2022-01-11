package com.mesutdurmuser.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.mesutdurmuser.MainCoroutineRule
import com.mesutdurmuser.artbooktestingexample.util.Status
import com.mesutdurmuser.artbooktestingexample.viewmodel.ArtViewModel
import com.mesutdurmuser.getOrAwaitValueTest
import com.mesutdurmuser.repo.FakeArtRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

//ExperimentalCoroutinesApi'yi aşağıda kullandığımız instantTaskExecutorRule ve mainCoroutineRule'un sorunsuz çalışması için koyuyoruz.
@ExperimentalCoroutinesApi
class ArtViewModelTest {

    private lateinit var viewModel: ArtViewModel

    // @get:Rule ile kullanacağımız rolleri belirtiyoruz. @get:Rule junit e bağlıdır.
    //Burada her işlemi instant bir şekilde execute ediyoruz. Yani herşeyi MainThread de çalıştırmamızı sağlıyor.
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Aşağıdaki kod sayesinde test edeceğimiz metodları hangi thread'de olursa olsun rahat test etmemize yarar.
    // Testlerimizde coroutine, threading vb kullanılmaması gerekiyor.
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup(){
        viewModel = ArtViewModel(FakeArtRepository())
    }

    //Test edilecek fonksiyonumuzun başına @Test eklemeliyiz. Aşağıdaki fonksiyonda kullanıcının yıl girmediği durumda hata mesajı alıp alamadığımızı kontrol ediyoruz.
    @Test
    fun `insert art without year returns error`() {
        viewModel.makeArt("Mona Lisa","Da Vinci","")
        //Normalde insert art mesajımızdan bize live data dönüyor. Fakat testlerde livedata kullanmadığımız için getOrAwaitValueTest ile datamızı normal dataya çevirdik.
        //getOrAwaitValueTest'i LiveDataUtil dosyamızdan çektik. internetten bulabilirsiniz.
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        //assertThat truth sınıfının bir fonksiyonudur. bu sayede iki değeri birbiri ile sorunsuz kıyaslayabiliyoruz. Aşağıda insertArtMessage'dan aldığımız değeri Status.Error ile kıyaslıyoruz.
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    //Aşağıdaki fonksiyonda kullanıcının isim girmediği durumda hata mesajı alıp alamadığımızı kontrol ediyoruz.
    @Test
    fun `insert art without name returns error`() {
        viewModel.makeArt("","Da Vinci","1500")

        val value = viewModel.insertArtMessage.getOrAwaitValueTest()

        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    //Aşağıdaki fonksiyonda kullanıcının isim girmediği durumda hata mesajı alıp alamadığımızı kontrol ediyoruz.
    @Test
    fun `insert art without artistName returns error`() {
        viewModel.makeArt("Mona Lisa","","1500")

        val value = viewModel.insertArtMessage.getOrAwaitValueTest()

        assertThat(value.status).isEqualTo(Status.ERROR)
    }
}