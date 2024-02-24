package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Car;

import java.util.Iterator;
import java.util.List;

public interface CarRepositoryIntf {
    public Car create(Car car);
    public Iterator<Car> findAll();
    Car findById(String id);
    public Car update(String id, Car updatedCar);
    public void delete(String id);
}