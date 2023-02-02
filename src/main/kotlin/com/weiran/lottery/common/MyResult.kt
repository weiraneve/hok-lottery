package com.weiran.lottery.common

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.Date

data class MyResult(
    var teamId: Int? = null,
    var data: String = "请您再核对密钥后，再抽取英雄组合",
    @JsonFormat(pattern="yyyy年MM月dd日 HH时mm分ss秒", timezone = "GMT+8")
    var time: Date = Date(),
    var logs: List<LogResponse>? = null
)
