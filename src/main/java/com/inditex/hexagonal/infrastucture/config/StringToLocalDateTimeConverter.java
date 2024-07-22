package com.inditex.hexagonal.infrastucture.config;

import com.inditex.hexagonal.rest.exception.InternalErrorException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(@NonNull String source) {

        try {
            if (source.isEmpty()) {
                return null;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return LocalDateTime.parse(source, formatter);
        } catch (Exception e) {
            throw new InternalErrorException(String.format("Error. reason: %s", e.getMessage()));
        }
    }
}
