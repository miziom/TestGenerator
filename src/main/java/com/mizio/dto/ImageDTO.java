package com.mizio.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ImageDTO {

    private int imageID;
    private String imageName;
    private byte[] image;
}
