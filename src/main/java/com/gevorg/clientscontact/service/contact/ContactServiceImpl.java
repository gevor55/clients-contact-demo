package com.gevorg.clientscontact.service.contact;

import com.gevorg.clientscontact.dto.ContactDto;
import com.gevorg.clientscontact.entity.Contact;
import com.gevorg.clientscontact.mapper.ContactMapper;
import com.gevorg.clientscontact.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactServiceImpl implements ContactService{

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Override
    public ContactDto create(ContactDto contactDto) {
        log.info("Starting create contact with command : {}.", contactDto);

        Contact contact = contactRepository.save(contactMapper.dtoToEntity(contactDto));

        log.info("Contact successfully created");

        return contactMapper.entityToDto(contact);
    }

}
