package org.subwave.kolishun

data class Vec2(var x: Double, var y: Double){
    fun norm() = Math.sqrt(x * x + y * y)
}
