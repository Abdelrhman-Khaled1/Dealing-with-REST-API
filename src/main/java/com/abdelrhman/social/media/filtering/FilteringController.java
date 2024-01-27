package com.abdelrhman.social.media.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue filtering() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        return applyFilter(someBean, "field1", "field2");
    }

    @GetMapping("/filtering-list")//field 2 , field 3
    public MappingJacksonValue filteringList() {
        List<SomeBean> someBeans = Arrays.asList(
                new SomeBean("value1", "value2", "value3"),
                new SomeBean("value4", "value5", "value6")
        );

        return applyFilter(someBeans, "field2", "field3");
    }

    private MappingJacksonValue applyFilter(Object data, String... fields) {
        //Object to accept all kind of data
        //String... fields -> This is known as a varargs (variable-length argument) parameter. It allows you to pass an arbitrary number of String arguments to the method


        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(data);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }
}
