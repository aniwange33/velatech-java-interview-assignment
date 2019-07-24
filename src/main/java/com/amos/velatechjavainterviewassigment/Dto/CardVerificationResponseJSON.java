package com.amos.velatechjavainterviewassigment.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardVerificationResponseJSON {
    private boolean success;
    private SchemeCardTypeBank payload;
}
