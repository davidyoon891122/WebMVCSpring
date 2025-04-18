package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model) {
        Item newItem = new Item();
        newItem.setItemName(itemName);
        newItem.setPrice(price);
        newItem.setQuantity(quantity);

        itemRepository.save(newItem);

        model.addAttribute("item", newItem);

        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) {
        itemRepository.save(item);

        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        itemRepository.save(item);

        return "basic/item";
    }

    @PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);

        return "basic/item";
    }

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

}
