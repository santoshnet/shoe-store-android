package com.frontendsource.shoestore.sealed

sealed class Orientation {
    object Vertical : Orientation()
    object Horizontal : Orientation()
}