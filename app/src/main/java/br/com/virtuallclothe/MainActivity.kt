package br.com.virtuallclothe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.virtuallclothe.databinding.ActivityMainBinding
import br.com.virtuallclothe.fragments.HomeFragment
import br.com.virtuallclothe.fragments.ShoppingCartFragment

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

    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

}

