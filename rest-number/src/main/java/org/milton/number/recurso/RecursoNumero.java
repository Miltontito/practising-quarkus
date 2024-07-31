package org.milton.number.recurso;

import com.github.javafaker.Faker;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.Instant;

@ApplicationScoped
public class RecursoNumero {

    @ConfigProperty(name = "number.separator", defaultValue = "false")
    boolean separator;

    public BookNumbersPOJO generateBookNumbers(){
        Faker faker = new Faker();

        BookNumbersPOJO bookNumbers = new BookNumbersPOJO();
        bookNumbers.setIsbn10(faker.code().isbn10(separator));
        bookNumbers.setIsbn13(faker.code().isbn13(separator));
        bookNumbers.setAsin(faker.code().asin());
        bookNumbers.setEan8(faker.code().ean8());
        bookNumbers.setEan13(faker.code().ean13());
        bookNumbers.setGenerationDate(Instant.now());

        return bookNumbers;
    }
}
