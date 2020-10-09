package springtestcrud.demo.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import springtestcrud.demo.service.IItemService;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import java.util.List;

@RestController
public class MyController {

    @Autowired
    private IItemService itemsService;

    @GetMapping(value = "/showItems",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemsListJson> findItems( Model model) {

        var items =  itemsService.findAll();

        model.addAttribute("items", items);
        return new ResponseEntity<>(new ItemsListJson(items), HttpStatus.OK);
    }

    @GetMapping(value = "/showItems/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemObjectJson> findItem(@PathVariable String id, Model model) {

        var item = itemsService.findById(id);

        model.addAttribute("item", item);
        return new ResponseEntity<>(new ItemObjectJson(item), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public @ResponseBody ResponseEntity<String> delete(@RequestBody DeleteDto DeleteDto) {

        try {
            var item = itemsService.deleteById(DeleteDto.getId());
            return new ResponseEntity<String>("DELETE Response", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @PostMapping("/createPost")
    public @ResponseBody ResponseEntity<String> createPost(@RequestBody PostDto PostDto) {

        try {
            var item = itemsService.createItem(PostDto.getId(), PostDto.getName(), PostDto.getComment());
            return new ResponseEntity<String>("POST Response", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    /**
     * DTO для описания id delete
     */

    // */
    private static class DeleteDto {
        @NotNull
        private String id;

        public String getId() {
            return id;
        }

    }
    /**
     * DTO для описания id POST
     */
    private static class PostDto {
        @NotNull
        private Long id;

        @NotNull
        private String name;

        @NotNull
        private String comment;

        public String getName() {
            return name;
        }

        public Long getId() {
            return id;
        }

        public String getComment() {
            return comment;
        }


    }
    //
    private static class ItemsListJson {

        @JsonProperty(value = "items")
        private final List items;

        private ItemsListJson(List items) {
            this.items = items;
        }
    }

    private static class ItemObjectJson {

        @JsonProperty(value = "item")
        private final Object item;

        private ItemObjectJson(Object item)
        {
            this.item = item;
        }
    }

}