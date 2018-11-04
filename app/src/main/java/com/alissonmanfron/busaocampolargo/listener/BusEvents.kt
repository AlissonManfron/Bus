package com.alissonmanfron.busaocampolargo.listener

import com.alissonmanfron.busaocampolargo.persistence.linhas.LinhaObj

data class FavoriteAddEvent(val linha: LinhaObj)

data class FavoriteRemoveEvent(val linha: LinhaObj)