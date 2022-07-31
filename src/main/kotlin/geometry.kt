package org.subwave.kolishun

class Segment(var left: Double, var right: Double) {
    init {
        assert(right >= left)
    }

    fun cells(cellSize: Int): Iterable<Int> {
        val current = left.toCellIdx(cellSize)
        val target = right.toCellIdx(cellSize)
        return (current .. target)
    }
}

class Rect(lowerLeft: Vec2, size: Vec2) {

    var lowerLeft = lowerLeft
        private set
    var size = size
        private set

    init {
        assert(size.x >= 0 && size.y >= 0)
    }

    fun move(delta: Vec2) {
        lowerLeft.x += delta.x
        lowerLeft.y += delta.y
    }

    fun moveTo(dest: Vec2) {
        lowerLeft = dest
    }

    fun cells(cellSize: Int): Iterable<IntVec2> {
        val ans = arrayListOf<IntVec2>()
        for (cx in Segment(lowerLeft.x, lowerLeft.x + size.x).cells(cellSize)) {
            for (cy in Segment(lowerLeft.y, lowerLeft.y + size.y).cells(cellSize)) {
                ans.add(IntVec2(cx, cy))
            }
        }
        return ans
    }
}

fun intersect(P: Rect, Q: Rect): Boolean {
    if (P.lowerLeft.x + P.size.x < Q.lowerLeft.x || Q.lowerLeft.x + Q.size.x < P.lowerLeft.x) {
        return false
    }
    if (P.lowerLeft.y + P.size.y < Q.lowerLeft.y || Q.lowerLeft.y + Q.size.y < P.lowerLeft.y) {
        return false
    }
    return true
}

fun overlap(P: Rect, Q: Rect): Rect? {
    if (!intersect(P, Q)) return null

    val (lx, rx) = Pair(Math.max(P.lowerLeft.x, Q.lowerLeft.x), Math.min(P.lowerLeft.x + P.size.x, Q.lowerLeft.x + Q.size.x))
    val (ly, ry) = Pair(Math.max(P.lowerLeft.y, Q.lowerLeft.y), Math.min(P.lowerLeft.y + P.size.y, Q.lowerLeft.y + Q.size.y))

    return Rect(Vec2(lx, ly), Vec2(rx - lx, ry - ly))
}