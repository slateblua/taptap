package com.slateblua.taptap.data.local.model

import comslatebluadb.TapEnt

fun Tap.toTapEnt(): TapEnt {
    return TapEnt(
        def = def,
        name = name,
        goal = goal,
        current = current,
    )
}

fun TapEnt.toTap(): Tap {
    return Tap(
        def = def,
        name = name,
        goal = goal,
        current = current,
    )
}
