package br.com.virtuallclothe.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "produto")
class Produto(id: Int, nome: String, valor: Double, tamanho: String, imagem: ByteArray) :
    java.io.Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = id

    @ColumnInfo(name = "nome")
    var nome: String = nome

    @ColumnInfo(name = "valor")
    var valor: Double = valor

    @ColumnInfo(name = "tamanho")
    var tamanho: String = tamanho

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var imagem: ByteArray? = imagem

    @Transient
    var subTotal: Double = 0.0

    @Transient
    var qtd: Int = 0

}