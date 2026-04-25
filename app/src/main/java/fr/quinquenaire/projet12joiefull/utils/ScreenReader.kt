package fr.quinquenaire.projet12joiefull.utils

import android.content.Context
import android.view.accessibility.AccessibilityManager

fun Context.isScreenReaderActive():Boolean{
    val accessibilityManager = getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
    return accessibilityManager.isEnabled && accessibilityManager.isTouchExplorationEnabled
}