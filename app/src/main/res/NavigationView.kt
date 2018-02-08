import android.view.MenuItem

/**
 * Created by marconte on 08/02/2018.
 */
class NavigationView {
    interface OnNavigationItemSelectedListener {
        fun onNavigationItemSelected(item: MenuItem): Boolean
    }
}