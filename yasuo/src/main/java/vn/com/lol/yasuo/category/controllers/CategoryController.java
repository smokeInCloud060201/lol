package vn.com.lol.yasuo.category.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.com.lol.annotations.file.image.IsImage;
import vn.com.lol.controller.BaseController;

import static vn.com.lol.constants.ControllerPathConstant.API_V1_PREFIX_BASE_PATH;
import static vn.com.lol.yasuo.commons.constant.ControllerPathConstant.CATEGORY_BASE;

@RestController
@RequestMapping(API_V1_PREFIX_BASE_PATH + CATEGORY_BASE)
@RequiredArgsConstructor
public class CategoryController extends BaseController {

    @PostMapping
    public ResponseEntity<Object> checkConstant(@Validated @IsImage @RequestParam("file") MultipartFile file) {
            return null;
    }
}
