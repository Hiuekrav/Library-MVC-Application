package pl.pas.mvc.DTO;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record RentForm(
        UUID bookId,
        String email,
        LocalDateTime beginTime,
        LocalDateTime endTime
) {}
