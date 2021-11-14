package org.example.test.mapper;

import org.example.test.bean.Car;
import org.example.test.dto.CarDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
//        componentModel = "spring",
        uses = {WeightMapper.class})
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);
    @Mappings({
            @Mapping(source = "numberOfSeats",target = "seatCount"),
            @Mapping(source = "carType.desc",target = "type"),
            @Mapping(source = "age",target = "age",ignore = true),
            @Mapping(source = "height",target = "height",qualifiedByName = "heightConvert"),
            @Mapping(source = "weight",target = "weight",qualifiedByName = {"weightMapper","weightConvert"}),
            @Mapping(source = "createTime",target = "createTime",dateFormat = "yyyy-MM-dd HH:mm:ss:SSS"),
            @Mapping(source = "price",target = "price",numberFormat = "0.00"),
            @Mapping(source = "rebate",target = "rebate",numberFormat = "0"),
            @Mapping(target = "intact",constant = "perfect")
    })
    CarDto carToCarDto(Car car);

    List<CarDto> carToCarDtos(List<Car> cars);

    @Named("heightConvert")
    default int heightConvert(int height){
        return height/100;
    }
    @Named("heightConvert2")
    default int heightConvert2(int height){
        return height*100;
    }
    /*@Named("weightConvert")
    default String weightConvert(int weight){
        return weight+"kg";
    }
    @Named("weightConvert2")
    default int weightConvert2(String weight){
        return Integer.parseInt(weight.replace("kg",""));
    }*/

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(source = "type",target = "carType",qualifiedByName = "getByDesc"),
            @Mapping(source = "height",target = "height",qualifiedByName = "heightConvert2"),
            @Mapping(source = "weight",target = "weight",qualifiedByName = {"weightMapper","weightConvert2"}),
    })
    Car carDtoToCar(CarDto carDto);

    @Named("getByCode")
    default Car.CarType getByCode(Integer code){
        Car.CarType[] sexEnums = Car.CarType.values();
        for (Car.CarType item:sexEnums){
            if(item.getCode().equals(code)){
                return item;
            }
        }
        return null;
    }
    @Named("getByDesc")
    default Car.CarType getByDesc(String desc){
        Car.CarType[] sexEnums = Car.CarType.values();
        for (Car.CarType item:sexEnums){
            if(item.getDesc().equals(desc)){
                return item;
            }
        }
        return null;
    }
}
