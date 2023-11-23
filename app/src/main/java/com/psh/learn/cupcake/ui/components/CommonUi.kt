package com.psh.learn.cupcake.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.psh.learn.cupcake.R

/**
 * Composable that displays formatted [price]
 */
@Composable
fun PriceLabel(subtotal: String, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.subtotal_price, subtotal),
        modifier = modifier,
        style = MaterialTheme.typography.headlineSmall
    )
}