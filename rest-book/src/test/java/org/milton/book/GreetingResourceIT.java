package org.milton.book;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import org.milton.book.GreetingResourceTest;

@QuarkusIntegrationTest
class GreetingResourceIT extends GreetingResourceTest {
    // Execute the same tests but in packaged mode.
}
