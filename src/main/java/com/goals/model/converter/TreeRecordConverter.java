package com.goals.model.converter;

import com.goals.model.track.TreeRecord;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TreeRecordConverter implements AttributeConverter<TreeRecord, String>
{
    @Override
    public String convertToDatabaseColumn(TreeRecord attribute)
    {
        return TreeRecord.recordDataToPathText(attribute);
    }

    @Override
    public TreeRecord convertToEntityAttribute(String pathText)
    {
        return TreeRecord.pathTextToRecordData(pathText);
    }
}
