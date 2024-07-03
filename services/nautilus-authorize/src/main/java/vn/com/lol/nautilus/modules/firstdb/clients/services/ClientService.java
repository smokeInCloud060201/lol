package vn.com.lol.nautilus.modules.firstdb.clients.services;


import org.springframework.data.domain.Page;
import vn.com.lol.nautilus.modules.firstdb.clients.dto.request.UpdateClientRequest;
import vn.com.lol.nautilus.modules.firstdb.clients.dto.response.ClientResponse;

public interface ClientService {

    Page<ClientResponse> getAllClient();
    ClientResponse getClientById(Long clientId);

    ClientResponse updateClient(Long clientId, UpdateClientRequest request);
    void removeClient(Long clientId);
}
