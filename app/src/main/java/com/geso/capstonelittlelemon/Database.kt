package com.geso.capstonelittlelemon

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Database(entities = [MenuItem::class], version = 1)
abstract class MenuDatabase : RoomDatabase() {
    abstract fun menuDao() : MenuDao
}

@Dao
interface MenuDao {
    @Query("SELECT * FROM Menu")
    fun getAllMenuItems() : LiveData<List<MenuItem>>

    @Insert
    fun saveMenuItem(menuItem: MenuItem)

    @Delete
    fun deleteMenuItem(menuItem: MenuItem)

    @Query("Delete FROM menu")
    fun deleteAllMenuItems()

    @Query("SELECT * FROM menu WHERE category = :category")
    fun getCategoryMenuItems(category: String) : List<MenuItem>
}


@Entity(tableName = "Menu")
data class MenuItem(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
)