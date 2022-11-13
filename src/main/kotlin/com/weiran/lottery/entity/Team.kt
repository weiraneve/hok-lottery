package com.weiran.lottery.entity

import javax.persistence.*

@Entity
@Table(name = "team")
class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Column(name = "encrypt_code")
    var encryptCode: String = ""

    @Column(name = "pick_count")
    var pickCount: Int = 0
}
