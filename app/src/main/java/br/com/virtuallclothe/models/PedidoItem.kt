package br.com.virtuallclothe.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "pedido_item")
class PedidoItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_pedido")
    var idPedido: Int = 0

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_produto")
    var idProduto: Int = 0

    @ColumnInfo(name = "quantidade")
    var qtd: Int = 0

    @ColumnInfo(name = "sub_total")
    var subTotal: BigDecimal = BigDecimal.ZERO

}