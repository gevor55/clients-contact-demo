package com.gevorg.clientscontact.controller;

import com.gevorg.clientscontact.dto.ContactDto;
import com.gevorg.clientscontact.service.contact.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping()
    public ResponseEntity<ContactDto> create(@RequestBody ContactDto contactDto) {
        contactService.create(contactDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
