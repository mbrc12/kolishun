package org.subwave.kolishun

import kotlin.math.floor

fun Double.toCellIdx(cellSize: Int): Int = floor(this / cellSize).toInt()