package com.mizio.converter;

import javax.persistence.AttributeConverter;
import java.awt.*;

public class ColorConverter implements AttributeConverter<Color, String> {

    public static final String SEPARATOR = "_";

    /**
     * Convert Color object to a String
     * with format red|green|blue|alpha
     */
    @Override
    public String convertToDatabaseColumn(Color color) {
        return color.getRed()
                + SEPARATOR
                + color.getGreen()
                + SEPARATOR
                + color.getBlue();
    }

    /**
     * Convert a String with format red|green|blue|alpha
     * to a Color object
     */
    @Override
    public Color convertToEntityAttribute(String colorString) {
        String[] rgb = colorString.split(SEPARATOR);
        return new Color(
                Integer.parseInt(rgb[0]),
                Integer.parseInt(rgb[1]),
                Integer.parseInt(rgb[2])
        );
    }
}
