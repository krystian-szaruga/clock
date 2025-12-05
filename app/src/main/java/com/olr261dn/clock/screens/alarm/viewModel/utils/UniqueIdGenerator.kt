package com.olr261dn.clock.screens.alarm.viewModel.utils

class UniqueIdGenerator(ids: List<Int>) {
    private val sortedIds = ids.sorted()

    fun getId(): Int {
        return when {
            sortedIds.isEmpty() -> 1
            sortedIds.size == 1 -> sortedIds[0] + 1
            sortedIds[0] != 1 -> 1
            else -> generateId()
        }
    }

    private fun generateId(): Int{
        sortedIds.zipWithNext { prev, current ->
            if (current - prev > 1) return prev + 1
        }
        return sortedIds.last() + 1
    }
}