import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
   private final String FILE_PATH = "data/Books.csv";
   private Library library = new Library();
   private CSVCoder<Book> booksCoder = null;

   public Book CreateBook(int id, String title, String author, int publicationYear, int numberOfPages) {
      Book book = new Book();
      book.setId(id);
      book.setTitle(title);
      book.setAuthor(author);
      book.setPublicationYear(publicationYear);
      book.setNumberOfPages(numberOfPages);
      return book;
   }

   public Main() {

      booksCoder = new CSVCoder<>(';') {
         @Override
         public String[] encode(Book book) {
            return new String[] {
                  String.valueOf(book.getId()),
                  book.getTitle(),
                  book.getAuthor(),
                  String.valueOf(book.getPublicationYear()),
                  String.valueOf(book.getNumberOfPages())

            };
         };

         @Override
         public Book decode(String[] values) {
            var id = Integer.parseInt(values[0]);
            var title = values[1];
            var author = values[2];
            var publicationYear = Integer.parseInt(values[3]);
            var numberOfpage = Integer.parseInt(values[4]);

            return new Book(id, title, author, publicationYear, numberOfpage);
         };
      };
   }

   public void loadInfo() {
      List<Book> books = new ArrayList<>();
      try {
         booksCoder.readFromFile(FILE_PATH, books);

         library = new Library();

         for (Book book : books) {
            library.addBook(book);
         }
         Console.writeLine();
      } catch (IOException e) {
         Console.writeLine("ERROR AL CARGAR EL ARCHIVO");
      }
   }

   public void saveInfo() {
      try {
         booksCoder.writeToFile(FILE_PATH, library.getAllBooks());
      } catch (IOException e) {

         Console.writeLine("ERROR AL GUARDAR EL ARCHIVO");
      }
   }

   public void showMenu() {
      String option = "";

      do {
         Console.writeLine("----------------------------------------");
         Console.writeLine("                 MENU");
         Console.writeLine("----------------------------------------");
         Console.writeLine("porfavor primero carga la informacion   ");
         Console.writeLine("----------------------------------------");

         Console.writeLine("1. Agregar un libro ");
         Console.writeLine("2. Eliminar un libro ");
         Console.writeLine("3. Prestar un libro ");
         Console.writeLine("4. Devolver un libro ");
         Console.writeLine("5. Mostrar todos los libros disponibles ");
         Console.writeLine("6. Cargar información ");
         Console.writeLine("7. Guardar información ");
         Console.writeLine("0. Salir");
         Console.writeLine("----------------------------------------");
         Console.writeLine("INGRESE LA OPCION");
         Console.writeLine("----------------------------------------");

         option = Console.readLine();

         switch (option) {
            case "1": {
               Console.writeLine("Ingrese el titulo del libro: ");
               String title = Console.readLine();
               Console.writeLine("Ingrese el autor del libro: ");
               String author = Console.readLine();
               Console.writeLine("Ingrese el año de publicación del libro: ");
               int publicationYear = (Integer.parseInt(Console.readLine()));
               Console.writeLine("Ingrese la cantidad de paginas del libro: ");
               int numberOfPages = (Integer.parseInt(Console.readLine()));
               Console.writeLine("Ingrese el id del libro: ");
               int id = (Integer.parseInt(Console.readLine()));

               Book book = CreateBook(id, title, author, publicationYear, numberOfPages);
               library.addBook(book);
               library.uniquID(id);

               if (library.uniquID(id)) {
                  Console.writeLine("Se ingreso el libro");
               } else {
                  Console.writeLine("Error: Existe un libro con esta ID");
               }
               break;
            }
            case "2": {
               Console.writeLine("Ingrese el ID del libro que deseas eliminar");
               int bookId = Integer.parseInt(Console.readLine());
               library.removeBook(bookId);
               break;
            }
            case "3": {
               Console.writeLine("Ingrese el ID del libro que desea sacar prestado: ");
               int lendBookId = Integer.parseInt(Console.readLine());
               boolean bookLent = library.lendBook(lendBookId);
               if (bookLent) {
                  Console.writeLine("Libro prestado correctamente.");
               } else {
                  Console.writeLine("Error: Este libro ya está en préstamo.");
               }
               break;

            }
            case "4": {
               Console.writeLine("Ingrese el ID del libro que desea devolver: ");
               int returnBookId = Integer.parseInt(Console.readLine());
               if (library.returnBook(returnBookId)) {
                  Console.writeLine("Libro devuelto correctamente.");
               } else {
                  Console.writeLine("No se encontró ningún libro prestado con ese ID.");
               }

               break;
            }
            case "5":
               Console.writeLine("Libros disponibles en la biblioteca:");
               for (Book book : library.getAllBooks()) {
                  Console.writeLine(book.getTitle() + " de " + book.getAuthor() + " publicado en " + book.getPublicationYear());
                  Console.writeLine(book.getId());
               }
               break;

            case "6": {
               loadInfo();
            }
               break;

            case "7": {
               saveInfo();
            }
               break;

            default:
               if (!option.matches("[0-7]")) {

                  Console.writeLine(" Opción invalida, intentelo de nuevo");
                  Console.writeLine();
               }

         }

      } while (!option.equals("0"));
      Console.writeLine("salir del programa");

   }

   public static void main(String[] args) {
      new Main().showMenu();
      {

      }
   }
}