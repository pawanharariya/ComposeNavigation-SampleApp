package com.psh.learn.cupcake

import androidx.navigation.NavController
import org.junit.Assert.assertEquals

/**
 * Extension function on NavController to verify the screen route
 */
fun NavController.assertCurrentRouteName(expectedRouteName: String) {
    assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}