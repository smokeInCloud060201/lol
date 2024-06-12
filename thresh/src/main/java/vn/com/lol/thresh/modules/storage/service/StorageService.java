package vn.com.lol.thresh.modules.storage.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

public interface StorageService {
    String store(MultipartFile file) throws Exception;
    Resource load(String filename) throws Exception;
}
