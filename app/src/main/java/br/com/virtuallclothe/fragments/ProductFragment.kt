package br.com.virtuallclothe.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import br.com.virtuallclothe.R
import br.com.virtuallclothe.databinding.FragmentProductBinding
import br.com.virtuallclothe.models.Pedido
import br.com.virtuallclothe.models.Produto
import br.com.virtuallclothe.utils.toBitmap

class ProductFragment() : Fragment() {

    companion object {
        const val ARG_ORDER = "order"
        const val ARG_PRODUCT = "product"

        fun newInstance(product: Produto, order: Pedido): ProductFragment {
            val fragment = ProductFragment()

            val bundle = Bundle().apply {
                putSerializable(ARG_ORDER, order)
                putSerializable(ARG_PRODUCT, product)
            }

            fragment.arguments = bundle

            return fragment
        }
    }

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private lateinit var instanceProduct: Produto
    private lateinit var instanceOrder: Pedido

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        instanceOrder = (arguments?.getSerializable(ARG_ORDER) ?: Pedido(0,0.0, emptyList())) as Pedido
        instanceProduct = (arguments?.getSerializable(ARG_PRODUCT) ?: Produto(0, "", 0.0, "", byteArrayOf())) as Produto
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView.setImageBitmap(instanceProduct.imagem?.toBitmap())
        binding.textPrice.text = "R$ " + instanceProduct.valor.toString()
        binding.textName.text = instanceProduct.nome

        binding.comprar.setOnClickListener {
            instanceProduct.qtd += 1
            instanceOrder.productList = instanceOrder.productList?.plus(instanceProduct)
            (requireContext() as FragmentActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fl_wrapper, ShoppingCartFragment.newInstance(instanceOrder))
                .commit()
        }
    }

}