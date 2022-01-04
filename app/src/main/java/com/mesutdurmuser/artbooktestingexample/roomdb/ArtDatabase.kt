package com.mesutdurmuser.artbooktestingexample.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase

//Room database in bizden içtedi üç öğeden biri olan database tarafını yazdık. @Database ile bir database olduğunu belirttik
//entities olarak Art class ımızı verdik ve version numaralandırdık. Ardından model database ve daomuzu birbirine bağladık.
@Database(entities = [Art::class], version = 1)
abstract class ArtDatabase : RoomDatabase() {
    abstract fun artDao() : ArtDao
}