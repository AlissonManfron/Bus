package com.alissonmanfron.busaocampolargo.model

import java.util.ArrayList

class Linha(var cod: Int,
            var name: String,
            var isFavorite: Boolean,
            var terminal_dias_uteis: ArrayList<String>,
            var terminal_sabados: ArrayList<String>,
            var terminal_dom_feriados: ArrayList<String>,
            var bairro_dias_uteis: ArrayList<String>,
            var bairro_sabados: ArrayList<String>,
            var bairro_dom_feriados: ArrayList<String>)
