package br.com.virtuallclothe.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.virtuallclothe.R
import br.com.virtuallclothe.models.Pedido
import br.com.virtuallclothe.utils.returnStringPrice
import br.com.virtuallclothe.utils.toBitmap

class OrderItemListAdapter(order: Pedido, private var ctx: Context): RecyclerView.Adapter<OrderItemListAdapter.PedidoViewHolder>() {

    private val order: Pedido

    init {
        this.order = order
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.order_item, parent, false)
        return PedidoViewHolder(view)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        val orderItem = order.productList!![position]

        holder.name.text = orderItem.nome

        orderItem.subTotal = orderItem.valor * orderItem.qtd
        holder.price.text = returnStringPrice(orderItem.subTotal)

        holder.image.setImageBitmap(orderItem.imagem!!.toBitmap())
        holder.qtd.text = orderItem.qtd.toString()
        holder.linearLayoutItem.setBackgroundColor(Color.WHITE)

        holder.addButton.setOnClickListener{
            orderItem.qtd = orderItem.qtd + 1
            holder.qtd.text = orderItem.qtd.toString()
            notifyDataSetChanged()
        }

        holder.subButton.setOnClickListener{
            if(holder.qtd.text.toString().toInt() > 0){
                orderItem.qtd = orderItem.qtd - 1
                holder.qtd.text = orderItem.qtd.toString()
                if(orderItem.qtd < 1){
                    val mutableList = order.productList!!.toMutableList()
                    mutableList.removeAt(position)
                    order.productList = mutableList
                }
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return order.productList!!.size
    }

    class PedidoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.product_name)
        var price: TextView = view.findViewById(R.id.product_price)
        var image: ImageView = view.findViewById(R.id.product_image)
        var qtd: TextView = view.findViewById(R.id.product_quantity)
        var linearLayoutItem: LinearLayout = view.findViewById(R.id.linear_layout_order_item)
        var addButton: ImageButton = view.findViewById(R.id.add)
        var subButton: ImageButton = view.findViewById(R.id.sub)
    }
}