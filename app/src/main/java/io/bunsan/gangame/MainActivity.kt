package io.bunsan.gangame

import android.support.v4.app.Fragment
import kotlin.collections.hashMapOf
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.bunsan.gangame.deals.DealsFragment
import io.bunsan.gangame.owned.TopOwnedFragment
import io.bunsan.gangame.rated.TopRatedFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val DEFAULT_OPTION = R.id.action_deals
    }

    val fragments: HashMap<Int, Fragment> = hashMapOf(
            Pair(R.id.action_deals, DealsFragment()),
            Pair(R.id.action_top_rated, TopRatedFragment()),
            Pair(R.id.action_most_owned, TopOwnedFragment())
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView(savedInstanceState)

        navigationView.selectedItemId = R.id.action_deals
        navigationView.setOnNavigationItemSelectedListener { item ->
            val fragment: Fragment? = fragments[item.itemId]

            if (fragment != null)
                replaceFragment(R.id.fragment_container, fragment)

            true
        }

    }

    fun initView(savedInstanceState: Bundle?) {
        val currentFragment = supportFragmentManager
                .findFragmentById(R.id.fragment_container)

        if(currentFragment == null)
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, defaultFragment())
                    .commit()

    }

    private fun replaceFragment(container: Int, fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(container, fragment)
                .commit()
    }



    private fun defaultFragment() = fragments[DEFAULT_OPTION]
}
