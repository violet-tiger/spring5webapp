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


        System.out.println("Started in Bootstrap");

        Publisher OwlBooks = new Publisher();
        OwlBooks.setPublisherName("OwlBooks");
        OwlBooks.setAddressLine1("1234 Cool St");
        OwlBooks.setCity("San Francisco");
        OwlBooks.setState("CA");
        OwlBooks.setZip("93106");
        publisherRepository.save(OwlBooks);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1232123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(OwlBooks);
        OwlBooks.getBooks().add(ddd);


        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(OwlBooks);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB","123124324" );
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        OwlBooks.getBooks().add(noEJB);


        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(OwlBooks);

        System.out.println("Number of Books:" + bookRepository.count());
        System.out.println("Number of Publishers:" + publisherRepository.count());

    }
}
