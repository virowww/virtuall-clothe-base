package br.com.virtuallclothe.repository

import android.content.Context
import br.com.virtuallclothe.models.Produto

class ProdutoRepository(context: Context) {
    private val produtoDb = DataBase.getDataBase(context).produtoDao()

    fun listarProdutoPorId(id: Int): Produto {
        return produtoDb.getProdutoById(id)
    }

    fun salvarProduto(produto: Produto): Long {
        return produtoDb.save(produto)
    }

    fun listarProdutos(): List<Produto> {
        return produtoDb.getAllProdutos()
    }

    fun alterarProduto(produto: Produto): Int {
        return produtoDb.update(produto)
    }

    fun deletarProduto(produto: Produto) {
        produtoDb.delete(produto)
    }

    fun listarProdutosPorNome(nome: String): List<Produto> {
        return produtoDb.listarProdutosPorNome(nome)
    }
}