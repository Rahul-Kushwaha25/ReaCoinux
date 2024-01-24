package com.rahul.titan.reacoinux.Screens

import android.util.Log
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rahul.titan.reacoinux.Models.CryptoCurrency
import com.rahul.titan.reacoinux.ui.theme.green
import com.rahul.titan.reacoinux.ui.theme.red


@Composable
fun Loserscreen(navController: NavHostController, cryptolist: List<CryptoCurrency>) {

    val topLoserList = cryptolist.sortedBy{
        it.quotes.first().percentChange24h
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color.Black
            )
            .padding(20.dp)
    ) {

        Column(
            modifier = Modifier
        ) {

            Text(text = "Top Loser in 24Hrs",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = red ,modifier = Modifier.padding(start = 20.dp , top = 20.dp))

            Spacer(modifier = Modifier.height(10.dp))

            //lazyColumn
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(20.dp)

            ) {
                Log.d("titanji", "in column ")
                items(topLoserList) { item ->
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