package com.example.demo.service;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


@Component
public class ParserUtils {

    private final String filePath = "C:\\Users\\dhaka\\Downloads\\lineParserAppThot\\demo\\src\\main\\resources\\inputData.txt";

    private int numGroups = 0;

    private final double Pi = 3.14;
    private final double Psi = 6.84845;
    private final double Zeta = 4.26;


    public String convertToThreePrecision(double num){
        return String.format("%.3f", num);
    }

    public String getFromSecondLine(String text) {
        String[] allTextSplitByLine = text.split("\n");
        //remove first line and add rest
        StringBuilder textAfterFirstLine = new StringBuilder();
        for (int i = 1; i < allTextSplitByLine.length; i++) {
            textAfterFirstLine.append(allTextSplitByLine[i]);
            if (i < allTextSplitByLine.length - 1) {
                textAfterFirstLine.append("\n");
            }
        }

        return textAfterFirstLine.toString();

    }

    public ArrayList<String> getGroups(String allText) {
        String[] allTextSplitByLine = allText.split("\n");
        numGroups = Integer.parseInt(allTextSplitByLine[0].strip()); // number of groups mentioned in the file

        String textAfterFirstLine = getFromSecondLine(allText);

        //form groups separated by * and remove empty groups
        String[] splitByStar = textAfterFirstLine.split("\\*");

        ArrayList<String> cleanList = new ArrayList<String>();

        for (String s : splitByStar) {
            if (s.strip().length() == 0) {
                continue;
            }
            cleanList.add(s);
        }

        //sanity check
        assert cleanList.size() == numGroups;

        return cleanList;
    }

    public ArrayList<ArrayList<Object>> getRowsAndColsData(String groupText) {
        String[] firstLine = groupText.split("\n")[0].split(" ");
        int numRows = Integer.parseInt(firstLine[0].strip());
        int numCols = Integer.parseInt(firstLine[1].strip());

        String textAfterFirstLine = getFromSecondLine(groupText);

        ArrayList<ArrayList<Object>> rowWiseData = new ArrayList<ArrayList<Object>>();

        for (String rowStr : textAfterFirstLine.strip().split("\n")) {
            ArrayList<Object> colWiseData = new ArrayList<Object>();
            for (String colStr : rowStr.strip().split(" ")) {
                try {
                    colWiseData.add(Integer.parseInt(colStr));
                } catch (NumberFormatException ne) {
                    colWiseData.add(colStr.strip());
                }
            }
            if (colWiseData.size() != numCols) {
                return null;
            }
            rowWiseData.add(colWiseData);
        }


        if (rowWiseData.size() != numRows) {
            return null;
        }

        return rowWiseData;
    }


    public String processForTwoColGroup(ArrayList<ArrayList<Object>> groupData) {
        String result = "";
        for (ArrayList<Object> rowData : groupData) {

            // get int array
            ArrayList<Integer> intArray = new ArrayList<Integer>();
            for (Object obj : rowData) {
                if (obj instanceof Number) {
                    intArray.add(((Number) obj).intValue());

                } else {
                    result += "Parsing error\n";
                    return result;
                }
            }
            int firstNum = intArray.get(0);
            int secondNum = intArray.get(1);
            double res = Pi * Pi * firstNum + Psi * (2.44 + Math.pow(secondNum, 3 / 2)) * 3;
            result += "AVG = " + convertToThreePrecision((firstNum + secondNum) / 2.0) + "\n";
            result += "FORM = " + convertToThreePrecision(res) + "\n";
        }
        result += "Record reads = " + groupData.size() + "\n";
        return result;
    }


    public String processForThreeColGroup(ArrayList<ArrayList<Object>> groupData) {
        String result = "";
        for (ArrayList<Object> rowData : groupData) {

            // get int array
            ArrayList<Double> doubleArray = new ArrayList<Double>();
            for (Object obj : rowData) {
                if (obj instanceof Number) {
                    doubleArray.add(((Number) obj).doubleValue());

                } else if (obj.toString().equals("a")) {//Could use a hashmap too
                    doubleArray.add(3.21);
                } else if (obj.toString().equals("b")) {
                    doubleArray.add(4.1);
                } else if (obj.toString().equals("c")) {
                    doubleArray.add(6.8);
                } else {
                    doubleArray.add(3.21);
                }
            }
            Double firstNum = doubleArray.get(0);
            Double secondNum = doubleArray.get(1);
            Double thirdNum = doubleArray.get(2);
            double res = Pi * Pi * (firstNum) * Pi + Psi * (2.44 + Math.pow(secondNum, 3 / 2)) * 3 + ((Math.pow(Zeta, thirdNum)) / (secondNum)) * Math.log(thirdNum);
            result += "AVG33 =" + convertToThreePrecision((firstNum + secondNum + thirdNum) / 3.00) + "\n";
            result += "FORM33 =" + convertToThreePrecision(res) + "\n";
        }
        result += "Record reads = " + groupData.size() + "\n";
        return result;
    }

    public String readFromDummyFile() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            return content;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
