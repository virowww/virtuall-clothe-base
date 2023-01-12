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

/**
 * A simple [Fragment] subclass.
 * Use the [ProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductFragment(product: Produto, order: Pedido) : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private val instanceProduct = product
    private val instanceOrder = order

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView.setImageBitmap(instanceProduct.imagem?.toBitmap())
        binding.textPrice.text = "R$ " + instanceProduct.valor.toString()
        binding.textName.text = instanceProduct.nome

        binding.comprar.setOnClickListener{
            instanceProduct.qtd += 1
            instanceOrder.productList = instanceOrder.productList?.plus(instanceProduct)
            (requireContext() as FragmentActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fl_wrapper, ShoppingCartFragment(instanceOrder))
                .commit()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ProductFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(product: Produto, order: Pedido) =
            ProductFragment(product, order).apply {
            }
    }
}