package fr.thatmuch.bonappetite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_menu_list.*

class MenuListActivity : AppCompatActivity() {
    lateinit var mDatabase: DatabaseReference
    lateinit var menuList : MutableList<Menu>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_list)

        // Stock the menus in a list
        menuList = mutableListOf()
        // Create a table "Menus" in the database
        mDatabase = FirebaseDatabase.getInstance().getReference("menus")
        listView = findViewById(R.id.listView)

        fun addMenu(){
            // Input text becomes the menu's name
            val menuName = addMenuTxt.text.toString()
            // Throw an error if the input text is empty
            if (addMenuTxt.text.toString().isEmpty()){
                addMenuTxt.error = "Entrer un nom"
                return
            }
            // Create a unique Id by new menu
            val menuId =  mDatabase.push().key
            // Create an instance of the object "Menu"
            val menu = Menu(menuName, menuId)
            // Save the menu in the database
            mDatabase.child(menuId).setValue(menu)
            addMenuTxt.text.clear()
        }

        // Add a new menu
        addMenuBtn.setOnClickListener{
            addMenu()
        }

        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot?) {
                if(p0!!.exists()){
                    menuList.clear()
                    for (m in p0.children){
                        val menu = m.getValue(Menu::class.java)
                        menuList.add(menu!!)
                    }
                    val adapter = MenuAdapter(applicationContext, R.layout.menus, menuList)
                    listView.adapter = adapter
                }
            }

        })

    }
}
