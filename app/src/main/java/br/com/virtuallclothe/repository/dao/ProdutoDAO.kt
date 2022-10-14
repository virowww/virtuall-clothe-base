package br.com.virtuallclothe.repository.dao

import androidx.room.*
import br.com.virtuallclothe.models.Produto

@Dao
interface ProdutoDAO {

    @Insert
    fun save(produto: Produto): Long

    @Update
    fun update(produto: Produto): Int

    @Delete
    fun delete(produto: Produto)

    @Query("SELECT * FROM produto")
    fun getAllProdutos(): List<Produto>

    @Query("SELECT * FROM produto WHERE id = :id")
    fun getProdutoById(id: Int): Produto
}