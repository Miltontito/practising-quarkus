package org.milton.number;

import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/")
@OpenAPIDefinition(
        info = @Info(title = "Number API",
                description = "This API allows to generate all sorts of numbers",
                version = "1.0",
                contact = @Contact(name = "@agoncal", url = "https://twitter.com/agoncal")),
        externalDocs = @ExternalDocumentation(url = "https://github.com/agoncal/agoncal-" +
                "fascicle-quarkus-pract", description = "All the Practising Quarkus code"),
        tags = {
                @Tag(name = "api", description = "Public API that can be used by anybody"),
                @Tag(name = "numbers", description = "Anybody interested in numbers")
        }
)
public class NumberApplication extends Application {
}

