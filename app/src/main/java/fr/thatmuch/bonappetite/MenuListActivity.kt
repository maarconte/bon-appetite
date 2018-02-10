package fr.thatmuch.bonappetite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.widget.ListView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_menu_list.*

class MenuListActivity : AppCompatActivity() {
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_list)

        fun addMenu(){
            // Input text becomes the menu's name
            val menuName = addMenuTxt.text.toString()
            // Throw an error if the input text is empty
            if (addMenuTxt.text.toString().isEmpty()){
                addMenuTxt.error = "Entrer un nom"
                return
            }

            // Create a table "Menus" in the database
            mDatabase = FirebaseDatabase.getInstance().getReference("menus")
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

    }
}
