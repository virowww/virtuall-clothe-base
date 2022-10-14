package br.com.virtuallclothe

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.roomprof.utils.toByteArray
import br.com.virtuallclothe.adapter.ProductListAdapter
import br.com.virtuallclothe.databinding.ActivityMainBinding
import br.com.virtuallclothe.fragments.HomeFragment
import br.com.virtuallclothe.fragments.ShoppingCartFragment
import br.com.virtuallclothe.models.Produto
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

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

    private fun iniciarRecyclerView(){
        val productRecyclerView = findViewById<RecyclerView>(R.id.product_list)
        productRecyclerView.layoutManager = LinearLayoutManager(this)

        productRecyclerView.adapter = ProductListAdapter(getProducts(), this)
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

    fun getProducts(): ArrayList<Produto>{
        val products = ArrayList<Produto>()

        var icon = BitmapFactory.decodeResource(resources, R.drawable.camiseta_preta)
        products.add(Produto(1, "Camiseta Preta", BigDecimal("49.99"), "", icon.toByteArray()))

        icon = BitmapFactory.decodeResource(resources, R.drawable.camiseta_branca)
        products.add(Produto(1, "Camiseta Branca", BigDecimal("35.99"), "", icon.toByteArray()))

        icon = BitmapFactory.decodeResource(resources, R.drawable.camiseta_vermelha)
        products.add(Produto(1, "Camiseta Vermelha", BigDecimal("29.99"), "", icon.toByteArray()))

        icon = BitmapFactory.decodeResource(resources, R.drawable.camiseta_roxo)
        products.add(Produto(1, "Camiseta Roxa", BigDecimal("35.99"), "", icon.toByteArray()))

        icon = BitmapFactory.decodeResource(resources, R.drawable.camiseta_verde)
        products.add(Produto(1, "Camiseta Verde", BigDecimal("29.99"), "", icon.toByteArray()))

        return products
    }
}

