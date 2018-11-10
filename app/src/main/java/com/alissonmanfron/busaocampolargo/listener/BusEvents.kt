package com.alissonmanfron.busaocampolargo.listener

import com.alissonmanfron.busaocampolargo.model.Linha

data class FavoriteAddEvent(val linha: Linha)

data class FavoriteRemoveEvent(val linha: Linha)