package br.com.virtuallclothe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.virtuallclothe.adapter.ProductListAdapter
import br.com.virtuallclothe.databinding.ActivityMainBinding
import br.com.virtuallclothe.fragments.HomeFragment
import br.com.virtuallclothe.fragments.ShoppingCartFragment
import br.com.virtuallclothe.models.Produto
import br.com.virtuallclothe.repository.ProdutoRepository

class MainActivity : AppCompatActivity() {

    var produtoAdapter: ProductListAdapter? = null
    var linearLayoutManager: LinearLayoutManager? = null

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shoppingCartFragment = ShoppingCartFragment()
        val homeFragment = HomeFragment()

        makeCurrentFragment(homeFragment)

        binding.topNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.app_title -> makeCurrentFragment(homeFragment)
                R.id.shopping_cart -> makeCurrentFragment(shoppingCartFragment)
            }
            true
        }
        iniciarRecyclerView()
    }

    private fun iniciarRecyclerView() {
        produtoAdapter = ProductListAdapter(getProducts(), this)
        linearLayoutManager = LinearLayoutManager(this)
        binding.root.findViewById<RecyclerView>(R.id.product_list).layoutManager = linearLayoutManager
        binding.root.findViewById<RecyclerView>(R.id.product_list).adapter = produtoAdapter
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

    fun getProducts(): List<Produto>{
        val repo = ProdutoRepository (this);
        val list: List<Produto> = repo.listarProdutos()
        return list
//        var icon = BitmapFactory.decodeResource(resources, R.drawable.camiseta_preta)
//        repo.salvarProduto(Produto(1, "Camiseta Preta", 49.99, "", icon.toByteArray()))
//
//        icon = BitmapFactory.decodeResource(resources, R.drawable.camiseta_branca)
//        repo.salvarProduto(Produto(2, "Camiseta Branca", 35.99, "", icon.toByteArray()))
//
//        icon = BitmapFactory.decodeResource(resources, R.drawable.camiseta_vermelha)
//        repo.salvarProduto(Produto(3, "Camiseta Vermelha", 29.99, "", icon.toByteArray()))
//
//        icon = BitmapFactory.decodeResource(resources, R.drawable.camiseta_roxo)
//        repo.salvarProduto(Produto(4, "Camiseta Roxa", 35.99, "", icon.toByteArray()))
//
//        icon = BitmapFactory.decodeResource(resources, R.drawable.camiseta_verde)
//        repo.salvarProduto(Produto(5, "Camiseta Verde", 29.99, "", icon.toByteArray()))
    }
}

