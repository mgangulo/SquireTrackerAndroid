package com.mabinogifanmade.squiretracker

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.RadioGroup
import com.mabinogifanmade.squiretracker.adapters.MiniSquireAdapter
import com.mabinogifanmade.squiretracker.squiredata.Squire
import androidx.recyclerview.widget.GridLayoutManager
import com.mabinogifanmade.squiretracker.userdata.Character
import com.mabinogifanmade.squiretracker.userdata.UserGeneral
import com.mabinogifanmade.squiretracker.utils.ShrdPrfsUtils
import kotlinx.android.synthetic.main.content_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val squireList: ArrayList<Squire> = arrayListOf(Squire.DAI, Squire.EIRLYS, Squire.ELSIE, Squire.KAOUR)
    var user:UserGeneral? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        val radioList: RadioGroup = findViewById(R.id.listOption)
        radioList.setOnCheckedChangeListener { group, checkedId ->
            setRecyclerViewType(
                when (checkedId) {
                    R.id.gridRadioOption -> true
                    else -> false
                }
            )
        }

        toggleDai?.setOnCheckedChangeListener(getSquireToggleListener(Squire.DAI))
        toggleEirlys?.setOnCheckedChangeListener(getSquireToggleListener(Squire.EIRLYS))
        toggleElsie?.setOnCheckedChangeListener(getSquireToggleListener(Squire.ELSIE))
        toggleKaour?.setOnCheckedChangeListener(getSquireToggleListener(Squire.KAOUR))
    }

    fun setUserDataOnView() {
        setDummyData()
        val user: UserGeneral? = ShrdPrfsUtils.getUserData(this)
        if (user != null) {
            squireRecyclerView?.adapter = MiniSquireAdapter(squireList, this, false,
                user.getCurrentCharacter().squireProgress)

            val currentChar: Character = user.characters.get(0)
            val gridRadio: RadioButton = findViewById(R.id.gridRadioOption)
            gridRadio.isChecked = user.prefersGrid
            val listRadio: RadioButton = findViewById(R.id.listRadioOption)
            listRadio.isChecked = !user.prefersGrid
            for (i in (squireList.size - 1) downTo 0) {
                val squire: Squire = squireList.get(i)
                if (!currentChar.squiresActive.contains(squire.id)) {
                    squireList.remove(squire)
                    if (squire.equals(Squire.DAI)) {
                        toggleDai?.isChecked = false
                    }
                    if (squire.equals(Squire.EIRLYS)) {
                        toggleEirlys?.isChecked = false
                    }
                    if (squire.equals(Squire.ELSIE)) {
                        toggleElsie?.isChecked = false
                    }
                    if (squire.equals(Squire.KAOUR)) {
                        toggleKaour?.isChecked = false
                    }
                }
            }
        }
    }

    fun setDummyData() {
        //if (!ShrdPrfsUtils.userDataExist(this)) {
            val user: UserGeneral = UserGeneral(Character("Alaguesia", "Alexina"))
            val currentChar: Character = user.characters.get(0)
            currentChar.squiresActive.remove(1)
            currentChar.squiresActive.remove(3)
            user.prefersGrid = true
            ShrdPrfsUtils.saveUserData(this, user)
        //}
    }

    override fun onStart() {
        super.onStart()
        setUserDataOnView()
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun getSquireToggleListener(squire: Squire): CompoundButton.OnCheckedChangeListener {
        return CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            val user: UserGeneral = ShrdPrfsUtils.getUserData(this)!!
            if (isChecked) {
                if (!squireList.contains(squire)) {
                    squireList.add(squire)
                    user.getCurrentCharacter().squiresActive.add(squire.id)
                    Collections.sort(squireList)
                    squireRecyclerView?.adapter?.notifyDataSetChanged()
                }
            } else {
                if (squireList.contains(squire)) {
                    squireList.remove(squire)
                    user.getCurrentCharacter().squiresActive.remove(squire.id)
                    squireRecyclerView?.adapter?.notifyDataSetChanged()
                }
            }
            ShrdPrfsUtils.saveUserData(buttonView.context,user)
        }
    }

    private fun setRecyclerViewType(isGrid: Boolean) {
        val layoutManager = squireRecyclerView?.layoutManager as GridLayoutManager
        layoutManager?.setSpanCount(
            when (isGrid) {
                true -> 2
                false -> 1
            }
        )
        (squireRecyclerView?.adapter as MiniSquireAdapter).setViewType(isGrid)
        val user:UserGeneral? = ShrdPrfsUtils.getUserData(this)
        user?.prefersGrid = isGrid
        ShrdPrfsUtils.saveUserData(this,user!!)
    }
}
