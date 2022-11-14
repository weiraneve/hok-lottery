package com.weiran.lottery.controller

import com.weiran.lottery.common.MyResult
import com.weiran.lottery.service.PickService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PickController {

    @Autowired
    private lateinit var pickService: PickService

    @GetMapping
    fun teamPickHeroes(@RequestParam(value = "code") encryptCode: String): MyResult {
        return pickService.pick(encryptCode)
    }

}
