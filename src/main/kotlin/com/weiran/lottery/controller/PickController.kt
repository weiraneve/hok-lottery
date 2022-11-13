package com.weiran.lottery.controller

import com.weiran.lottery.service.PickService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class PickController {

    @Autowired
    private lateinit var pickService: PickService

    @GetMapping
    fun teamPickHeroes(@RequestParam(value = "code") encryptCode: String): String {
        return pickService.pick(encryptCode).data
    }

}
