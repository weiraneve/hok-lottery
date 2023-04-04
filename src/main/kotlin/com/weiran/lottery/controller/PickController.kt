package com.weiran.lottery.controller

import com.weiran.lottery.common.MyResult
import com.weiran.lottery.common.PostParam
import com.weiran.lottery.service.PickService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
class PickController(val pickService: PickService) {

    @PostMapping
    fun pickHeroes(@RequestBody param: PostParam): MyResult {
        return pickService.pick(param)
    }

}
