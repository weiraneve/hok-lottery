package com.weiran.lottery.common

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.Date

data class MyResult(
    var data: String = "",
    @JsonFormat(pattern="yyyy年MM月dd日 HH时mm分ss秒", timezone = "GMT+8")
    var time: Date = Date(),
)
