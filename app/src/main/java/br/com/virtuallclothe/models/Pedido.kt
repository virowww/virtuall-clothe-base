package br.com.virtuallclothe.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pedido")
class Pedido(id: Int, total: Double, productList: List<Produto>) : java.io.Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = id

    @ColumnInfo(name = "nome")
    var total: Double = total

    @Transient
    var productList: List<Produto> = productList
}