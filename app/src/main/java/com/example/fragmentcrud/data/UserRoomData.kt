package com.example.fragmentcrud.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.example.fragmentcrud.data.Fragments.model.User


@Database(entities = [User::class],version= 1, exportSchema = true)
abstract class UserRoomData :RoomDatabase(){

    abstract val  userDao:UserDao

    companion object {
        @Volatile
        var INSTANCE: UserRoomData? = null

        fun getInstance(context:Context): UserRoomData {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {

                val Instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        UserRoomData::class.java,
                        "user_table"
                    ).allowMainThreadQueries()

                        .build()

                     INSTANCE=Instance
                return Instance
            }
        }

}}