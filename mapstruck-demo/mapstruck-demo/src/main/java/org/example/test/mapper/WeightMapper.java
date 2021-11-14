package org.example.test.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
@Named("weightMapper")
class WeightMapper {
    @Named("weightConvert")
    public String weightConvert(int weight){
        return weight+"kg";
    }
    @Named("weightConvert2")
    public int weightConvert2(String weight){
        return Integer.parseInt(weight.replace("kg",""));
    }
}
