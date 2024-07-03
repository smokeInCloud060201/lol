package vn.com.lol.nautilus.modules.firstdb.clients.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import vn.com.lol.common.dto.response.BaseResponse;
import vn.com.lol.common.mapper.BaseResponseMapper;
import vn.com.lol.nautilus.modules.firstdb.clients.dto.request.UpdateClientRequest;
import vn.com.lol.nautilus.modules.firstdb.clients.dto.response.ClientResponse;
import vn.com.lol.nautilus.modules.firstdb.clients.services.ClientService;

import static vn.com.lol.common.constants.ControllerPathConstant.API_V1_PREFIX_BASE_PATH;
import static vn.com.lol.nautilus.commons.constant.ControllerPathConstant.CLIENTS_BASE;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1_PREFIX_BASE_PATH + CLIENTS_BASE)
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public BaseResponse<Page<ClientResponse>> searchClient() {
        return BaseResponseMapper.of(clientService.getAllClient());
    }

    @GetMapping("/{id}")
    public BaseResponse<ClientResponse> searchClientById(@PathVariable("id") String id) {
        Long clientID = Long.valueOf(id);
        return BaseResponseMapper.of(clientService.getClientById(clientID));
    }

    @PutMapping("/{id}")
    public BaseResponse<ClientResponse> updateClient(@PathVariable("id") String id, @RequestBody UpdateClientRequest request) {
        Long clientID = Long.valueOf(id);
        return BaseResponseMapper.of(clientService.updateClient(clientID, request));
    }


    @DeleteMapping("/{id}")
    public BaseResponse<Object> removeClient(@PathVariable("id") String id) {
        Long clientID = Long.valueOf(id);
        clientService.removeClient(clientID);
        return BaseResponseMapper.of(null);
    }

}
