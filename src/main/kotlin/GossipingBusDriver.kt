package com.example.kata.gossippingbusdriver

import java.util.stream.IntStream.range

data class Route(val stops: List<Int>)

data class Driver(val name: String, val route: Route, var position: Int = 0) {
    val gossips: MutableSet<String> = mutableSetOf()

    init {
        this.gossips.add(name)
    }

    fun moveOne() {
        this.position = (position + 1) % this.route.stops.size
    }

    fun isAt(i: Int): Boolean {
        return this.route.stops[position] == i
    }

    fun positionX(): Int {
        return this.route.stops[this.position]
    }
}

object Gossipper {
    fun find(vararg drivers: Driver): Int {
        for (i in range(0, 480)) {
            drivers.map { it ->
                val gossipsAtThisStep =
                    drivers.filter { driver -> driver.isAt(it.positionX()) }.flatMap { it.gossips }.toSet()
                it.gossips.addAll(gossipsAtThisStep)
            }
            drivers.map {
                it.moveOne()
            }
            if (finished(drivers)) {
                return i + 1
            }
        }
        return -1
    }

    private fun finished(drivers: Array<out Driver>): Boolean {
        val size = drivers.filter { driver -> driver.gossips.size == drivers.size }.size
        return size == drivers.size
    }

}
