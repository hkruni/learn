package learn.modelMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.time.DateUtils;
import org.modelmapper.ModelMapper;

import java.text.ParseException;

public class ModelMapperTest {

    public static void main(String[] args) throws ParseException, JsonProcessingException {

        ModelMapper mapper = new ModelMapper();

        House house = new House();
        house.setAdminId(10000L);
        house.setArea(10);
        house.setCityEnName("北京");
        house.setCreateTime( DateUtils.parseDate("2018-06-23","yyyy-MM-dd"));
        house.setDistrict("海淀区");
        house.setId(1L);
        house.setLastUpdateTime(DateUtils.parseDate("2015-06-23","yyyy-MM-dd"));
        house.setPrice(200000);
        house.setRegionEnName("HAIDIAN");
        house.setRoom(3);
        house.setStreet("中关村大街");
        house.setTitle("中国移动海兴大厦");

        HouseDTO houseDTO = mapper.map(house,HouseDTO.class);
        System.out.println(houseDTO);

        House house1 = new House();
        mapper.map(house,house1);
        System.out.println(house1);


        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(house));




    }
}
