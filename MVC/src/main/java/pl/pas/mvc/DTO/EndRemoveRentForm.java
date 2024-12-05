package pl.pas.mvc.DTO;

import lombok.Builder;

import java.util.UUID;

@Builder
public record EndRemoveRentForm(
        UUID rentId
) {}
