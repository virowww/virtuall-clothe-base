package br.com.virtuallclothe.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.virtuallclothe.models.Pedido
import br.com.virtuallclothe.models.PedidoConverter
import br.com.virtuallclothe.models.PedidoItem
import br.com.virtuallclothe.models.Produto
import br.com.virtuallclothe.repository.dao.PedidoDAO
import br.com.virtuallclothe.repository.dao.PedidoItemDAO
import br.com.virtuallclothe.repository.dao.ProdutoDAO

@Database(entities = [Pedido::class, Produto::class, PedidoItem::class ], version = 1)
@TypeConverters(PedidoConverter::class)
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