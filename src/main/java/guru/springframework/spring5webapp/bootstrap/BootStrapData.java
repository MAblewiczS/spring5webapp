package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain driven Design", "1231231");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        Publisher publisher = new Publisher("aaa", "city", "state", "11111" );
        System.out.println("Number of publishers before save: " + publisherRepository.count());
        publisherRepository.save(publisher);
        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEjb = new Book("J2EE development", "1211111");
        rod.getBooks().add(noEjb);
        noEjb.getAuthors().add(rod);
        noEjb.setPublisher(publisher);
        publisher.getBooks().add(noEjb);


        authorRepository.save(rod);
        bookRepository.save(noEjb);
        publisherRepository.save(publisher);
        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());




        System.out.println("Number of publishers after save: " + publisherRepository.count());
        System.out.println("Publisher Number of books " + publisher.getBooks().size() );



    }
}
