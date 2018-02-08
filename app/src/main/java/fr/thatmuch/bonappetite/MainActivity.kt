package fr.thatmuch.bonappetite

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.my_menus.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val menus = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setContentView(R.layout.my_menus)
        setSupportActionBar(toolbar)
        // Keep the last random menu index in memory
        var lastRandom = 0

        fun chooseMenu(){
            val random = Random()
            var randomMenu : Int
            if(menus.count() > 0) {
                do {
                    randomMenu = random.nextInt(menus.count())
                } while (randomMenu == lastRandom)
                lastRandom = randomMenu

                selectedFood.text = menus[randomMenu]
            }
        }

        fun addMenu(){
            val newMenu = addMenuTxt.text.toString()
            menus.add(newMenu)
            addMenuTxt.text.clear()
        }

        chooseMenu.setOnClickListener{
            chooseMenu()
         }
        /* addMenuBtn.setOnClickListener{
            addMenu()
        }*/

        val toggle = ActionBarDrawerToggle(
                this, /* host activity */
                drawer_layout, /* Drawer Layout object */
                toolbar, /* Toolbar Object */
                R.string.nav_open, /* description for accessibility */
                R.string.nav_closed  /* description for accessibility */
        )
// Set the drawer toggle as the DrawerListener
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        // Set-up the NavigationView and its listener
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    // Called when an item in the navigation menu is selected.
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId
        when (id) {
            R.id.my_menus -> Toast.makeText(this, "Mes Menus", Toast.LENGTH_SHORT).show()
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


}
