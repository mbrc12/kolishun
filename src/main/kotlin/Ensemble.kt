package org.subwave.kolishun

// Represents an ensemble of boxes together forming a body
class Ensemble<E>(var boxes: List<Rect>, val layer: Int, val mask: List<Int>, val userData: E) {

    fun cells(cellSize: Int): Iterable<IntVec2> = boxes.flatMap {it.cells(cellSize)}

    fun move(delta: Vec2) = boxes.forEach {it.move(delta)}

    override fun toString(): String {
        val boxStr = boxes.fold(StringBuilder()) {sb, it -> sb.append("$it, ")}
        return "[boxes: $boxStr data: ${userData.toString()}]"
    }
}