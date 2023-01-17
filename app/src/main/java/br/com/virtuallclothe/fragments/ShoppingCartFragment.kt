package br.com.virtuallclothe.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.virtuallclothe.adapter.OrderItemListAdapter
import br.com.virtuallclothe.databinding.FragmentShoppingCartBinding
import br.com.virtuallclothe.models.Pedido

class ShoppingCartFragment : Fragment() {

    companion object {
        const val ARG_ORDER = "order"

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
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        instanceOrder = if (arguments?.getSerializable(ProductFragment.ARG_ORDER) as Pedido == null) Pedido(0, 0.0, emptyList()) else arguments?.getSerializable(ProductFragment.ARG_ORDER) as Pedido
        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iniciarRecyclerView()
    }

    private fun iniciarRecyclerView() {

        orderItemAdapter = OrderItemListAdapter(instanceOrder, requireContext())
        linearLayoutManager = LinearLayoutManager(requireContext())

        binding.orderItemList.layoutManager = linearLayoutManager
        binding.orderItemList.adapter = orderItemAdapter
    }

}