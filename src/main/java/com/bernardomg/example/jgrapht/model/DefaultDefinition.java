
package com.bernardomg.example.jgrapht.model;

import java.util.Collection;

import lombok.Data;

@Data
public final class DefaultDefinition implements Definition {

    private String             name;

    private Collection<String> properties;

}
