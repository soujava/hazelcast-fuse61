package com.hazelcast.routes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.vo.WeatherVO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class ApiRouteBuilder extends RouteBuilder {
    private HazelcastInstance hazelcastInstance;

    public final void configure() throws Exception {
        this.define();
    }

    public void define() throws Exception {

        from("direct:queue:startEndpoint")
        .to("direct-vm:testHZ")
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        System.out.println(exchange.getIn().getBody(String.class));
                        System.out.println("done");
                    }
                });

        from("direct-vm:testHZ")
                .process(new Processor() {

                    public void process(Exchange exchange) throws Exception {
                        IMap<String, String> map = hazelcastInstance.getMap("CustomerMap");

                        ObjectMapper mapper = new ObjectMapper();
                        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

                        String bodyString = exchange.getIn().getBody(String.class);

                        WeatherVO vo = mapper.readValue((mapper.readTree(bodyString)).traverse(), new TypeReference<WeatherVO>() {
                        });
                        vo.setBase("testhz");

                        String json = mapper.writeValueAsString(vo);

                        //add to cache
                        map.put(vo.getBase(), json);

                        //retrieve from cache
                        String str = map.get(vo.getBase());

                        assert str.contains("testhz") : "testhz expected to be set";

                        //set the body now in order to assert the changed value on our IT test
                        exchange.getIn().setBody(str);
                    }
                });
    }

    @SuppressWarnings("unused")
    public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

}





