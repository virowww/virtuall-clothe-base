package br.com.virtuallclothe.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
import br.com.virtuallclothe.adapter.OrderItemListAdapter
import br.com.virtuallclothe.databinding.FragmentShoppingCartBinding
import br.com.virtuallclothe.models.Pedido
import java.math.BigDecimal
import java.math.RoundingMode

class ShoppingCartFragment : Fragment() {

    companion object {
        private const val ARG_ORDER = "order"

        fun newInstance(order: Pedido): ShoppingCartFragment {
            val fragment = ShoppingCartFragment()

            val bundle = Bundle().apply {
                putSerializable(ARG_ORDER, order)
            }

            fragment.arguments = bundle

            return fragment
        }
    }

    private var _binding: FragmentShoppingCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var instanceOrder: Pedido

    var orderItemAdapter: OrderItemListAdapter? = null
    var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        instanceOrder = arguments?.getSerializable(ProductFragment.ARG_ORDER) as Pedido
        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iniciarRecyclerView()
    }

    private fun iniciarRecyclerView() {

        orderItemAdapter = OrderItemListAdapter(instanceOrder, requireContext())
        orderItemAdapter!!.registerAdapterDataObserver(object: AdapterDataObserver(){
            override fun onChanged() {
                super.onChanged()
                binding.orderPriceText.text = getOrderPrice()
            }
        })
        linearLayoutManager = LinearLayoutManager(requireContext())

        binding.orderItemList.layoutManager = linearLayoutManager
        binding.orderItemList.adapter = orderItemAdapter
        binding.orderItemList.addItemDecoration(DividerItemDecoration(context, linearLayoutManager!!.orientation))
        binding.orderPriceText.text = getOrderPrice()
    }

    private fun getOrderPrice(): String{
        var price = 0.0

        instanceOrder.productList?.forEach{
            price += it.valor * it.qtd
        }

        return "R$ " + BigDecimal(price).setScale(2, RoundingMode.HALF_UP)
    }

}