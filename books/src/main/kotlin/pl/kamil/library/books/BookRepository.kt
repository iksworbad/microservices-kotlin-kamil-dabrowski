package pl.kamil.library.books

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BookRepository:JpaRepository<Book, Long>