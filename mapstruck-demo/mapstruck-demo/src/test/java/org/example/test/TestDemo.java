package org.example.test;

import org.example.test.bean.Car;
import org.example.test.dto.CarDto;
import org.example.test.mapper.CarMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestDemo {
    public static void main(String[] args) {
        Car car = new Car();
        car.setCarType(Car.CarType.AAA);
        car.setMake("test");
        car.setNumberOfSeats(4);
        car.setAge(3);
        car.setHeight(200);
        car.setWeight(200);
        car.setCreateTime(new Date());
        car.setPrice(200.010101);
        car.setRebate(200.010101);
        car.setIntact("low");
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(car);
        CarMapper instance = CarMapper.INSTANCE;
        List<CarDto> carDtos = instance.carToCarDtos(cars);
        System.out.println(cars);
        System.out.println(carDtos);

        System.out.println(instance.carDtoToCar(carDtos.get(0)));
    }
}
