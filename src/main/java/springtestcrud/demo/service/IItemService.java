package springtestcrud.demo.service;

import springtestcrud.demo.model.Item;

import java.util.List;
import java.util.Optional;

public interface IItemService {
    List<Item> findAll();
    Optional<Item> findById(String id);
    Void deleteById(String id);
    Item createItem(Long id, String name, String comment);
}
