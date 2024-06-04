package vn.com.lol.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * This enum modifier for File extension
 *
 * @author Ngoc Khanh
 */
public enum FileExtension {

    PDF,
    DOCX,
    DOC,
    XLS,
    XLSM,
    XLSX,
    CSV,
    PNG,
    JPEG,
    JPG;

    public static boolean isImageTypeExtension(String fileNameExtension) {
        return Stream.of(PNG, JPEG, JPG)
                .anyMatch(imageExtension -> imageExtension.name()
                        .equalsIgnoreCase(fileNameExtension));
    }
}
