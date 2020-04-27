package kr.or.ddit.basic;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

/**
 * JSON : Javascript Object Notation
 * JSON은 데이터를 저장하거나 교환하기 위한 문법
 * JSON은 자바스크립트 오브젝트 표현방식으로 작성한 문자열
 *
 * - JSON에서 value값으로 가능한 데이터 타입
 * 1. String
 * 2. number
 * 3. object(JSON Object)
 * 4. array
 * 5. boolean
 * 6. null
 *
 * XML => 모든 데이터를 태그로 감싸서 내가 보낼 데이터를 몇자 안되는데 태그를 감싸므로 네트워크에 부하를 줄 수 있다.
 * 이런 불합리한점을 개선한 것이 바로 JSON이다.
 */
public class JsonSimpleWriterTest {
    public static void main(String[] args) throws IOException {
        //JSON 데이터 생성
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("name","홍길동");
        jsonObj.put("job","학생");
        jsonObj.put("age",30);
        jsonObj.put("addr","대전시 중구");

        //JSONArray 데이터 생성
        JSONArray singerList = new JSONArray();

        JSONObject singer = new JSONObject();
        singer.put("name","싸이");
        singer.put("gender","남자");
        singer.put("age",40);
        singerList.add(singer);

        singer = new JSONObject();
        singer.put("name","비욘세");
        singer.put("gender","여자");
        singer.put("age",33);
        singerList.add(singer);

        singer = new JSONObject();
        singer.put("name","본조비");
        singer.put("gender","남자");
        singer.put("age",44);
        singerList.add(singer);

        jsonObj.put("singerList",singerList);

        FileWriter fw = new FileWriter("e:/D_Other/myJsonFile.txt");
        fw.write(jsonObj.toString());
        fw.flush();
        fw.close();

        System.out.println("JSON객체 내용 출력 : " + jsonObj);
    }
}
