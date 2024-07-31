package org.milton.book;

import io.quarkus.test.Mock;
import org.milton.book.client.IsbnNumbers;
import org.milton.book.client.NumberProxy;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.enterprise.context.ApplicationScoped;

@Mock
@ApplicationScoped
@RestClient
public class MockNumberProxy implements NumberProxy {

    @Override
    public IsbnNumbers generateIsbnNumbers() {
        IsbnNumbers isbnNumbers = new IsbnNumbers();
        isbnNumbers.setIsbn13(LibroResourceTest.MOCK_ISBN_13);
        isbnNumbers.setIsbn10(LibroResourceTest.MOCK_ISBN_10);
        return isbnNumbers;
    }
}
