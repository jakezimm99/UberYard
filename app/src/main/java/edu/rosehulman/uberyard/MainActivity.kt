package edu.rosehulman.uberyard

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import edu.rosehulman.uberyard.ui.billing.BillingFragment
import edu.rosehulman.uberyard.ui.home.HomeFragment
import edu.rosehulman.uberyard.ui.jobhistory.JobHistoryFragment
import edu.rosehulman.uberyard.ui.jobrequest.JobRequestFragment
import edu.rosehulman.uberyard.ui.jobstatuses.JobStatusesFragment
import edu.rosehulman.uberyard.ui.login.LoginFragment
import edu.rosehulman.uberyard.ui.settings.SettingsFragment

// Developed by Jake Zimmerman and Zach Thelen


class MainActivity : AppCompatActivity(), LoginFragment.OnLoginButtonPressedListener {

    private val RC_SIGN_IN = 1

    override fun onLoginButtonPressed() {
        launchLogin()
    }

    private fun launchLogin() {
        val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.PhoneBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build()
        )

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN
        )
    }

    val auth = FirebaseAuth.getInstance()
    lateinit var authListener: FirebaseAuth.AuthStateListener


    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

//        val fab: FloatingActionButton = findViewById(R.id.fab)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        var fragment: Fragment = LoginFragment()
        authListener = FirebaseAuth.AuthStateListener { auth: FirebaseAuth ->
            val user = auth.currentUser
            Log.d("Uber", "$user")
            if (user != null) {
                Log.d("Uber", "Main activity")
                fragment = HomeFragment()
            } else {
                Log.d("Uber", "Log in activity")
                fragment = LoginFragment()
            }
        }

        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_login -> {
                    fragment = LoginFragment()
                    val ft = supportFragmentManager.beginTransaction()
                    ft.replace(R.id.flContent, fragment)
                    ft.commit()

                    true
                }
                R.id.nav_home -> {
                    fragment = HomeFragment()
                    if (auth.currentUser != null) {
                        val ft = supportFragmentManager.beginTransaction()
                        ft.replace(R.id.flContent, fragment)
                        ft.commit()
                    }
                    true
                }
                R.id.nav_job_request -> {
                    fragment = JobRequestFragment()
                    if(auth.currentUser != null) {
                        val ft = supportFragmentManager.beginTransaction()
                        ft.replace(R.id.flContent, fragment)
                        ft.commit()
                    }
                    true
                }
                R.id.nav_job_history -> {
                    fragment = JobHistoryFragment()
                    if(auth.currentUser != null) {
                        val ft = supportFragmentManager.beginTransaction()
                        ft.replace(R.id.flContent, fragment)
                        ft.commit()
                    }
                    true
                }
                R.id.nav_job_statuses -> {
                    fragment = JobStatusesFragment()
                    if(auth.currentUser != null) {
                        val ft = supportFragmentManager.beginTransaction()
                        ft.replace(R.id.flContent, fragment)
                        ft.commit()
                    }
                    true
                }
                R.id.nav_settings -> {
                    fragment = SettingsFragment()
                    if(auth.currentUser != null) {
                        val ft = supportFragmentManager.beginTransaction()
                        ft.replace(R.id.flContent, fragment)
                        ft.commit()
                    }
                    true
                }
                R.id.nav_billing -> {
                    fragment = BillingFragment()
                    if(auth.currentUser != null) {
                        val ft = supportFragmentManager.beginTransaction()
                        ft.replace(R.id.flContent, fragment)
                        ft.commit()
                    }
                    true
                }
                else -> {
                    true
                }
            }
        }

//        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

//        appBarConfiguration = AppBarConfiguration(
//                setOf(
//                        R.id.nav_login, R.id.nav_home, R.id.nav_job_request, R.id.nav_job_history,
//                        R.id.nav_job_statuses, R.id.nav_settings, R.id.nav_billing
//                ), drawerLayout
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
        Log.d("Uber", "About to call the listener method")



    }

    override fun onStart() {
        super.onStart()
        auth.addAuthStateListener(authListener)
    }

    override fun onStop() {
        super.onStop()
        auth.removeAuthStateListener(authListener)
    }
    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        if (requestCode == 17) {
            if (permissions.size == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                val home = supportFragmentManager.findFragmentById(R.id.nav_home) as HomeFragment
                home.map!!.setMyLocationEnabled(true);
            } else {
                // Permission was denied. Display an error message.
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)


        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.logout -> {
                auth.signOut()
                true
            }
            R.id.nav_button -> {
                if (!drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.openDrawer(GravityCompat.START);
                else drawerLayout.closeDrawer(GravityCompat.END);
                true
            }


            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}

