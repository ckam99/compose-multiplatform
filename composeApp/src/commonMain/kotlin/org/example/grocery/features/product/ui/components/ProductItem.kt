package org.example.grocery.features.product.ui.components


import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.kmpalette.color
import com.kmpalette.palette.graphics.Palette
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import org.example.grocery.core.ui.State
import org.example.grocery.features.product.domain.models.Product
import com.skydoves.landscapist.coil3.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.palette.PalettePlugin
import com.skydoves.landscapist.palette.rememberPaletteState
import org.example.grocery.features.product.domain.models.Rating
import org.jetbrains.compose.ui.tooling.preview.Preview



@Composable
fun ProductCard(
    modifier:  Modifier,
    product: Product,
    onClick: () -> Unit
) {
    val rounded = 10.dp
    var palette by rememberPaletteState(null)

    Box(
        modifier = modifier.clip(RoundedCornerShape(rounded))
            .clickable(enabled = true, onClick = onClick)
    ) {

        Box(modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(rounded))
            .background(color = Color(palette?.lightVibrantSwatch?.rgb ?: 0))
            .alpha(0.4f)
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Box(
                modifier = Modifier.fillMaxWidth().weight(1f)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize().padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Icon(Icons.Default.Favorite, contentDescription = "icon favorit", tint = MaterialTheme.colorScheme.primary)
                }
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    ProductImage(
                        modifier = Modifier.fillMaxSize(),
                        imageUrl = product.image,
                        onPaletteChange = {  palette = it }
                    )
                }
            }
            Row(
                modifier = Modifier.padding(top = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column(
                    modifier = Modifier.weight(1f).padding(start = 3.dp, bottom = 5.dp)
                ){
                    Text(product.title, fontSize = 12.sp, lineHeight = 0.sp, overflow = TextOverflow.Ellipsis, maxLines = 2)
                    Text("290 in stock",fontSize = 10.sp, lineHeight = 0.sp, modifier = Modifier.alpha(0.5f))
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart =rounded, bottomEnd =rounded))
                        .background(MaterialTheme.colorScheme.primary)
                ){
                    Column(
                        modifier = Modifier.padding(8.dp, 5.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text("$18.10", lineHeight = 0.sp, color = Color.White)
                        Text("$9.20", lineHeight = 0.sp, fontSize = 10.sp, color = Color.White, modifier = Modifier.alpha(0.5f))
                    }
                }
            }

        }
    }
}

@Preview
@Composable
fun ProductCardPreview(){
    for ( i in 0 until 10){
      ProductCard(
          modifier = Modifier.padding(10.dp).height(200.dp),
          product = Product(
              id = i.toLong(),
              title = "Item",
              description = "",
              category = "",
              price = (219*i).toDouble(),
              image = "https://torraslife.com/cdn/shop/files/6ca10b2eba177237d84fb183dcef0033_e08a4a57-ab6d-45c8-9cd3-272844df1cfd.png?v=1694693218",
              rating = Rating(0.0, 0)
          )
      ){}
    }
}

@Composable
fun ProductImage(
    modifier: Modifier,
    imageUrl: String,
    onPaletteChange: ((palette: Palette) -> Unit)? = null
){

//    var palette by rememberPaletteState(null)

//    Crossfade(
//        targetState = palette,
//        modifier = Modifier
//            .padding(horizontal = 8.dp)
//            .size(120.dp)
//    ) {
//        Box(
//            modifier = Modifier
//                .background(color = Color(it?.lightVibrantSwatch?.rgb ?: 0))
//                .fillMaxSize()
//        )
//    }

//    Box(
//        modifier = modifier
////                    .background( palette?.dominantSwatch?.color ?: Color.LightGray ),
////            .background( Color(palette?.dominantSwatch?.rgb ?: 0)),
//        .background(color = Color(palette?.lightVibrantSwatch?.rgb ?: 0)),
//        contentAlignment = Alignment.Center
//    ) {
        CoilImage(
            modifier = modifier,
            imageModel = {imageUrl},
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            ),
            component = rememberImageComponent {
                +PalettePlugin{
//                    palette = it
                    onPaletteChange?.apply { onPaletteChange(it) }
                }},
            loading = {
                Box(modifier = modifier,
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                }},
            failure = {
                Box(modifier = modifier.background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ){}
            }
        )
//    }
}