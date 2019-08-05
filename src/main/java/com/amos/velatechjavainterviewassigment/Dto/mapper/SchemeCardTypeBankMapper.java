package com.amos.velatechjavainterviewassigment.Dto.mapper;

import com.amos.velatechjavainterviewassigment.Dto.GeneralResponse;
import com.amos.velatechjavainterviewassigment.Dto.SchemeCardTypeBank;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SchemeCardTypeBankMapper {
    SchemeCardTypeBankMapper CARD_VERIFICATION_MAPPER= Mappers.getMapper(SchemeCardTypeBankMapper.class);
    @Mapping(source = "bank.name", target ="bank")
    SchemeCardTypeBank schemeCardTypeBankFrom(GeneralResponse generalResponse);

}
