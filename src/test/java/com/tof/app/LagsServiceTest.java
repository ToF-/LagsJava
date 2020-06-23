package com.tof.app;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class LagsServiceTest {

    private ByteArrayOutputStream stream;
    private LagsService lagsService;

    @Before
    public void setup() {
        stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        lagsService = new LagsService();
    }
}