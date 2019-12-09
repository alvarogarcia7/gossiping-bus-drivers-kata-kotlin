package com.example.kata.gossippingbusdriver

import java.util.stream.IntStream.range

data class Route(val stops: List<Int>)

data class Driver(val name: String, val route: Route) {
    private val gossips: MutableSet<String> = mutableSetOf()
    private var position: Int = 0

    init {
        this.gossips.add(name)
    }

    fun driveToNext() {
        this.position = (position + 1) % this.route.stops.size
    }

    fun learnAbout(gossips: Set<String>) {
        this.gossips.addAll(gossips)
    }

    fun knowsAbout(): Set<String> {
        return this.gossips.toSet()
    }

    fun stoppedWhere(it: Driver): Boolean {
        return at() == it.at()
    }

    private fun at() = this.route.stops[this.position]
}

object Gossipper {
    fun find(vararg drivers: Driver): Int {
        for (i in range(0, 480)) {
            drivers.map { driver ->
                val atTheSameStation = drivers.filter { it -> it.stoppedWhere(driver) }
                val gossipsAtThisStep = atTheSameStation.flatMap { it.knowsAbout() }.toSet()
                driver.learnAbout(gossipsAtThisStep)
            }
            drivers.map {
                it.driveToNext()
            }
            if (allGossipsDistributedAmong(drivers)) {
                return i + 1
            }
        }
        return -1
    }

    private fun allGossipsDistributedAmong(drivers: Array<out Driver>): Boolean {
        return drivers.all { driver -> driver.knowsAbout().size == drivers.size }
    }

}
