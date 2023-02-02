package br.com.virtuallclothe.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
import br.com.virtuallclothe.R
import br.com.virtuallclothe.adapter.OrderItemListAdapter
import br.com.virtuallclothe.databinding.FragmentShoppingCartBinding
import br.com.virtuallclothe.models.Pedido
import br.com.virtuallclothe.repository.PedidoRepository
import br.com.virtuallclothe.utils.getOrderPrice

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
        instanceOrder = arguments?.getSerializable(ARG_ORDER) as Pedido
        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadRecyclerView()
    }

    private fun loadRecyclerView() {
        orderItemAdapter = OrderItemListAdapter(instanceOrder, requireContext())
        orderItemAdapter!!.registerAdapterDataObserver(object: AdapterDataObserver(){
            override fun onChanged() {
                super.onChanged()
                activity?.intent?.putExtra(ARG_ORDER, instanceOrder)
                binding.orderPriceText.text = instanceOrder.productList?.let { getOrderPrice(it) }
            }
        })
        linearLayoutManager = LinearLayoutManager(context)

        binding.orderItemList.layoutManager = linearLayoutManager
        binding.orderItemList.adapter = orderItemAdapter
        binding.orderItemList.addItemDecoration(DividerItemDecoration(context, linearLayoutManager!!.orientation))
        binding.orderPriceText.text = instanceOrder.productList?.let { getOrderPrice(it) }
        binding.comprar.setOnClickListener{
            showConfirmPurchaseDialog()
        }
    }

    private fun showConfirmPurchaseDialog() {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.apply {
            setIcon(R.drawable.ic_baseline_shopping_cart_24)
            setTitle("Deseja finalizar pedido?")
            setPositiveButton("Sim"){ _, _ -> saveOrder()}
            setNegativeButton("Não"){_,_ ->}
        }.create().show()
    }

    private fun saveOrder() {
        context?.let { PedidoRepository(it) }?.salvarPedido(instanceOrder)

        val alertDialog = AlertDialog.Builder(context)
        alertDialog.apply {
            setIcon(R.drawable.ic_soccer)
            setTitle("Pedido feito com sucesso.")
            setPositiveButton("Ok"){ _, _ -> resetAndCallHomeFragment()}
        }.create().show()
    }

    private fun resetAndCallHomeFragment(){
        instanceOrder = Pedido(0, 0.0, emptyList())
        activity?.intent?.putExtra(ARG_ORDER, instanceOrder)
        loadRecyclerView()
        (context as FragmentActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.fl_wrapper, HomeFragment.newInstance(instanceOrder))
            .commit()
    }

}