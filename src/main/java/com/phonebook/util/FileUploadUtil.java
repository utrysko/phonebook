package com.phonebook.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;

/**
 * Util class to save photo.
 *
 * @author Vasyl Utrysko
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUploadUtil {

    /**
     * Method to save photo.
     *
     * @param uploadDir dir where file will be uploaded
     * @param fileName name of the file
     * @param multipartFile file itself
     */
    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
}
