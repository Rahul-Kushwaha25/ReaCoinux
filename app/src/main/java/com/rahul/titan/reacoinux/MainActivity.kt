package com.rahul.titan.reacoinux

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rahul.titan.reacoinux.Models.CryptoCurrency
import com.rahul.titan.reacoinux.Retrofit.retrofit_instance
import com.rahul.titan.reacoinux.Screens.Gainersscreen
import com.rahul.titan.reacoinux.Screens.Loserscreen
import com.rahul.titan.reacoinux.Screens.Mainscreen
import com.rahul.titan.reacoinux.ui.theme.ReaCoinuxTheme
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReaCoinuxTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var cryptolist by remember {
                        //immutableListOf<CryptoCurrency>()
                        mutableStateOf(listOf<CryptoCurrency>())
                    }
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "Mainscreen") {
                        composable("Mainscreen") {
                            Mainscreen(navController, cryptolist)
                        }
                        composable("Gainersscreen") {
                            Gainersscreen(navController, cryptolist)
                        }
                        composable("Loserscreen") {
                            Loserscreen(navController, cryptolist)
                        }
                    }


                    val scope = rememberCoroutineScope()

                    LaunchedEffect(key1 = true) {
                        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
                            throwable.printStackTrace()
                        }
                        scope.launch(Dispatchers.IO + coroutineExceptionHandler) {

                            Log.d("shriram", "onCreate: calling")
                            val response = try {
                                retrofit_instance.api.getlisting()

                            } catch (e: IOException) {
                                Log.d("shriram", "onCreate: IO EXECPTION")
                                Toast.makeText(this@MainActivity, "error in IO", Toast.LENGTH_LONG)
                                    .show()
                                return@launch
                            } catch (e: HttpException) {
                                Log.d("shriram", "onCreate: HTTP EXECPTION")
                                Toast.makeText(
                                    this@MainActivity,
                                    "error in Http",
                                    Toast.LENGTH_LONG
                                ).show()
                                return@launch
                            }


                            if (response.isSuccessful && response.body() != null) {
                                withContext(Dispatchers.Main) {
                                    cryptolist = response.body()!!.data.cryptoCurrencyList
                                }
                                val name =
                                    response.body()!!.data.cryptoCurrencyList.firstOrNull { it.id == 1 }
                                val namemain = cryptolist.firstOrNull { it.id == 1 }
                                if (name != null) {
                                    Log.d("titanji", "api data: ${name}")
                                    Log.d("titanji", "api data second: ${namemain}")

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



