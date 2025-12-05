package com.olr261dn.clock.utils.timeConverters.localDateTime

import java.time.LocalDateTime

abstract class BaseToStringConverter(val time: LocalDateTime){
    abstract fun convert(): String
}





