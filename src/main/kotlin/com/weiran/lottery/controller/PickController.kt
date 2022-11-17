package com.weiran.lottery.controller

import com.weiran.lottery.common.MyResult
import com.weiran.lottery.common.PostParam
import com.weiran.lottery.service.PickService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
class PickController {

    @Autowired
    private lateinit var pickService: PickService

    @PostMapping
    fun postToPickHeroes(@RequestBody param: PostParam): MyResult {
        return pickService.pick(param)
    }

}
