package com.rahul.titan.reacoinux.Screens

import android.util.Log
import android.util.Size
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rahul.titan.reacoinux.Models.CryptoCurrency
import com.rahul.titan.reacoinux.ui.theme.green
import java.lang.reflect.Modifier

@Composable
fun Gainersscreen(navController: NavHostController, cryptolist: List<CryptoCurrency> ) {
    val topGainerList = cryptolist.sortedByDescending {
        it.quotes.first().percentChange24h
    }
    Box(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .background(
                Color.Black
            )
            .padding(20.dp)
    ) {

        Column(
            modifier = androidx.compose.ui.Modifier
        ) {

        Text(text = "Top Gainer in 24Hrs",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = green ,modifier = androidx.compose.ui.Modifier.padding(start = 20.dp , top = 20.dp))

        Spacer(modifier = androidx.compose.ui.Modifier.height(10.dp))

        //lazyColumn
        LazyColumn(
            modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(20.dp)

        ) {
            Log.d("titanji", "in column ")
            items(topGainerList) { item ->
                itemCoin(
                    name = item.name,
                    price = String.format("%.2f", item.quotes.first().price),
                    changeIn24 = item.quotes.first().percentChange24h.toInt(),
                    id = item.id
                )

            }


        }
    }

    }

}