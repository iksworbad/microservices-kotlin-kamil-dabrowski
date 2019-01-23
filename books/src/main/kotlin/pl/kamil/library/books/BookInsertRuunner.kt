package pl.kamil.library.books

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service

@Service
class BookInsertRuunner(private val bookRepository: BookRepository): CommandLineRunner{

    override fun run(vararg args: String?) {
        bookRepository.saveAll(listOf(
                Book(1, "The Hunger Games", "Suzanne Collins", 2008, true),
                Book(2, "Harry Potter and the Order of the Phoenix", "J.K. Rowling",
                        2003, true),
                Book(3, "To Kill a Mockingbird", "Harper Lee", 1960, true),
                Book(4, "Pride and Prejudice", "Jane Austen", 1813, true),
                Book(5, "Twilight", "Stephenie Meyer", 2005, true),
                Book(6, "The Book Thief", "Markus Zusak", 2005, true),
                Book(7, "The Chronicles of Narnia", "C.S. Lewis", 1950, true),
                Book(8, "Animal Farm", "George Orwell", 1945, false),
                Book(9, "Gone with the Wind", "Margaret Mitchell", 1936, false),
                Book(10, "The Fault in Our Stars", "John Green", 2012, true)
        ))
        println("Books in database ${bookRepository.count()}")
    }
}