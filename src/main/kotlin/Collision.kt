package org.subwave.kolishun

data class Collision<E, F>(val first: Ensemble<E>, val second: Ensemble<F>, val impacts: List<Vec2>)