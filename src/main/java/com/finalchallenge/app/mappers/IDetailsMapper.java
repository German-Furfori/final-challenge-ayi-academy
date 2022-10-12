package com.finalchallenge.app.mappers;

import com.finalchallenge.app.dto.request.details.DetailsRequestDTO;
import com.finalchallenge.app.dto.response.details.DetailsResponseDTO;
import com.finalchallenge.app.entities.DetailsEntity;

public interface IDetailsMapper {

    DetailsEntity fromDtoToEntity(DetailsRequestDTO detailsRequestDTO);

    DetailsResponseDTO fromRequestToResponse(DetailsRequestDTO detailsRequestDTO);
}
