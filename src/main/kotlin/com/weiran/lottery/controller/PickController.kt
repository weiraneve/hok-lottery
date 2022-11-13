package com.weiran.lottery.controller

import com.weiran.lottery.common.MyResult
import com.weiran.lottery.service.PickService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class PickController(val pickService: PickService) {

    @GetMapping("/pick")
    fun teamPickHeroes(encryptCode: String): MyResult {
        return pickService.pick(encryptCode)
    }

}
