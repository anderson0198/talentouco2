package com.example.talentouco2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.NonNull
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.talentouco2.adapter.TalentoAdapter
import com.example.talentouco2.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var client: GoogleSignInClient
    private val signInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            if (data != null) {
                handleGoogleSignInResult(data)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        //appBarConfiguration = AppBarConfiguration(navController.graph)
        //setupActionBarWithNavController(navController, appBarConfiguration)

        binding.LogGoogle.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val signInIntent = client.signInIntent
            signInLauncher.launch(signInIntent)
        }

        //Evento personalizado de google analytics
        val analytics:FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Integración de Firebase Completa")
        analytics.logEvent("InitScreen", bundle)

        //Auth con google
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("1089080056448-2hchbneietvtpaaup40lq5u3783732ro.apps.googleusercontent.com")
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(this, options)
        googleSignInClient.signOut().addOnCompleteListener {
             //Cierre de sesión completado
        }
        client = GoogleSignIn.getClient(this, options)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
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

    private fun handleGoogleSignInResult(data: Intent) {
        val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
            val credential: AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)
            FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // La autenticación fue exitosa
                        val intent = Intent(applicationContext, RecyclerActivity::class.java)
                        intent.putExtra("options", client.apiOptions)
                        startActivity(intent)
                        // Realiza acciones adicionales
                    } else {
                        // La autenticación fallo
                        Toast.makeText(applicationContext, task.exception?.message ?: "", Toast.LENGTH_SHORT).show()
                        // Manejar la excepción
                    }
                }
        } catch (e: ApiException) {
            e.printStackTrace()
        }
    }

}