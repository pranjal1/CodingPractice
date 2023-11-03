package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ParserService {

    @Autowired
    ParserUtils parserUtils;

    public String parseFile(Optional<String> fileText) {
        String result = "";

        String allText = fileText.isEmpty() ? parserUtils.readFromDummyFile() : fileText.get();
        ArrayList<String> groupTexts = parserUtils.getGroups(allText);

        for (String gt : groupTexts) {
            result += gt;
            result += "\n";
            ArrayList<ArrayList<Object>> groupwiseData = parserUtils.getRowsAndColsData(gt);
            if (groupwiseData == null) {
                result += "error\n";
            } else if (groupwiseData.get(0).size() == 2) {
                result += parserUtils.processForTwoColGroup(groupwiseData);
            } else if (groupwiseData.get(0).size() == 3) {
                result += parserUtils.processForThreeColGroup(groupwiseData);
            }
            result += "------------\n";
        }
        return result;
    }

}
