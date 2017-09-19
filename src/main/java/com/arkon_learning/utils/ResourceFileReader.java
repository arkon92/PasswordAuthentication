package com.arkon_learning.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by arkon92 on 17/09/2017.
 */
public class ResourceFileReader {

    private Logger logger = LoggerFactory.getLogger(ResourceFileReader.class);

    private final String errorFilePath = "/static/error/400.html";
    private final String fileToReadPath;

    public ResourceFileReader(String fileToReadPath) {
        this.fileToReadPath = fileToReadPath;
    }

    /**
     * @return Return the content UTF-8 encoded of the file
     * @throws Exception
     */
    public String getFileContentAsString() {
        try {
            URL urlOfIndexFile = getClass().getResource(fileToReadPath);
            Path filePath = Paths.get(urlOfIndexFile.toURI());
            byte[] fileContentAsBytes = Files.readAllBytes(filePath);
            String fileContent = new String(fileContentAsBytes);
            return fileContent;
        } catch (Exception e) {
            logger.error("Unable to read file " + fileToReadPath, e);
            ResourceFileReader errorFileReader = new ResourceFileReader(errorFilePath);
            return errorFileReader.getFileContentAsString();
        }
    }


}
