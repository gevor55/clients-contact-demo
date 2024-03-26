package com.gevorg.clientscontact.service.client;

import com.gevorg.clientscontact.advice.NotFoundException;
import com.gevorg.clientscontact.dto.ClientDto;
import com.gevorg.clientscontact.dto.ContactDto;
import com.gevorg.clientscontact.entity.Client;
import com.gevorg.clientscontact.entity.Contact;
import com.gevorg.clientscontact.mapper.ClientMapper;
import com.gevorg.clientscontact.mapper.ContactMapper;
import com.gevorg.clientscontact.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ContactMapper contactMapper;
    private final ClientMapper clientMapper;

    @Override
    public ClientDto createClient(ClientDto dto) {
        log.info("Starting create client with command : {}.", dto);

        Client client = clientRepository.save(clientMapper.dtoToEntity(dto));

        log.info("Client successfully created");

        return clientMapper.entityToDto(client);
    }

    @Override
    public void addContactToClient(Long clientId, ContactDto contactDto) {
        log.info("Adding contact {} to client with id {}", contactDto, clientId);

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("Client with id " + clientId + " not found"));

        Contact contact = contactMapper.dtoToEntity(contactDto);

        contact.setClient(client);

        client.getContacts().add(contact);

        log.info("Contact added successfully to client: {}", client);

        clientRepository.save(client);
    }

    @Override
    public List<ClientDto> findAll() {
        log.info("Search all clients");
        return clientRepository.findAll().stream()
                .map(clientMapper::entityToDto)
                .toList();
    }

    @Override
    public Optional<ClientDto> findById(Long id) {
        log.info("Search client with id: {}.", id);

        return Optional.ofNullable(clientRepository.findById(id)
                .map(clientMapper::entityToDto)
                .orElseThrow(() -> new NotFoundException("Client with id: " + id + " not found")));
    }

    public List<ContactDto> findContactsOfClient(Long id) {

        log.info("Searching contacts of client with id: {}", id);

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client with id " + id + " not found"));

        return client.getContacts().stream()
                .map(contactMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContactDto> findContactsOfTypeOfClient(Long clientId, String contactType) {
        log.info("Searching  contacts of type {} for client with id {}", contactType, clientId);

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("Client with id " + clientId + " not found"));

        boolean contactExists = client.getContacts().stream()
                .anyMatch(contact -> contact.getType().toString().equals(contactType));

        if (!contactExists) {
            throw new NotFoundException("Contact of type " + contactType + " not found for client with id " + clientId);
        }

        return client.getContacts().stream()
                .filter(contact -> contact.getType().toString().equals(contactType))
                .map(contactMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
