package fr.raphaelmakaryan.cours_springboot.entity;

import lombok.Builder;
import org.springframework.data.annotation.Transient;

import java.time.LocalDateTime;

@Builder
public record ClientEntity(
        LocalDateTime timeStamp,
        String message,
        @Transient
        String errorAuthor,
        int httpStatus
) {
}