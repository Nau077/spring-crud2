package springtestcrud.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springtestcrud.demo.model.Item;
import springtestcrud.demo.repository.ItemRepository;
import java.util.Optional;


import java.util.List;

@Service
public class ItemService implements IItemService {
    @Autowired
    private ItemRepository repository;

    @Override
    public List<Item> findAll() {
        var items = (List<Item>) repository.findAll();
        return items;
    }

    @Override
    public Optional<Item> findById(String id) {
        long longId = Long.parseLong(id);
        return repository.findById(longId);
    }

    @Override
    public Void deleteById(String id) {
        long longId = Long.parseLong(id);
        repository.deleteById(longId);
        return null;
    }

    public Item createItem(Long id, String name, String comment) {

        Item newItem = new Item(name, comment);
        return repository.save(newItem);
    }
}
