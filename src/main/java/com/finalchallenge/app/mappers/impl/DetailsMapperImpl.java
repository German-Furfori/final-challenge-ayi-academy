package com.finalchallenge.app.mappers.impl;

import com.finalchallenge.app.dto.request.details.DetailsRequestDTO;
import com.finalchallenge.app.dto.response.details.DetailsResponseDTO;
import com.finalchallenge.app.mappers.IDetailsMapper;
import com.finalchallenge.app.entities.DetailsEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DetailsMapperImpl implements IDetailsMapper {

    private final ModelMapper modelMapper;

    @Override
    public DetailsEntity fromDtoToEntity(DetailsRequestDTO detailsRequestDTO) {
        DetailsEntity detailsEntity = new DetailsEntity();
        modelMapper.map(detailsRequestDTO, detailsEntity);
        return detailsEntity;
    }

    @Override
    public DetailsResponseDTO fromRequestToResponse(DetailsRequestDTO detailsRequestDTO) {
        DetailsResponseDTO detailsResponseDTO = new DetailsResponseDTO();
        modelMapper.map(detailsRequestDTO, detailsResponseDTO);
        return detailsResponseDTO;
    }

}
