package com.mabinogifanmade.squiretracker.activitiesfragments

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.mabinogifanmade.squiretracker.R
import com.mabinogifanmade.squiretracker.squiredata.Squire
import com.mabinogifanmade.squiretracker.userdata.PlayerChar
import com.mabinogifanmade.squiretracker.userdata.UserGeneral
import com.mabinogifanmade.squiretracker.utils.ConversationUtils
import com.mabinogifanmade.squiretracker.utils.ShrdPrfsUtils
import com.mabinogifanmade.squiretracker.utils.UserUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),
    MainFragment.OnFragmentInteractionListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
       // setDummyData()
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        val navController = Navigation.findNavController(this, R.id.nav_host)
        appBarConfiguration = AppBarConfiguration(navController.graph,drawerLayout)
        navView.setupWithNavController(navController)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onStart() {
        super.onStart()
        test()
    }

    fun setDummyData() {
        if (!ShrdPrfsUtils.userDataExist(this)) {
            val user: UserGeneral = UserGeneral(PlayerChar("Alaguesia", "Alexina"))
            val currentChar: PlayerChar = user.playerChars.get(0)
            currentChar.squiresActive.remove(1)
            currentChar.squiresActive.remove(3)
            user.prefersGrid = true
            ShrdPrfsUtils.saveUserData(this, user)
        }
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        //menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun updateInfoOnNav() {
        val headerView = nav_view.getHeaderView(0)
        headerView.findViewById<TextView>(R.id.navHeaderTitle)
            .setText(UserUtils.getCurrentCharPlayer(this).charName)
        headerView.findViewById<TextView>(R.id.navHeaderSubtitle).setText(UserUtils.getCurrentCharPlayer(this).server)
        headerView.findViewById<ImageView>(R.id.imageView).setImageDrawable(ContextCompat.getDrawable(this,UserUtils.getCurrentCharPlayer(this).avatar))
    }
   /* override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }*/

    fun test(){
        return
        Log.v("SquireConvo","Dai")
        var s:String = "Dai\n"
        for (i in 0..Squire.DAI.sequenceHint.length-1){
            s = s +ConversationUtils.translateAbv(Squire.DAI.sequenceHint.get(i))+
                    ", "+ConversationUtils.translateAbv(Squire.DAI.sequenceConvo.get(i))+"\n"
        }
        Log.v("SquireConvo",s)
        s = "Eirlys\n"
        for (i in 0..Squire.EIRLYS.sequenceHint.length-1){
            s= s + ConversationUtils.translateAbv(Squire.EIRLYS.sequenceHint.get(i)) +
                    ", "+ConversationUtils.translateAbv(Squire.EIRLYS.sequenceConvo.get(i))+"\n"
        }
        Log.v("SquireConvo",s)
        s = "Elsie\n"
        for (i in 0..Squire.ELSIE.sequenceConvo.length-1){
            s= s +ConversationUtils.translateAbv(Squire.ELSIE.sequenceConvo.get(i))+"\n"
        }
        Log.v("SquireConvo",s)
        s = "Kaour\n"
        for (i in 0..Squire.KAOUR.sequenceConvo.length-1){
            s= s +ConversationUtils.translateAbv(Squire.KAOUR.sequenceConvo.get(i))+"\n"
        }
        Log.v("SquireConvo",s)
    }
}
