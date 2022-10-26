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
import androidx.recyclerview.widget.RecyclerView
import br.com.virtuallclothe.R
import br.com.virtuallclothe.models.Pedido
import br.com.virtuallclothe.utils.toBitmap

class OrderItemListAdapter(order: Pedido, private var ctx: Context) :
    RecyclerView.Adapter<OrderItemListAdapter.PedidoViewHolder>() {

    private val order: Pedido

    init {
        this.order = order
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.order_item, parent, false)
        return PedidoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        val orderItem = order.productList[position]

        holder.name.text = orderItem.nome
        holder.price.text = "R$ " + orderItem.valor.toString()
        holder.image.setImageBitmap(orderItem.imagem!!.toBitmap())
        holder.qtd.text = orderItem.qtd.toString()

        if (position % 2 == 0) {
            holder.linearLayoutItem.setBackgroundColor(ContextCompat.getColor(ctx, R.color.grey))
            holder.price.setTextColor(Color.WHITE)
            holder.name.setTextColor(Color.WHITE)
        } else {
            holder.linearLayoutItem.setBackgroundColor(Color.WHITE)
        }
    }

    override fun getItemCount(): Int {
        return order.productList.size
    }

    class PedidoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.product_name)
        var price: TextView = view.findViewById(R.id.product_price)
        var image: ImageView = view.findViewById(R.id.product_image)
        var qtd: TextView = view.findViewById(R.id.product_quantity)
        var linearLayoutItem: LinearLayout = view.findViewById(R.id.linear_layout_order_item)
    }
}