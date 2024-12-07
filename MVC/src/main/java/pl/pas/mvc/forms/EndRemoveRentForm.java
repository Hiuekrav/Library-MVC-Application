package pl.pas.mvc.forms;

import lombok.Builder;

import java.util.UUID;

@Builder
public record EndRemoveRentForm(
        UUID rentId
) {}
