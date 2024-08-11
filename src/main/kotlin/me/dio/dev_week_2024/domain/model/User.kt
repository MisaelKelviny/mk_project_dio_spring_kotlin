package me.dio.dev_week_2024.domain.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne

@Entity(name = "tb_user")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    var name: String = ""

    @OneToOne(cascade = [CascadeType.ALL])
    var account: Account = Account()

    @OneToOne(cascade = [CascadeType.ALL])
    var card: Card = Card()

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var features: List<Feature> = listOf<Feature>()

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var news: List<News> = listOf<News>()
}