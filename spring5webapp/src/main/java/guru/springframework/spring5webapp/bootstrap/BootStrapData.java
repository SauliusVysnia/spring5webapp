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

        System.out.println("Started in Bootsrap");

        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("Vilnius");
        publisher.setState("LT");

        publisherRepository.save(publisher);

        System.out.println("Publicher Count: " + publisherRepository.count());

        Author saulius = new Author("Saulius", "Vysniauskas");
        Book ddd = new Book("Pasakos vaikams", "123123");
        saulius.getBooks().add(ddd);
        ddd.getAuthors().add(saulius);

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(saulius);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);


        Author paulius = new Author("Paulius", "Paulauskas");
        Book noEJB = new Book("Matematika", "324531");
        paulius.getBooks().add(noEJB);
        noEJB.getAuthors().add(paulius);

        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepository.save(paulius);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);


        System.out.println("Numbers of Books: " + bookRepository.count());
        System.out.println("Publisher Number of Books: " + publisher.getBooks().size());

    }
}
