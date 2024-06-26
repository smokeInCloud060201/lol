package vn.com.lol.yasuo.product.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.lol.common.controller.BaseController;

import static vn.com.lol.common.constants.ControllerPathConstant.API_V1_PREFIX_BASE_PATH;
import static vn.com.lol.yasuo.commons.constant.ControllerPathConstant.PRODUCT_BASE;

@RestController
@RequestMapping(API_V1_PREFIX_BASE_PATH + PRODUCT_BASE)
@RequiredArgsConstructor
public class ProductController extends BaseController {
}
