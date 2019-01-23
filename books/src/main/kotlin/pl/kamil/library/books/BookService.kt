package pl.kamil.library.books

import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException


@Service
class BookService(private val bookRepository: BookRepository) {

    private inline fun <T> handlerBookIfExist(id: Long, block: (Book) -> T): T = with(bookRepository.findById(id)){
        when {
            this.isPresent -> block(this.get())
            else -> throw NoSuchElementException("Book with this id $id does not exists")
        }
    }

    fun getAvailableBooks() = bookRepository.findAll().filter { it.available }

    fun getBookInfo(bookId: Long) = handlerBookIfExist(bookId){it.toDTO()}

    fun isBookAvaiable(bookId: Long) = handlerBookIfExist(bookId) { it.available }

    fun addBook(title: String, author: String, publicationYear: Int) = bookRepository.save(Book(0, title, author, publicationYear, true))

    private fun toggleBookStatus(book: Book) =
            bookRepository.save(book.copy(available = !book.available))

    fun markBookAvailable(bookId: Long) =handlerBookIfExist(bookId) {
        if(it.available) throw IllegalStateException("Book is already available")
        else toggleBookStatus(it)
    }
    fun markBookUnavailable(bookId: Long) =handlerBookIfExist(bookId) {
        if(!it.available) throw IllegalStateException("Book is already unavailable")
        else toggleBookStatus(it)
    }
}
