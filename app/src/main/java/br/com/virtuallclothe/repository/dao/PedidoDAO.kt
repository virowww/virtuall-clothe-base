package br.com.virtuallclothe.repository.dao

import androidx.room.*
import br.com.virtuallclothe.models.Pedido

@Dao
interface PedidoDAO {

    @Insert
    fun save(pedido: Pedido): Long

    @Update
    fun update(pedido: Pedido): Int

    @Delete
    fun delete(pedido: Pedido)

    @Query("SELECT * FROM pedido")
    fun getAllPedidos(): List<Pedido>

    @Query("SELECT * FROM pedido WHERE id = :id")
    fun getPedidoById(id: Int): Pedido
}