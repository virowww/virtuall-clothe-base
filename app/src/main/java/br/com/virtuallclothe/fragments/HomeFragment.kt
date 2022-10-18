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
import br.com.virtuallclothe.models.Produto
import br.com.virtuallclothe.repository.ProdutoRepository
import br.com.virtuallclothe.utils.toByteArray

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    var produtoAdapter: ProductListAdapter? = null
    var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iniciarRecyclerView()
    }

    private fun iniciarRecyclerView() {
        produtoAdapter = ProductListAdapter(getProducts(), requireContext())
        linearLayoutManager = LinearLayoutManager(requireContext())

        binding.productList.layoutManager = linearLayoutManager
        binding.productList.adapter = produtoAdapter
    }

    private fun getProducts(): List<Produto>{
        val repo = ProdutoRepository (requireContext())
        val list: List<Produto> = repo.listarProdutos()
        return list.ifEmpty {
            saveProducts()
        }
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
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}