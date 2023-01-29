package br.com.virtuallclothe.repository.dao

import androidx.room.*
import br.com.virtuallclothe.models.PedidoItem

@Dao
interface PedidoItemDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(pedidoItem: PedidoItem): Long

    @Update
    fun update(pedidoItem: PedidoItem): Int

    @Delete
    fun delete(pedidoItem: PedidoItem)

    @Query("SELECT * FROM pedido_item")
    fun getAllPedidoItems(): List<PedidoItem>

    @Query("SELECT * FROM pedido_item WHERE id = :id")
    fun getPedidoItemById(id: Int): PedidoItem

}