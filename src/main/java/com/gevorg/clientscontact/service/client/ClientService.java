package com.gevorg.clientscontact.service.client;
import com.gevorg.clientscontact.dto.ClientDto;
import com.gevorg.clientscontact.dto.ContactDto;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    ClientDto createClient(ClientDto clientDto);

    void addContactToClient(Long clientId, ContactDto contactDto);

    List<ClientDto> findAll();

    Optional<ClientDto> findById(Long id);

    List<ContactDto> findContactsOfClient(Long clientId);

    List<ContactDto> findContactsOfTypeOfClient(Long clientId, String contactType);
}
