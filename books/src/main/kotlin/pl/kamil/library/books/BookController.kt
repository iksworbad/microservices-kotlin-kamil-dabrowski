package pl.kamil.library.books

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/books")
class BookController(private val bookService: BookService) {
    @GetMapping("/")
    fun getAvailableBooks() = bookService.getAvailableBooks()
    @GetMapping("/{bookId}")
    fun getBookInfo(@PathVariable bookId: Long) = bookService.getBookInfo(bookId)
    @GetMapping("/is-available/{bookId}")
    fun isBookAvaiable(@PathVariable bookId: Long) = bookService.isBookAvaiable(bookId)
    @PostMapping("/new")
    fun addBook(@RequestBody newBook: BookDTO) = bookService.addBook(newBook.title, newBook.author, newBook.releaseYear)
    @PutMapping("/available/{bookId}")
    fun markBookAsAvailable(@PathVariable bookId: Long)= bookService.markBookAvailable(bookId)
    @PutMapping("/unavailable/{bookId}")
    fun markBookAsUnvailable(@PathVariable bookId: Long) = bookService.markBookUnavailable(bookId)

}

