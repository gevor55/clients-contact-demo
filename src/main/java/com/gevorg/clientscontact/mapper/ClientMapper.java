package com.gevorg.clientscontact.mapper;

import com.gevorg.clientscontact.dto.ClientDto;
import com.gevorg.clientscontact.entity.Client;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ClientMapper {

    public Client dtoToEntity(ClientDto dto) {
        return Client.builder()
                .name(dto.getName())
                .build();
    }

    public ClientDto entityToDto(Client client) {
        return ClientDto.builder()
                .name(client.getName())
                .build();
    }
}
