package pl.kamil.library.books

import javax.persistence.Entity
import javax.persistence.*
import javax.persistence.Table
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@Entity
@Table(name = "BOOKS")
data class Book(
        @Id @GeneratedValue val id: Long = 0,
        @Column @NotEmpty @NotNull val title: String = "",
        @Column @NotEmpty @NotNull val author: String = "",
        @Column @NotNull @Positive val releaseYear: Int = 1,
        @Column @NotNull val available: Boolean = true
) {
    fun toDTO() = BookDTO(title, author, releaseYear)
}

data class BookDTO(
        val title: String,
        val author: String,
        val releaseYear: Int
)
