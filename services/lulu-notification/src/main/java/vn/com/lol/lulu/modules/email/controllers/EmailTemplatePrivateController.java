package vn.com.lol.lulu.modules.email.controllers;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.com.lol.common.constants.ControllerPathConstant;
import vn.com.lol.common.controller.BaseController;
import vn.com.lol.common.dto.request.SearchDTO;
import vn.com.lol.common.dto.response.BaseResponse;
import vn.com.lol.common.mapper.BaseResponseMapper;
import vn.com.lol.common.mapper.SearchMapper;
import vn.com.lol.common.utils.JsonUtil;
import vn.com.lol.common.validations.CreateValidation;
import vn.com.lol.lulu.modules.email.dtos.request.UpsertEmailTemplateRequest;
import vn.com.lol.lulu.modules.email.dtos.response.EmailTemplateResponse;
import vn.com.lol.lulu.modules.email.dtos.response.MockResponseDTO;
import vn.com.lol.lulu.modules.email.services.EmailTemplateService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerPathConstant.API_V1_PREFIX_BASE_PATH + "/email-templates")
public class EmailTemplatePrivateController extends BaseController {

    private final EmailTemplateService emailTemplateService;

    @PostMapping
    public BaseResponse<EmailTemplateResponse> createEmailTemplate(@Validated(CreateValidation.class) @RequestBody UpsertEmailTemplateRequest request) {
        return BaseResponseMapper.of(emailTemplateService.createEmailTemplate(request));
    }

    @PutMapping("/{id}")
    public BaseResponse<EmailTemplateResponse> updateEmailTemplate(@Valid @RequestBody UpsertEmailTemplateRequest request, @PathVariable("id") Long id) {
        return BaseResponseMapper.of(emailTemplateService.updateEmailTemplate(request, id));
    }

    @GetMapping("/search")
    public BaseResponse<List<EmailTemplateResponse>> getAllEmailTemplate(@RequestParam(name = "page", defaultValue = "0") int pageIndex,
                                                                         @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                                                         @RequestParam(value = "search_key", defaultValue = "") String searchKey,
                                                                         @RequestParam(value = "sorts", defaultValue = "") String sorts,
                                                                         @RequestParam(value = "filters", defaultValue = "") String filters) {
        SearchDTO searchDTO = SearchMapper.mappingFromRequestParamsToSearchDTO(pageIndex, pageSize, searchKey, sorts, filters);
        return BaseResponseMapper.ofPageable(emailTemplateService.getAll(searchDTO));
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteEmailTemplate(@PathVariable("id") Long id) {
        emailTemplateService.deleteEmailTemplate(id);
        return BaseResponseMapper.of(null);
    }

    @GetMapping
    public BaseResponse<List<MockResponseDTO>> mockResponse(HttpServletResponse response) throws IOException {
        List<MockResponseDTO> sampleDataList = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            MockResponseDTO sampleDTO = new MockResponseDTO();
            sampleDTO.setField1("Field1 Value: A long string with detailed data " + i);
            sampleDTO.setField2("Field2 Value: Another long string with more detailed data " + i);
            sampleDTO.setField3("Field3 Value: This is yet another string containing some detailed information " + i);
            sampleDTO.setField4("Field4 Value: Detailed data for testing purposes " + i);
            sampleDTO.setField5("Field5 Value: Example of a detailed data field " + i);
            sampleDTO.setField6("Field6 Value: More comprehensive data for the field " + i);
            sampleDTO.setField7("Field7 Value: Including different types of strings and lengths " + i);
            sampleDTO.setField8("Field8 Value: Testing field with more verbose content " + i);
            sampleDTO.setField9("Field9 Value: String data that is more extensive " + i);
            sampleDTO.setField10("Field10 Value: Another example of a detailed string " + i);
            sampleDTO.setField11("Field11 Value: Adding more detailed and varied data " + i);
            sampleDTO.setField12("Field12 Value: String field containing comprehensive data " + i);
            sampleDTO.setField13("Field13 Value: Example string with more details " + i);
            sampleDTO.setField14("Field14 Value: Detailed information for field " + i);
            sampleDTO.setField15("Field15 Value: More data added to this field for testing " + i);
            sampleDTO.setField16("Field16 Value: Detailed string for testing purposes " + i);
            sampleDTO.setField17("Field17 Value: Comprehensive data field example " + i);
            sampleDTO.setField18("Field18 Value: Including more details in the string data " + i);
            sampleDTO.setField19("Field19 Value: Another detailed string example " + i);
            sampleDTO.setField20("Field20 Value: Field with extensive and detailed string data " + i);

            sampleDataList.add(sampleDTO);
        }
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        response.setHeader(HttpHeaders.CONTENT_ENCODING, "gzip");
        return BaseResponseMapper.of(sampleDataList);
    }


}
