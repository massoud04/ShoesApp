package com.bytesthetic.shoesapp.screen

import android.widget.Toast
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.bytesthetic.shoesapp.model.ProductUiModel
import com.bytesthetic.shoesapp.theme.Accent
import com.bytesthetic.shoesapp.theme.Background
import com.bytesthetic.shoesapp.theme.Border
import com.bytesthetic.shoesapp.theme.DarkText
import com.bytesthetic.shoesapp.theme.Favorite
import com.bytesthetic.shoesapp.theme.IconTint
import com.bytesthetic.shoesapp.theme.LightText
import com.bytesthetic.shoesapp.theme.MediumText
import com.bytesthetic.shoesapp.theme.Primary
import com.bytesthetic.shoesapp.theme.RegularText
import com.bytesthetic.shoesapp.theme.Shadow
import com.bytesthetic.shoesapp.theme.ShoesAppTheme
import com.bytesthetic.shoesapp.theme.Star
import kotlinx.coroutines.delay
import coil.compose.rememberImagePainter
import com.bytesthetic.shoesapp.viewmodel.SharedViewModel



private const val SIZE_38 = "38"
private const val SIZE_39 = "39"
private const val SIZE_40 = "40"
private const val SIZE_41 = "41"

private const val DURATION = 600

@Composable
fun ProductDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: SharedViewModel,
    navController: NavController
) {
    val product by viewModel.selectedProduct.collectAsState()

    product?.let {
        ProductDetailsContent(
            modifier = modifier,
            product = it
        ) {
            navController.popBackStack()
        }
    }
}
@Composable
fun ProductDetailsContent(
    modifier: Modifier = Modifier,
    product: ProductUiModel,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    var quantities by remember { mutableStateOf(1) }
    var isFavorite by remember { mutableStateOf(false) }
    var selectedSize by remember { mutableStateOf(SIZE_38) }
    var selectedColor by remember { mutableStateOf(product.color) }
    var xOffset by remember { mutableStateOf(800.dp) }
    var yOffset by remember { mutableStateOf(800.dp) }
    var buttonScale by remember { mutableFloatStateOf(0f) }
    var iconScale by remember { mutableFloatStateOf(0f) }
    var sneakerScale by remember { mutableFloatStateOf(0.6f) }
    var sneakerRotate by remember { mutableFloatStateOf(-60f) }

    val animatedXOffset = animateDpAsState(
        targetValue = xOffset,
        label = "",
        animationSpec = tween(durationMillis = DURATION, easing = FastOutLinearInEasing)
    )
    val animatedYOffset = animateDpAsState(
        targetValue = yOffset,
        label = "",
        animationSpec = tween(durationMillis = DURATION, easing = FastOutLinearInEasing)
    )
    val animatedButtonScale = animateFloatAsState(
        targetValue = buttonScale,
        label = "",
        animationSpec = tween(easing = FastOutLinearInEasing)
    )
    val animatedIconScale = animateFloatAsState(
        targetValue = iconScale,
        label = "",
        animationSpec = tween(easing = FastOutLinearInEasing)
    )
    val animatedSneakerScale = animateFloatAsState(
        targetValue = sneakerScale,
        label = "",
        animationSpec = tween(durationMillis = DURATION, easing = FastOutLinearInEasing)
    )
    val animatedSneakerRotate = animateFloatAsState(
        targetValue = sneakerRotate,
        label = "",
        animationSpec = tween(durationMillis = DURATION, easing = FastOutLinearInEasing)
    )

    LaunchedEffect(true) {
        delay(150)
        xOffset = 140.dp
        yOffset = -130.dp
        sneakerScale = 1f
        sneakerRotate = -25f
        delay(400)
        iconScale = 1f
        delay(100)
        buttonScale = 1f
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Main content
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            IconButton(
                modifier = Modifier.padding(start = 22.dp, top = 42.dp)
                    .shadow(elevation = 24.dp, spotColor = Shadow, shape = RoundedCornerShape(12.dp))
                    .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                    .size(36.dp),
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "Back Icon",
                    tint = IconTint
                )
            }

            Image(
                modifier = Modifier
                    .scale(animatedSneakerScale.value)
                    .rotate(animatedSneakerRotate.value)
                    .padding(end = 48.dp)
                    .padding(top = 30.dp)
                    .size(280.dp),
                painter = rememberImagePainter(data = product.imageResource),
                contentDescription = "Product Image"
            )

            // Product details
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp)
                    .padding(top = 48.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Sneaker",
                        color = LightText,
                        fontSize =10.sp,
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(includeFontPadding = false)
                        )
                    )
                    Text(
                        modifier = Modifier.padding(top = 2.dp),
                        text = "${product.name}",
                        color = DarkText,
                        fontSize = 22.sp,
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(includeFontPadding = false)
                        )
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            imageVector = Icons.Outlined.Star,
                            contentDescription = "Rating Icon",
                            tint = Star
                        )
                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            textAlign = TextAlign.Center,
                            text = product.rating.toString(),
                            color = MediumText,
                            fontSize = 12.sp,
                            style = TextStyle(
                                platformStyle = PlatformTextStyle(includeFontPadding = false)
                            )
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = "$${product.price}",
                    color = Accent,
                    fontSize = 36.sp,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    )
                )
            }

            // Size selection
            Text(
                modifier = Modifier.padding(horizontal = 22.dp, vertical = 3.dp),
                text = "Size",
                color = MediumText,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp, horizontal = 22.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ProductSizeCard(
                    size = SIZE_38,
                    isSelected = selectedSize == SIZE_38
                ) {
                    selectedSize = SIZE_38
                }
                ProductSizeCard(
                    size = SIZE_39,
                    isSelected = selectedSize == SIZE_39
                ) {
                    selectedSize = SIZE_39
                }
                ProductSizeCard(
                    size = SIZE_40,
                    isSelected = selectedSize == SIZE_40
                ) {
                    selectedSize = SIZE_40
                }
                ProductSizeCard(
                    size = SIZE_41,
                    isSelected = selectedSize == SIZE_41
                ) {
                    selectedSize = SIZE_41
                }
            }

            // Color selection
            Text(
                modifier = Modifier.padding(horizontal = 22.dp, vertical = 6.dp),
                text = "Color",
                color = MediumText,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp, horizontal = 22.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                product.color?.let {
                    ProductColor(
                        color = it,
                        isSelected = selectedColor == product.color
                    ) {
                        selectedColor = product.color
                    }
                }
            }

            // Product description

                Text(
                    modifier = Modifier.padding(horizontal = 22.dp, vertical = 8.dp),
                    text = "Description",
                    color = MediumText,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    )
                )
            product.description?.let {
                Text(
                    modifier = Modifier.padding(horizontal = 22.dp, vertical = 8.dp),
                    text = it,
                    color = DarkText,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    )
                )
            }
        }

        // Bottom section
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Quantities:",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Button(colors = ButtonDefaults.buttonColors(
                    containerColor = Accent
                ),
                    onClick = { if (quantities > 1) quantities-- },
                    modifier = Modifier.size(24.dp),
                    contentPadding = ButtonDefaults.ContentPadding
                ) {
                    Text("-", style = MaterialTheme.typography.bodyMedium)
                }
                Text(
                    text = quantities.toString(),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Button(colors = ButtonDefaults.buttonColors(
                    containerColor = Accent
                ),
                    onClick = { quantities ++ },
                    modifier = Modifier.size(24.dp),
                    contentPadding = ButtonDefaults.ContentPadding
                ) {
                    Text("-", style = MaterialTheme.typography.bodyMedium)
                }
                IconButton(
                    modifier = Modifier
                        .scale(animatedIconScale.value),
                    onClick = { isFavorite = !isFavorite }
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                        contentDescription = "Favorite Icon",
                        tint = if (isFavorite) Favorite else IconTint
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(colors = ButtonDefaults.buttonColors(
                containerColor = Accent),
                onClick = {
                    Toast.makeText(
                        context,
                        "Bought for $${product.price?.times(quantities)}",
                        Toast.LENGTH_SHORT
                    ).show()
                    onBackClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Buy for $${product.price?.times(quantities)}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}




@Composable
fun ProductSizeCard(
    modifier: Modifier = Modifier,
    size: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) Accent else Color.White
    val textColor = if (isSelected) Color.White else RegularText
    val border = if (isSelected) 0.dp else 0.8.dp

    Text(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .border(width = border, color = Border, shape = RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .clickable(
                onClick = onClick
            )
            .padding(12.dp),
        text = size,
        fontSize = 12.sp,
        color = textColor,
        style = TextStyle(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        )
    )
}

@Composable
fun ProductColor(
    modifier: Modifier = Modifier,
    color: String, // Change this to String to hold the color code
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) Primary else Color.Transparent

    Box(
        modifier = modifier
            .border(width = 0.5.dp, color = borderColor, shape = CircleShape)
            .padding(3.dp)
            .background(
                Color(android.graphics.Color.parseColor(color)),
                shape = CircleShape
            ) // Parse the color code to a Color
            .size(12.dp)
            .clickable(
                onClick = onClick
            )
    )
}


@Preview
@Composable
fun ProductDetailsContentPreview() {
    ShoesAppTheme {
        ProductDetailsContent(
            product = ProductUiModel(
                imageResource = "https://firebasestorage.googleapis.com/v0/b/shoesapp-6697c.appspot.com/o/s_1.png?alt=media&token=98f45cf5-618c-4065-9a05-2563f47e6abd", // Use the URL of the image
                color = "#FF5722", // Use the color code
                name = "SA",
                price = 128.99f,
                oldPrice = 168.99f,
            ),
            onBackClick = {})
    }
}
