package br.com.virtuallclothe.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.virtuallclothe.R
import br.com.virtuallclothe.fragments.ProductFragment
import br.com.virtuallclothe.models.Pedido
import br.com.virtuallclothe.models.Produto
import br.com.virtuallclothe.utils.returnStringPrice
import br.com.virtuallclothe.utils.toBitmap


class ProductListAdapter(productList: List<Produto>, order: Pedido, private var ctx: Context):
    RecyclerView.Adapter<ProductListAdapter.ProdutoViewHolder>() {

    private var productList: List<Produto> = ArrayList()
    private var instanceOrder: Pedido = order
    init {
        this.productList = productList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.product_item, parent, false)
        return ProdutoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val product = productList[position]

        holder.name.text = product.nome
        holder.price.text = returnStringPrice(product.valor)
        holder.image.setImageBitmap(product.imagem!!.toBitmap())
        holder.linearLayoutItem.setOnClickListener {
            (ctx as FragmentActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fl_wrapper, ProductFragment.newInstance(product, instanceOrder))
                .commit()
        }

        if(position % 2 == 0){
            holder.linearLayoutItem.setBackgroundColor(ContextCompat.getColor(ctx ,R.color.grey))
            holder.price.setTextColor(Color.WHITE)
            holder.name.setTextColor(Color.WHITE)
        } else {
            holder.linearLayoutItem.setBackgroundColor(Color.WHITE)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ProdutoViewHolder(view: View): RecyclerView.ViewHolder(view){
        var name: TextView = view.findViewById(R.id.product_name)
        var price: TextView = view.findViewById(R.id.product_price)
        var image: ImageView = view.findViewById(R.id.product_image)
        var linearLayoutItem: LinearLayout = view.findViewById(R.id.linear_layout_item)
    }
}

