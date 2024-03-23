import java.util.ArrayList;
import java.util.List;

class Library {
    private List<Book> availableBooks;
    private List<Book> borrowBooks;

    public Library() {
        this.availableBooks = new ArrayList<>();
        this.borrowBooks = new ArrayList<>();
    }

    public boolean uniquID(int id) {
        for (Book existingBook : availableBooks) {
            if (existingBook.getId() == id) {
                return false;
            }
        }
        return true;
    }

    public void addBook(Book book) {
        if (uniquID(book.getId())) {
            availableBooks.add(book);
        }

    }

    public void removeBook(int id) {
        for (Book book : availableBooks) {
            if (book.getId() == id) {
                availableBooks.remove(book);
                borrowBooks.add(book);
                break;
            }
        }
    }

    public boolean lendBook(int id) {
        for (int i = 0; i < availableBooks.size(); i++) {
            if (availableBooks.get(i).getId() == id) {

                Book book = availableBooks.remove(i);
                borrowBooks.add(book);
                return true;
            }
        }
        return false;
    }

    public boolean returnBook(int id) {
        for (int i = 0; i < borrowBooks.size(); i++) {
            if (borrowBooks.get(i).getId() == id) {
                Book book = borrowBooks.remove(i);
                availableBooks.add(book);
                return true;
            }
        }
        return false;
    }

    public List<Book> getAllBooks() {
        return availableBooks;
    }

    public List<Book> getBorrowedBooks() {
        return borrowBooks;
    }
}