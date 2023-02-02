package br.com.virtuallclothe.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.virtuallclothe.adapter.ProductListAdapter
import br.com.virtuallclothe.databinding.FragmentHomeBinding
import br.com.virtuallclothe.models.Pedido
import br.com.virtuallclothe.utils.getProducts
import br.com.virtuallclothe.utils.getProductsByName

class HomeFragment : Fragment() {

    companion object {
        const val ARG_ORDER = "order"

        fun newInstance(order: Pedido): HomeFragment {
            val fragment = HomeFragment()

            val bundle = Bundle().apply {
                putSerializable(ARG_ORDER, order)
            }

            fragment.arguments = bundle

            return fragment
        }
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var instanceOrder: Pedido

    var productListAdapter: ProductListAdapter? = null
    var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        instanceOrder = arguments?.getSerializable(ARG_ORDER) as Pedido
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productListAdapter = ProductListAdapter(getProducts(requireContext(), resources), instanceOrder, requireContext())
        linearLayoutManager = LinearLayoutManager(context)

        binding.productList.layoutManager = linearLayoutManager
        binding.productList.adapter = productListAdapter
        binding.searchButton.setOnClickListener{
            productListAdapter = ProductListAdapter(getProductsByName(binding.searchText.text.toString(), requireContext(), resources), instanceOrder, requireContext())
            binding.productList.adapter = productListAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}