package org.subwave.kolishun

internal data class EnsembleData<E>(val ensemble: Ensemble<E>, val callback: ((Collision<E>) -> Unit)?, val identifier: Int)
class World<E>(private val cellSize: Int) {


    private val bodies: HashMap<Int, EnsembleData<E>> = hashMapOf()
    private var bodyCount = 0

    fun add(ensemble: Ensemble<E>, callback: ((Collision<E>) -> Unit)?) {
        val bodyCount = bodyCount + 1
        bodies[bodyCount] = EnsembleData(ensemble, callback, bodyCount)
        this.bodyCount = bodyCount
    }

    fun detect(): List<Collision<E>> {
        // map from cell -> body and part of body intersecting this cell
        val activeCells: HashMap<IntVec2, ArrayList<Pair<EnsembleData<E>, Rect>>> = hashMapOf()
        for (body in bodies.values) {
            for (box in body.ensemble.boxes) {
                for (cell in box.cells(cellSize)) {
                    activeCells.addToList(cell, Pair(body, box))
                }
            }
        }

        // collisions between (p, q) which will be reported to p, so there is a symmetric copy for (q, p)
        val collisions: HashMap<Pair<Int, Int>, ArrayList<Rect>> = hashMapOf()

        for ((cell, pointedBodies) in activeCells.asIterable()) {
            val len = pointedBodies.size
            for (i in 0 until len) {
                val P = pointedBodies[i]
                for (j in (i+1) until len) {
                    val Q = pointedBodies[j]

                    // ignore collisions between the same ensemble
                    if (P.first.identifier == Q.first.identifier) continue

                    val result = overlap(P.second, Q.second)
                    if (result != null) {
                        collisions.addToList(Pair(P.first.identifier, Q.first.identifier), result)
                        collisions.addToList(Pair(Q.first.identifier, P.first.identifier), result)
                    }
                }
            }
        }

        return collisions.entries.map { (ids, rects) ->
            val p = ids.first
            val q = ids.second
            val specP = bodies[p]!!
            val specQ = bodies[q]!!
            val collision = Collision(specP.ensemble, specQ.ensemble, rects)

            // only do one side of the callback because the other side is handled symmetrically
            if (specP.callback != null) {
                specP.callback.invoke(collision)
            }

            collision
        }
    }
}