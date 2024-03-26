package com.gevorg.clientscontact.controller;

import com.gevorg.clientscontact.dto.ClientDto;
import com.gevorg.clientscontact.dto.ContactDto;
import com.gevorg.clientscontact.service.client.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping()
    public ResponseEntity<ClientDto> create( @RequestBody ClientDto clientDto) {
        ClientDto createdClient = clientService.createClient(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    @PostMapping("/{clientId}/contacts")
    public ResponseEntity<?> addContactToClient(@PathVariable Long clientId, @RequestBody ContactDto contactDto) {
        clientService.addContactToClient(clientId, contactDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<List<ClientDto>> findAll() {
        List<ClientDto> clients = clientService.findAll();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Optional<ClientDto>> findById(@PathVariable Long clientId) {
        Optional<ClientDto> client = clientService.findById(clientId);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/{clientId}/contacts")
    public ResponseEntity<List<ContactDto>> findContactsOfClient(@PathVariable Long clientId) {
        List<ContactDto> contacts = clientService.findContactsOfClient(clientId);
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{clientId}/contacts/{contactType}")
    public ResponseEntity<List<ContactDto>> findContactsOfTypeOfClient(@PathVariable Long clientId, @PathVariable String contactType) {
        List<ContactDto> contacts = clientService.findContactsOfTypeOfClient(clientId, contactType);
        return ResponseEntity.ok(contacts);
    }
}
