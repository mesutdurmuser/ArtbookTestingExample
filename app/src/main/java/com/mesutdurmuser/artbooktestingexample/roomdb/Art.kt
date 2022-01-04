package com.mesutdurmuser.artbooktestingexample.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey
//Modelimiz
//@Entity diyerek bu sınıfın room database de kullanılacağını entity olarak kullanılacağını belirtiyoruz. ve yanına constructor ile tablo ismini belirtiyoruz
@Entity(tableName = "arts")
data class Art (
    var name: String,
    var artistName: String,
    var year: Int,
    var imageUrl: String,
    //burada room database in bir özelliği olan PrimaryKey kullandık. Kendinden sonra gelen değişkenin bir id olduğunu özel olduğunu ve otomatik olarak oluşturması gerektiğini söyledik.
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
)