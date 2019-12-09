package com.example.kata.gossippingbusdriver

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is
import org.junit.Test

class GossipingBusDriverTest {

    @Test
    fun `example 1`() {
        val driverA = Driver("A", Route(listOf(3, 1, 2, 3)))
        val driverB = Driver("B", Route(listOf(3, 2, 3, 1)))
        val driverC = Driver("C", Route(listOf(4, 2, 3, 4, 5)))

        assertThat(Gossipper.find(driverA, driverB, driverC), Is.`is`(5))
    }

    @Test
    fun `example 2`() {
        val driverA = Driver("A", Route(listOf(2, 1, 2)))
        val driverB = Driver("B", Route(listOf(5, 2, 8)))

        assertThat(Gossipper.find(driverA, driverB), Is.`is`(-1))
    }
}
