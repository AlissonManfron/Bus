package com.alissonmanfron.busaocampolargo.listener

import com.alissonmanfron.busaocampolargo.persistence.LinhaObj

data class FavoriteAddEvent(val linha: LinhaObj)

data class FavoriteRemoveEvent(val linha: LinhaObj)