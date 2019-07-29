package com.mabinogifanmade.squiretracker

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.widget.RadioGroup
import androidx.recyclerview.widget.RecyclerView
import com.mabinogifanmade.squiretracker.adapters.MiniSquireAdapter
import com.mabinogifanmade.squiretracker.squiredata.Squire
import androidx.recyclerview.widget.GridLayoutManager
import me.rishabhkhanna.customtogglebutton.CustomToggleButton


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


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
        val squireList:ArrayList<Squire> = arrayListOf(Squire.DAI,Squire.EIRLYS,Squire.ELSIE,Squire.KAOUR)
        val recyclerView: RecyclerView = findViewById(R.id.squireRecyclerView)
        recyclerView.adapter = MiniSquireAdapter(squireList,this,true)
        val radioList: RadioGroup = findViewById(R.id.listOption)
        radioList.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.gridRadioOption){
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                layoutManager.setSpanCount(2)
                (recyclerView.adapter as MiniSquireAdapter).setViewType(true)
            } else {
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                layoutManager.setSpanCount(1)
                (recyclerView.adapter as MiniSquireAdapter).setViewType(false)
            }
        }
        val daiToggle: CustomToggleButton = findViewById(R.id.toggleDai)
        val eirlysToggle: CustomToggleButton = findViewById(R.id.toggleEirlys)
        val elsieToggle: CustomToggleButton = findViewById(R.id.toggleElsie)
        val kaourToggle: CustomToggleButton = findViewById(R.id.toggleKaour)
        daiToggle.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                if (!squireList.contains(Squire.DAI)){
                   squireList.add(Squire.DAI);
                   recyclerView?.adapter?.notifyDataSetChanged()
                }
            } else {
                if (squireList.contains(Squire.DAI)){
                    squireList.remove(Squire.DAI);
                    recyclerView?.adapter?.notifyDataSetChanged()
                }
            }
        }
        eirlysToggle.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                if (!squireList.contains(Squire.EIRLYS)){
                    squireList.add(Squire.EIRLYS);
                    recyclerView?.adapter?.notifyDataSetChanged()
                }
            } else {
                if (squireList.contains(Squire.EIRLYS)){
                    squireList.remove(Squire.EIRLYS);
                    recyclerView?.adapter?.notifyDataSetChanged()
                }
            }
        }
        elsieToggle.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                if (!squireList.contains(Squire.ELSIE)){
                    squireList.add(Squire.ELSIE)
                    recyclerView?.adapter?.notifyDataSetChanged()
                }
            } else {
                if (squireList.contains(Squire.ELSIE)){
                    squireList.remove(Squire.ELSIE);
                    recyclerView?.adapter?.notifyDataSetChanged()
                }
            }
        }
        kaourToggle.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                if (!squireList.contains(Squire.KAOUR)){
                    squireList.add(Squire.KAOUR);
                    recyclerView?.adapter?.notifyDataSetChanged()
                }
            } else {
                if (squireList.contains(Squire.KAOUR)){
                    squireList.remove(Squire.KAOUR);
                    recyclerView?.adapter?.notifyDataSetChanged()
                }
            }
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
}
