package fr.thatmuch.bonappetite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

/**
 * Created by marconte on 10/02/2018.
 */
class MenuAdapter(val mCtxt: Context, val layoutResId: Int, val menuList: List<Menu> )
    :ArrayAdapter<Menu>(mCtxt,layoutResId,menuList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtxt)
        val view : View = layoutInflater.inflate(layoutResId, null)
        // Find text view
        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        val menu = menuList[position]
        textViewName.text = menu.name

         return view
    }
}