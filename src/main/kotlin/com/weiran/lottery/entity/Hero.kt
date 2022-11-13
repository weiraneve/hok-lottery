package com.weiran.lottery.entity

import javax.persistence.*

@Entity
@Table(name = "hero")
class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Column(name = "name")
    var name: String = ""

    @Column(name = "line")
    var line: Int? = null

    @Column(name = "is_pick")
    var isPick: Boolean = false

}
