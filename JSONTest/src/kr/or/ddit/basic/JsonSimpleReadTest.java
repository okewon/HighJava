package kr.or.ddit.basic;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class JsonSimpleReadTest {
    public static void main(String[] args) throws IOException, ParseException {
        FileReader fr = new FileReader("e:/D_Other/myJsonFile.txt");
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(fr);
        JSONObject jsonfile = (JSONObject) obj;

        String name = (String) jsonfile.get("name");
        String job = (String) jsonfile.get("job");
        long age = (long) jsonfile.get("age");
        String addr = (String) jsonfile.get("addr");

        System.out.println("name : " + name);
        System.out.println("job : " + job);
        System.out.println("age : " + age);
        System.out.println("addr : " + addr);

        //object타입은 json타입으로 형변환한다.
        JSONArray list = (JSONArray) jsonfile.get("singerList");

        Iterator<JSONObject> it = list.iterator();

        JSONObject singer = null;
        while (it.hasNext()) {
            singer = it.next();
            System.out.printf("이름 : %s, \t성별 : %s \t 나이 : %d\n", singer.get("name"), singer.get("gender"), singer.get("age"));


        }
    }
}
