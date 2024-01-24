package com.rahul.titan.reacoinux.Screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.rahul.titan.reacoinux.Models.CryptoCurrency
import com.rahul.titan.reacoinux.R
import com.rahul.titan.reacoinux.ui.theme.black200
import com.rahul.titan.reacoinux.ui.theme.green
import com.rahul.titan.reacoinux.ui.theme.red

@Composable
fun Mainscreen(
    navController: NavHostController,
    cryptolist: List<CryptoCurrency>, modifier: Modifier = Modifier
) {


    Box(
        modifier
            .fillMaxSize()
            .background(
                Color.Black
            )
    )

    {

        Column(
            modifier = modifier.padding(20.dp),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.logobanner),
                contentDescription = " ",
                modifier.padding(30.dp),
                alignment = Alignment.Center

            )
            Row(modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
                OutlinedButton(
                    onClick = {
                       navController.navigate("Gainersscreen")
                    },
                    border = BorderStroke(2.dp, green)
                ) {
                    Text(text = "Gainers", textAlign = TextAlign.Center, color = green)
                }
                OutlinedButton(
                    onClick = {
                              navController.navigate("Loserscreen")
                    },
                    border = BorderStroke(2.dp, red)
                ) {
                    Text(text = "Losers", textAlign = TextAlign.Center, color = red)
                }


            }
            Spacer(modifier = Modifier.height(20.dp))


            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(20.dp)

            ) {
                Log.d("titanji", "in column ")
                items(cryptolist) { item ->
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


@Composable
fun itemCoin(name: String, price: String, changeIn24: Int, id: Int, modifier: Modifier = Modifier) {


    Log.d("titanji", "itemCoin: inside item")



    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(black200),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween


        ) {
            AsyncImage(
                model = "https://s2.coinmarketcap.com/static/img/coins/64x64/" + id + ".png",
                contentDescription = "",
                modifier = Modifier
                    .height(50.dp)
                    .padding(start = 10.dp),
                contentScale = ContentScale.FillHeight,
                alignment = Alignment.Center
            )

            Text(
                text = name, fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Start,
                //modifier = Modifier.padding(start = 4.dp)
            )

            Text(
                text = price.toString(),
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                //modifier = Modifier.padding(start = 50.dp)

            )

            Text(
                text = changeIn24.toString() + "%",
                fontSize = 20.sp,
                color = if (changeIn24 < 0) red else green,
                textAlign = TextAlign.End,
                modifier = Modifier.padding(end = 10.dp)


            )


        }

    }
}


//items(cryptolist){ item->
//                    itemCoin(name = item.name,
//                        price = item.quotes.first().price,
//                        changeIn24 = item.quotes.first().percentChange24h,
//                        id = item.id)
//itemCoin(name = "paopa", price = 125.45, changeIn24 = 12.5, id = 1 )






