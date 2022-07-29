package org.subwave.kolishun

import kotlin.test.Test
import kotlin.test.assertEquals

class RectTests {

    @Test
    fun createRect() {
        Rect(Vec2(5.0, -6.7), Vec2(4.5, 6.7))
    }

    @Test
    fun cellCount() {
        assertEquals(12, Rect(Vec2(0.1, 0.1), Vec2(7.0, 4.9)).cells(2).toList().size)
    }

}