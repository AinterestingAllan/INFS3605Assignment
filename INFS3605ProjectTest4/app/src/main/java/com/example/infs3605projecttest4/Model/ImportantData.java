package com.example.infs3605projecttest4.Model;

public class ImportantData {
    private static TestType currExeType = null;

    public static TestType getCurrExeType() {
        return currExeType;
    }

    public static void setCurrExeType(TestType currExeType) {
        ImportantData.currExeType = currExeType;
    }
}
