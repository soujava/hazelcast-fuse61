package com.hazelcast.cache;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.IMap;
import com.hazelcast.vo.WeatherVO;
import junit.framework.Assert;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.testng.CamelSpringTestSupport;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CacheITTest extends CamelSpringTestSupport {

    String file = "src/test/resources/mock/weather_ams.json";

    @Test
    public void shouldTestRouteBuilderAndCache() throws IOException {
        String inMessage = slurp(new File(file));
        Exchange exchange = new DefaultExchange(context);
        exchange.getIn().setBody(inMessage);

        Exchange response = template.send("direct-vm:testHZ", exchange);
        String body = response.getIn().getBody(String.class);

        assertStringContains(body, "testhz");
    }


    public static String slurp (final File file)
            throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            char[] buf = new char[1024];
            int r;
            while ((r = reader.read(buf)) != -1) {
                result.append(buf, 0, r);
            }
        }
        finally {
            if(reader != null)
                reader.close();
        }
        return result.toString();
    }


    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/camel-context-test.xml"});
    }
}