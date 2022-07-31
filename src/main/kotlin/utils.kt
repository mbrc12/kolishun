package org.subwave.kolishun

// For a hashmap of lists, add the item to the list corresponding to id, creating it if necessary
fun <E, V> HashMap<E, ArrayList<V>>.addToList(id: E, item: V) {
    if (!this.containsKey(id)) {
        this[id] = arrayListOf()
    }

    this[id]!!.add(item)
}