package pl.pas.mvc.DTO;

import lombok.Builder;

import java.util.UUID;

@Builder
public record RentNowForm (
        UUID bookId,
        String email
) {}
