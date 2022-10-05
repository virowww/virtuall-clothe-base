package br.com.virtuallclothe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import br.com.virtuallclothe.fragments.HomeFragment
import br.com.virtuallclothe.fragments.ShoppingCartFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shoppingCartFragment = ShoppingCartFragment()
        val homeFragment = HomeFragment()

        makeCurrentFragment(homeFragment)

        val b = findViewById<BottomNavigationView>(R.id.top_navigation)

        b.setOnNavigationItemSelectedListener {
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

