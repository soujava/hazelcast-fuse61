package com.hazelcast.cache;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.IMap;
import com.hazelcast.vo.WeatherVO;
import junit.framework.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class CacheUnitTest {

    IMap<String, String> map = Hazelcast.newHazelcastInstance(null).getMap("testHZ");

    //http://api.openweathermap.org/data/2.5/weather?q=Amsterdam,uk&appid=2de143494c0b295cca9337e1e96b00e0
    String file = "src/test/resources/mock/weather_ams.json";

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldParseJSONWithOneLinerAndCache() throws IOException {

        WeatherVO vo = mapper.readValue((mapper.readTree(new File(file))).traverse(), new TypeReference<WeatherVO>() {});
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        System.out.println(vo);
        String json = mapper.writeValueAsString(vo);

        map.put(vo.getBase(), json);
        String str = map.get(vo.getBase());
        WeatherVO v = mapper.readValue((mapper.readTree(str)).traverse(), new TypeReference<WeatherVO>() {});

        Assert.assertNotNull(v.getBase());
        Assert.assertEquals(v.getBase(), vo.getBase());

        System.out.println(v.getBase());
    }

}