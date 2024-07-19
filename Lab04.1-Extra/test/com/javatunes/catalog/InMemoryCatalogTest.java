package com.javatunes.catalog;

import org.junit.Before;
import static org.junit.Assert.*;

public class InMemoryCatalogTest {

    private InMemoryCatalog catalog;

    @Before
    public void setUp() {
        catalog = new InMemoryCatalog();
    }
}