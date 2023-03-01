package com.example.aqa.endpoints;

import com.example.aqa.models.CustomObject;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class ExampleEndpoint {

    static String lastStr;

    @GetMapping(path = "/getString")
    public String getStringWithoutSpaces(@RequestParam(defaultValue = "empty string") String str) {
        lastStr = str;
        return str.trim();
    }

    @GetMapping(path = "/getInteger")
    public String getString(@RequestParam(defaultValue = "1") Integer str) {
        return str.toString();
    }

    @GetMapping(path = "/getLastStr")
    public String getLastStr() {
//        if (lastStr != null) {
//            return lastStr;
//        } else {
//            return "Строка не была передана";
//        } это то же самое, что и далее
        return Objects.requireNonNullElse(lastStr, "Строка не была передана");
    }

    @DeleteMapping(path = "/deleteLastStr")
    public void deleteLastStr() {
        lastStr = null;
    }

    @GetMapping(path = "/getJson")
    public JSONObject getJson() {
        JSONObject newJsonObject = new JSONObject();
        newJsonObject.put("query", "куртка");
        newJsonObject.put("page", "0");
        newJsonObject.put("size", "10");

        return newJsonObject;
    }

    @GetMapping(path = "/getObject")
    public CustomObject getObject() {
        return new CustomObject("куртка", 0, 10);
    }
}
