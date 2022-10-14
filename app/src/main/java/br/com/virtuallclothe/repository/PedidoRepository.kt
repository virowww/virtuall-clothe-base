package br.com.virtuallclothe.repository

import android.content.Context
import br.com.virtuallclothe.models.Pedido

class PedidoRepository(context: Context){

    private val pedidoDb = DataBase.getDataBase(context).pedidoDao()

    fun listarPedidoPorId(id: Int): Pedido {
        return pedidoDb.getPedidoById(id)
    }

    fun salvarPedido(pedido: Pedido): Long {
        return pedidoDb.save(pedido)
    }

    fun listarPedidos(): List<Pedido> {
        return pedidoDb.getAllPedidos()
    }

    fun alterarPedido(pedido: Pedido): Int {
        return pedidoDb.update(pedido)
    }

    fun deletarPedido(pedido: Pedido) {
        pedidoDb.delete(pedido)
    }
}