package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WczytywanieJson {

    public List<File> loadJsonFiles(String directoryPath)
    {
        File directory = new File(directoryPath);
        List<File> jsonFiles = new ArrayList<>();

        if (directory.exists() && directory.isDirectory())
        {
            File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
            if (files != null)
            {
                for (File file : files)
                {
                    jsonFiles.add(file);
                }
            }
        } else
        {
            System.err.println("Katalog nie istnieje!: " + directoryPath);
        }

        return jsonFiles;
    }
}
