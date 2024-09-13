package org.example

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val year: Int
)
// начальный список книг
fun createBookList(): MutableList<Book> {
    return mutableListOf(
        Book(1, "Маленький принц", "Антуан де Сент-Экзюпери", 1943),
        Book(2, "Гордость и предубеждение", "Джейн Остин", 1813),
        Book(3, "Великий Гэтсби", "Скотт Фицджеральд", 1925),
        Book(4, "Гарри Поттер и философский камень", "Джоан Роулинг", 1997),
        Book(5, "1984", "Джордж Оруэлл", 1949)
    )
}
// добавление новой книги в список
fun addBook(books: MutableList<Book>) {
    println("Введите id книги:")
    val id = readLine()?.toIntOrNull() ?: return
    println("Введите название книги:")
    val title = readLine().orEmpty()
    println("Введите автора книги:")
    val author = readLine().orEmpty()
    println("Введите год выпуска книги:")
    val year = readLine()?.toIntOrNull() ?: return

    books.add(Book(id, title, author, year))
    println("Книга добавлена!")
}
// отображения всех книг в списке
fun showAllBooks(books: List<Book>) {
    if (books.isEmpty()) {
        println("Список книг пуст.")
    } else {
        println("Список книг:")
        // Перебираем каждую книгу и выводим в нужном формате
        books.forEach { book ->
            println("${book.title}, автор: ${book.author}, год издания: ${book.year}")
        }
    }
}
// фильтрация книг
fun filterBooksByYear(books: List<Book>, year: Int): List<Book> {
    return books.filter { it.year > year }
}
// сортировка книг по названию
fun sortBooksByTitle(books: List<Book>): List<Book> {
    return books.sortedBy { it.title }
}
// отображение меню действий
fun displayMenu() {
    println("\nВыберите действие, написав его номер:")
    println("1. Добавить книгу")
    println("2. Показать все книги")
    println("3. Фильтровать книги по году издания")
    println("4. Сортировать книги по названию")
    println("5. Выйти")
}

fun main() {

    val books = createBookList()

    while (true) {
        displayMenu() //вызов меню
        when (readLine()) {
            "1" -> addBook(books)
            "2" -> showAllBooks(books)
            "3" -> {
                println("Введите год для фильтрации: ")
                val year = readLine()?.toIntOrNull()
                if (year != null) {
                    val filteredBooks = filterBooksByYear(books, year)
                    println("Книги, изданные после $year:")
                    showAllBooks(filteredBooks)
                } else {
                    println("Неподходящий год, попробуйте еще раз")
                }
            }
            "4" -> {
                val sortedBooks = sortBooksByTitle(books)
                println("Книги, отсортированные по названию:")
                showAllBooks(sortedBooks)
            }
            "5" -> {
                println("Выход из программы.")
                return
            }
            else -> println("Неверный выбор. Выберите действие от 1 до 5")
        }
    }
}