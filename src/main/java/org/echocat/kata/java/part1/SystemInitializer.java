package org.echocat.kata.java.part1;

import lombok.extern.java.Log;
import org.echocat.kata.java.part1.entity.Author;
import org.echocat.kata.java.part1.entity.Book;
import org.echocat.kata.java.part1.repository.AuthorRepository;
import org.echocat.kata.java.part1.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

@Service
@Log
public class SystemInitializer implements ApplicationRunner {
    public static final String CSV_DELIMITER = ";";

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("console runner");
        String authorCSV = "org/echocat/kata/java/part1/data/authors.csv";
        loadAuthors(authorCSV);
        String bookCSV = "org/echocat/kata/java/part1/data/books.csv";
        loadBooks(bookCSV);
    }

    private void loadBooks(String bookCSV) throws IOException {
        Resource resource = new ClassPathResource(bookCSV);

        String content = new String(Files.readAllBytes(resource.getFile().toPath()));
        System.out.println("Book data File Found : " + resource.getFile().exists());

        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(CSV_DELIMITER);
                Book book = new Book();
                book.setTitle(values[0]);
                book.setIsbn(values[1]);
                String[] authors = values[2].split(",");
                book.setDescription(values[3]);
                Set<Author> authorSet = new HashSet<>();
                for (String author :
                        authors) {
                    authorRepository.findById(author).map(authorEntity -> authorSet.add(authorEntity));
                }
                book.setAuthors(authorSet);
                bookRepository.save(book);

            }
        }
        log.info(content);
    }

    private void loadAuthors(String authorCSV) throws IOException {
        Resource resource = new ClassPathResource(authorCSV);
        System.out.println("Author File Found : " + resource.getFile().exists());
        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(CSV_DELIMITER);
                Author author = new Author();
                author.setEmail(values[0]);
                author.setFirstName(values[1]);
                author.setLastName(values[2]);
                authorRepository.save(author);
            }
        }
    }
}
