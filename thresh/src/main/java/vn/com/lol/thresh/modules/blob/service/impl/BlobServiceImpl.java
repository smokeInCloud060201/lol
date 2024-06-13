package vn.com.lol.thresh.modules.blob.service.impl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import vn.com.lol.thresh.commons.utils.StringUtil;
import vn.com.lol.thresh.modules.blob.dtos.request.RegisterBlobRequest;
import vn.com.lol.thresh.modules.blob.dtos.response.BlobDTO;
import vn.com.lol.thresh.modules.blob.service.BlobService;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

@Service
public class BlobServiceImpl implements BlobService, CommandLineRunner {
    public BlobDTO registerBlob(RegisterBlobRequest request) throws NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("RSA");
        return null;
    }

    @Override
    public void run(String... args) throws Exception {
        String ramdomString = StringUtil.generateRandomString(32);
        System.out.println(ramdomString);
    }
}
