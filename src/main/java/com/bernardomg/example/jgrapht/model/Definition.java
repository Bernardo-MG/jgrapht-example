
package com.bernardomg.example.jgrapht.model;

import java.util.Collection;

public interface Definition {

    public String getName();

    public void setName(final String name);

    public Collection<String> getProperties();

    public void setProperties(final Collection<String> properties);

}
