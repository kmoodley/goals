package com.goals.model.track;

import com.goals.model.converter.TreeRecordConverter;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
@Table(name = "tree")
@NoArgsConstructor
public class TreeRecord
{
    @Id
    private Long id;

    @NotNull
    @NotBlank
    private String letter;

    @NotNull
    @NotBlank
    @Column
    @Convert(converter = TreeRecordConverter.class)
    private TreeRecord path;

    public static final String SEPERATOR = "\\.";

    public TreeRecord(@NotBlank String letter, @NotBlank TreeRecord path)
    {
        this.letter = letter;
        this.path = path;
    }

    public static List<String> getTreeRecordData(TreeRecord treeRecord)
    {
        List<String> list = new ArrayList<>();
        list.add(treeRecord.getLetter());
        TreeRecord temp_record = treeRecord.getPath();

        while (temp_record != null)
        {
            list.add(temp_record.getLetter());
            temp_record = temp_record.getPath();
        }
        return list;
    }

    public static String recordDataToPathText(TreeRecord record)
    {
        List<String> letterList = TreeRecord.getTreeRecordData(record);
        StringBuilder sb = new StringBuilder();
        letterList.forEach(s -> sb.append(".").append(s));
        sb.delete(0,1); //Remove leading "."
        return sb.toString();
    }

    public static TreeRecord pathTextToRecordData(String pathText)
    {
        String[] letterPath = pathText.split(TreeRecord.SEPERATOR);

        List<TreeRecord> listTreeRecords = new ArrayList<>();

        Arrays.stream(letterPath)
                .forEach(l -> listTreeRecords.add(new TreeRecord(l, null)));

        int index = 0;
        for (int i = 0; i < listTreeRecords.size()-1; i++)
        {
            listTreeRecords.get(i).setPath(listTreeRecords.get(++index));
        }

        return listTreeRecords.get(0);
    }
}
