package br.com.virtuallclothe.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "pedido_item",
    foreignKeys = [ForeignKey(
        entity = Pedido::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("id_pedido"),
        onDelete = ForeignKey.CASCADE
    ),
        ForeignKey(
            entity = Produto::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("id_produto"),
            onDelete = ForeignKey.CASCADE
        )]
)
class PedidoItem : java.io.Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "id_pedido")
    var idPedido: Int = 0

    @ColumnInfo(name = "id_produto")
    var idProduto: Int = 0

    @ColumnInfo(name = "quantidade")
    var qtd: Int = 0

    @ColumnInfo(name = "sub_total")
    var subTotal: Double = 0.00

}