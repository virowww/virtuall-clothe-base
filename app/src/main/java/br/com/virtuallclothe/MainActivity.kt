package br.com.virtuallclothe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import br.com.virtuallclothe.fragments.HomeFragment
import br.com.virtuallclothe.fragments.ShoppingCartFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shoppingCartFragment = ShoppingCartFragment()
        val homeFragment = HomeFragment()

        makeCurrentFragmentHome(homeFragment)

        setView
    }

    private fun makeCurrentFragmentHome(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
}

