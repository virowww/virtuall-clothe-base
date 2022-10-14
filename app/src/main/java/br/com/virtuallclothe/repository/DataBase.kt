package br.com.virtuallclothe.repository

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.virtuallclothe.repository.dao.PedidoDAO
import br.com.virtuallclothe.repository.dao.PedidoItemDAO
import br.com.virtuallclothe.repository.dao.ProdutoDAO

abstract class DataBase : RoomDatabase(){

    abstract fun produtoDao(): ProdutoDAO
    abstract fun pedidoDao(): PedidoDAO
    abstract fun pedidoItemDao(): PedidoItemDAO

    companion object{
        private lateinit var instance: DataBase

        fun getDataBase(context: Context): DataBase{
            if (!::instance.isInitialized){
                instance = Room.databaseBuilder(context, DataBase::class.java, "virtuallClotheDB")
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}