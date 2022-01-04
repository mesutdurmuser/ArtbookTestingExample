package com.mesutdurmuser.artbooktestingexample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Bu sınıfımız Hilt'i kullanabilmemiz için zorunludur. Projelerde genelde application ile biten şekilde isimlendirilir.
//Ve Applicationdan kalıtım alır. @HiltAndroidApp'i belirmemiz yeterlidir. Body'ye sahip olmasına gerek yoktur.
//Mutlaka AndroidManifest'e eklememiz gerekiyor. (android:name=".ArtbookApplication")
//Ve son olarak modüllerimizi oluşturmamız.

@HiltAndroidApp
class ArtbookApplication : Application()