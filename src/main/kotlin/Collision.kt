package org.subwave.kolishun

data class Collision<E>(val first: Ensemble<E>, val second: Ensemble<E>, val overlap: List<Rect>)