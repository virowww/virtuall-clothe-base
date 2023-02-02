package br.com.virtuallclothe.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import br.com.virtuallclothe.R
import br.com.virtuallclothe.models.Produto
import br.com.virtuallclothe.repository.ProdutoRepository
import java.io.ByteArrayOutputStream
import java.math.BigDecimal
import java.math.RoundingMode

fun Bitmap.toByteArray(): ByteArray {

    val stream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.PNG, 100, stream)

    if(stream.toByteArray().size > 500000){
        return stream.compressByteArray()
    }
    return stream.toByteArray()

}

fun ByteArray.toBitmap(): Bitmap{
    return BitmapFactory.decodeByteArray(this, 0, this.size)
}

fun ByteArrayOutputStream.compressByteArray(): ByteArray {
    val bitmap: Bitmap = this.toByteArray().toBitmap()
    val resized: Bitmap = Bitmap.createScaledBitmap(bitmap, (bitmap.width*0.8).toInt(), (bitmap.height*0.8).toInt(), true)
    resized.compress(Bitmap.CompressFormat.PNG, 100, this)
    return this.toByteArray()
}

fun returnStringPrice(value: Double): String{
    return "R$ " + BigDecimal(value).setScale(2, RoundingMode.HALF_UP)
}

fun getOrderPrice(productList: List<Produto>): String{
    var price = 0.0
    productList.forEach{
        price += it.valor * it.qtd
    }
    return returnStringPrice(price)
}

fun getProducts(context: Context, resources: Resources): List<Produto>{
    val repo = ProdutoRepository (context)
    val list: List<Produto> = repo.listarProdutos()
    return list.ifEmpty {
        saveProducts(context, resources)
    }
}

fun saveProducts(context: Context, resources: Resources): List<Produto>{
    val repo = ProdutoRepository (context)

    val icon = BitmapFactory.decodeResource(resources, R.drawable.camiseta_preta)
    repo.salvarProduto(Produto(1, "Camiseta Preta", 49.99, "", icon.toByteArray()))

    val icon2 = BitmapFactory.decodeResource(resources, R.drawable.camiseta_branca)
    repo.salvarProduto(Produto(2, "Camiseta Branca", 35.99, "", icon2.toByteArray()))

    val icon3 = BitmapFactory.decodeResource(resources, R.drawable.camiseta_vermelha)
    repo.salvarProduto(Produto(3, "Camiseta Vermelha", 29.99, "", icon3.toByteArray()))

    val icon4 = BitmapFactory.decodeResource(resources, R.drawable.camiseta_roxo)
    repo.salvarProduto(Produto(4, "Camiseta Roxa", 35.99, "", icon4.toByteArray()))

    val icon5 = BitmapFactory.decodeResource(resources, R.drawable.camiseta_verde)
    repo.salvarProduto(Produto(5, "Camiseta Verde", 29.99, "", icon5.toByteArray()))

    return repo.listarProdutos()
}

fun getProductsByName(productName: String, context: Context, resources: Resources): List<Produto> {
    if (productName.isBlank()) {
        return getProducts(context, resources)
    }
    val repo = ProdutoRepository(context)
    return repo.listarProdutosPorNome(productName)
}