package com.goals.model.converter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.postgresql.util.PGobject;


@Converter
public class TaskPathLTreeConverter implements AttributeConverter<String, String>
{
    private static final Logger LOG = LogManager.getLogger();

    @Override
    public String convertToDatabaseColumn(String attribute)
    {
        return attribute;
    }

    @Override
    public String convertToEntityAttribute(String pathText)
    {
        return pathText;
    }
}
