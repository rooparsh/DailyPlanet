package com.darklabs.businessdomain.util

interface Mapper<Storage, Remote> {
    fun Storage.toRemote(): Remote
    fun Remote.toStorage(): Storage
    fun List<Storage>.toRemote(): List<Remote> = this.map { it.toRemote() }
    fun List<Remote>.toStorage(): List<Storage> = this.map { it.toStorage() }
}