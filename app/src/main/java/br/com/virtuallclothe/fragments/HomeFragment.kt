package br.com.virtuallclothe.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.virtuallclothe.R
import br.com.virtuallclothe.adapter.ProductListAdapter
import br.com.virtuallclothe.databinding.FragmentHomeBinding
import br.com.virtuallclothe.models.Pedido
import br.com.virtuallclothe.models.Produto
import br.com.virtuallclothe.repository.ProdutoRepository
import br.com.virtuallclothe.utils.toByteArray

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var instanceOrder: Pedido

    var produtoAdapter: ProductListAdapter? = null
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
        iniciarRecyclerView()
    }

    private fun iniciarRecyclerView() {
        produtoAdapter = ProductListAdapter(getProducts(), instanceOrder, requireContext())
        linearLayoutManager = LinearLayoutManager(requireContext())

        binding.productList.layoutManager = linearLayoutManager
        binding.productList.adapter = produtoAdapter
        binding.searchButton.setOnClickListener{
            produtoAdapter = ProductListAdapter(getProductsByName(binding.searchText.text.toString()), instanceOrder, requireContext())
            binding.productList.adapter = produtoAdapter
        }
    }

    private fun getProducts(): List<Produto>{
        val repo = ProdutoRepository (requireContext())
        val list: List<Produto> = repo.listarProdutos()
        return list.ifEmpty {
            saveProducts()
        }
    }

    private fun getProductsByName(productName: String): List<Produto> {
        if (productName.isBlank()) {
            return getProducts()
        }
        val repo = ProdutoRepository(requireContext())
        return repo.listarProdutosPorNome(productName)
    }

    private fun saveProducts(): List<Produto>{
        val repo = ProdutoRepository (requireContext())

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

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
}