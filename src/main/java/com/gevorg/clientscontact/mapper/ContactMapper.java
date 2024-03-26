package com.gevorg.clientscontact.mapper;

import com.gevorg.clientscontact.dto.ContactDto;
import com.gevorg.clientscontact.entity.Contact;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ContactMapper {

    public Contact dtoToEntity(ContactDto dto) {
        return Contact.builder()
                .contactValue(dto.getContactValue())
                .type(dto.getContactType())
                .build();
    }

    public ContactDto entityToDto(Contact contact) {
        return ContactDto.builder()
                .contactValue(contact.getContactValue())
                .contactType(contact.getType())
                .build();
    }
}
