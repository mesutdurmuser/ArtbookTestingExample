package com.mesutdurmuser.artbooktestingexample.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*

//interfaceleri birden çok yerde kullanmak için tercih ediyoruz. Dao'yu diğer sınıflarda obje gibi kullanıp içerisindeki fonksiyonları çekeceğiz.
//@Dao ile bunun bir dao olduğunu belirtiyoruz. Room database..
@Dao
interface ArtDao {

    //burada suspend fun kullandık çünkü silme ekleme vb işlemleri yaparken Main Thread'ın meşgul edilmesini istemiyoruz
    //suspend fun coroutines ile kullanılan bir teknolojidir.
    //Insert ise eklemek için kullanacağımız fonksiyonu belirmek için kullandık. OnConflictStrategy ise herhangi bir çakışma durumunda ne yapılmasını istediğimizi belirttiğimiz bölüm. Biz replace seçtik yani ayni id den birşey olursa yerine yaz anlamında.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArt(art: Art)
    //burada ise room database imizden silme işlemini yapacağız @Delete dememiz yeterli olmaktadır.
    @Delete
    suspend fun deleteArt(art: Art)

    //Query (sorgu) için kullanacağımız metodumuz. Livedata yani canlı dinlenebilir datadan oluşturduk. Burada suspend kullanmadık çünkü Livedata senkron çalışan bir teknolojidir.
    //@Queryden sonra verdiğimiz SELECT * FROM arts ile art table'ındaki bütün verileri seç demek istiyoruz. Burada dikkat edilmesi gerekilen yer table isminin Art modelinde @Entity bölümünde verdiğimiz ile aynı olması gerekmektedir.
    @Query("SELECT * FROM arts")
    fun observeArts(): LiveData<List<Art>>
}