package br.com.virtuallclothe.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

@Entity(tableName = "pedido")
class Pedido(id: Int, total: Double, productList: List<Produto>) : java.io.Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = id

    @ColumnInfo(name = "nome")
    var total: Double = total

    @ColumnInfo(name = "productList")
    var productList: List<Produto> = productList
}

class PedidoConverter {
    @TypeConverter
    fun listToJson(value: List<Produto>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Produto>::class.java).toList()
}