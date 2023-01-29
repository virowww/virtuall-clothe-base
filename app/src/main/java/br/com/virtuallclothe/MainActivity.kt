package br.com.virtuallclothe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.virtuallclothe.databinding.ActivityMainBinding
import br.com.virtuallclothe.fragments.HomeFragment
import br.com.virtuallclothe.fragments.ShoppingCartFragment
import br.com.virtuallclothe.models.Pedido

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val order = getOrder()
        val shoppingCartFragment = ShoppingCartFragment.newInstance(order)
        val homeFragment = HomeFragment.newInstance(order)

        makeCurrentFragment(homeFragment)

        binding.topNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.app_title -> makeCurrentFragment(homeFragment)
                R.id.shopping_cart -> makeCurrentFragment(shoppingCartFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {

            if (fragment is HomeFragment) {
                replace(R.id.fl_wrapper, HomeFragment.newInstance(getOrder()))
                commit()
            } else if (fragment is ShoppingCartFragment) {
                replace(R.id.fl_wrapper, ShoppingCartFragment.newInstance(getOrder()))
                commit()
            }
        }

    private fun getOrder() : Pedido {
        val order = intent.getSerializableExtra("order")
        return if (order != null) order as Pedido else Pedido(0, 0.0, emptyList())
    }
}