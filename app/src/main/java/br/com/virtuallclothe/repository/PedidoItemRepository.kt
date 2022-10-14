package br.com.virtuallclothe.repository

import android.content.Context
import br.com.virtuallclothe.models.PedidoItem

class PedidoItemRepository(context: Context) {

    private val pedidoItemDb = DataBase.getDataBase(context).pedidoItemDao()

    fun listarPedidoItemPorId(id: Int): PedidoItem {
        return pedidoItemDb.getPedidoItemById(id)
    }

    fun salvarPedidoItem(pedidoItem: PedidoItem): Long {
        return pedidoItemDb.save(pedidoItem)
    }

    fun listarPedidoItems(): List<PedidoItem> {
        return pedidoItemDb.getAllPedidoItems()
    }

    fun alterarPedidoItem(pedidoItem: PedidoItem): Int {
        return pedidoItemDb.update(pedidoItem)
    }

    fun deletarPedidoItem(pedidoItem: PedidoItem) {
        pedidoItemDb.delete(pedidoItem)
    }
}