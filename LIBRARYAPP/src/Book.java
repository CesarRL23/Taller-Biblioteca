public class Book {
    private String title;
    private String author;
    private int id = 0;
    private int publicationYear = 0;
    private int numberOfPages = 0;
    private int lend = 0;

    public Book(int id, String title, String author, int publicationYear, int numberOfpage) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.numberOfPages = numberOfpage;
    }

    public Book() {

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String newAuthor) {

        author = newAuthor;
    }

    public void setTitle(String newTitle) {

        title = newTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setPublicationYear(int newPublicationYear) {
        publicationYear = newPublicationYear;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setNumberOfPages(int newNumberOfPages) {

        numberOfPages = newNumberOfPages;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setId(int newId) {

        id = newId;
    }

    public int getId() {
        return id;
    }

    public void setBookLend(int newBookLend) {

        lend = newBookLend;
    }

    public int getBookLend() {
        return lend;
    }
}