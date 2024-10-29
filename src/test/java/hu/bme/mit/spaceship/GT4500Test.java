package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.nio.file.*;
import java.util.stream.Collectors;

class GT4500Test {

    
    private GT4500 ship;
    private List<String> inputLines;
    private List<String> expectedOutputLines;
    private Iterator<Path> inputIterator;
    private Iterator<Path> outputIterator;

    @BeforeEach
    public void init() {
        this.ship = new GT4500();
        inputLines = new ArrayList<>();
        expectedOutputLines = new ArrayList<>();
        
        try {
            List<Path> inputFiles = Files.list(Paths.get("test-data"))
                    .filter(path -> path.getFileName().toString().startsWith("input") && path.getFileName().toString().endsWith(".txt"))
                    .sorted()
                    .collect(Collectors.toList());

            List<Path> outputFiles = Files.list(Paths.get("test-data"))
                    .filter(path -> path.getFileName().toString().startsWith("output") && path.getFileName().toString().endsWith(".txt"))
                    .sorted()
                    .collect(Collectors.toList());

            inputIterator = inputFiles.iterator();
            outputIterator = outputFiles.iterator();

            readNextFilePair();

        } catch (Exception e) {
            System.out.println("Error initializing test data files: " + e.getMessage());
        }
    }

    private void readNextFilePair() throws Exception {
        if (inputIterator.hasNext() && outputIterator.hasNext()) {
            Path inputFilePath = inputIterator.next();
            Path outputFilePath = outputIterator.next();

            inputLines = Files.readAllLines(inputFilePath);
            expectedOutputLines = Files.readAllLines(outputFilePath);
    
            for (String command : inputLines) {
                if (command.equals("fire SINGLE")) {
                    ship.fireTorpedo(FiringMode.SINGLE);
                } else if (command.equals("fire ALL")) {
                    ship.fireTorpedo(FiringMode.ALL);
                }
            }
        }
    }

    @Test
    void fireTorpedo_Single_Success() {
        // Arrange
        
       
        // Act
        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        // Assert
        assertEquals(true, result);
    }

    @Test
    void fireTorpedo_All_Success() {
        // Arrange
        
        
        // Act
        boolean result = ship.fireTorpedo(FiringMode.ALL);

        // Assert
        assertEquals(true, result);
    }
}
