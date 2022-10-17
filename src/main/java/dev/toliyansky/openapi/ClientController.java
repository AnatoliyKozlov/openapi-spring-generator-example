package dev.toliyansky.openapi;

import dev.toliyansky.openapi.api.ClientApiController;
import dev.toliyansky.openapi.model.Client;
import dev.toliyansky.openapi.model.ClientResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ClientController extends ClientApiController {

    private final RandomExceptionService randomExceptionService;

    public ClientController(NativeWebRequest request, RandomExceptionService randomExceptionService) {
        super(request);
        this.randomExceptionService = randomExceptionService;
    }

    @Override
    public ResponseEntity<ClientResponse> clientGet(UUID id) {
        var clientResponse = new ClientResponse();
        try {
            var client = new Client(); // Stub. But in real project get client from service level.
            clientResponse.setSuccess(true);
            clientResponse.setClient(client);
            randomExceptionService.generateException50percentChance();
            return ResponseEntity.ok(clientResponse);
        } catch (Exception e) {
            e.printStackTrace();
            clientResponse.setSuccess(false);
            clientResponse.setMessage(e.getMessage());
            clientResponse.setClient(null);
            return ResponseEntity.internalServerError().body(clientResponse);
        }
    }

    @Override
    public ResponseEntity<ClientResponse> clientPost(Client client) {
        var clientResponse = new ClientResponse();
        try {
            // Do some actions with client in service level.
            client.id(UUID.randomUUID());
            clientResponse.setSuccess(true);
            clientResponse.setClient(client);
            randomExceptionService.generateException50percentChance();
            return ResponseEntity.ok(clientResponse);
        } catch (Exception e) {
            e.printStackTrace();
            clientResponse.setSuccess(false);
            clientResponse.setMessage(e.getMessage());
            clientResponse.setClient(null);
            return ResponseEntity.internalServerError().body(clientResponse);
        }
    }
}
