package com.stock.capital.enterprise.api.regulatory.dto;

import java.io.Serializable;

public class LetterAndReturnInfoDto implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private LetterByteDto letterByteDto;

    private LetterReturnByteDto letterReturnByteDto;

    public LetterByteDto getLetterByteDto() {
        return letterByteDto;
    }

    public void setLetterByteDto(LetterByteDto letterByteDto) {
        this.letterByteDto = letterByteDto;
    }

    public LetterReturnByteDto getLetterReturnByteDto() {
        return letterReturnByteDto;
    }

    public void setLetterReturnByteDto(LetterReturnByteDto letterReturnByteDto) {
        this.letterReturnByteDto = letterReturnByteDto;
    }

}
