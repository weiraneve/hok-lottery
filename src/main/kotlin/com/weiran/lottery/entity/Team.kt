package com.weiran.lottery.entity

import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "team")
class Team(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(name = "encrypt_code")
    var encryptCode: String = "",

    @Column(name = "pick_content")
    var pickContent: String = "",

    @Column(name = "is_picked")
    var isPicked: Boolean = false,

    @Column(name = "update_time")
    var updateTime: Date = Date()
)
