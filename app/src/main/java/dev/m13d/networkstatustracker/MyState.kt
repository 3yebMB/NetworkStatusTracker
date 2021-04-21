package dev.m13d.networkstatustracker

sealed class MyState {
    object Fetched : MyState()
    object Error : MyState()
}