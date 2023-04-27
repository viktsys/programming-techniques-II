package br.ufc.tpii.vmsys.inventory;

import br.ufc.tpii.vmsys.inventory.exceptions.ItemAlreadyAdded;
import br.ufc.tpii.vmsys.inventory.exceptions.ItemNotFound;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HashMapInventoryTest {

    @Test
    public void inventoryConstructorTest() {
        var inventory = new HashMapInventory();
        assertEquals(0, inventory.numberOfItems());
    }

    @Test
    public void addItemTest() throws ItemAlreadyAdded {
        var inventory = new HashMapInventory();
        var item = new Item("item", 1.0, 1);
        inventory.addItem(item);
        assertEquals(1, inventory.numberOfItems());

        var iex = assertThrows(ItemAlreadyAdded.class, () -> {
            inventory.addItem(item);
        });
        assertEquals("Item already added: item", iex.getMessage());

        // For coverage purposes only
        try {
            inventory.addItem(item);
        } catch (ItemAlreadyAdded e) {
            assertEquals("Item already added: item", e.getMessage());
        }
    }

    @Test
    public void removeItemTest() throws ItemAlreadyAdded, ItemNotFound {
        var inventory = new HashMapInventory();
        var itemA = new Item("itemA", 1.0, 1);
        var itemB = new Item("itemB", 1.0, 1);

        inventory.addItem(itemA);
        inventory.addItem(itemB);
        assertEquals(2, inventory.numberOfItems());

        inventory.removeItem("itemA");
        assertEquals(1, inventory.numberOfItems());

        var iex = assertThrows(ItemNotFound.class, () -> {
            inventory.removeItem("itemA");
        });
        assertEquals("Item not found: itemA", iex.getMessage());

        // For coverage purposes only
        try {
            inventory.removeItem("itemA");
        } catch (ItemNotFound e) {
            assertEquals("Item not found: itemA", e.getMessage());
        }
    }

    @Test
    public void getItemTest() throws ItemAlreadyAdded, ItemNotFound {
        var inventory = new HashMapInventory();
        var itemA = new Item("itemA", 1.0, 1);
        var itemB = new Item("itemB", 1.0, 1);

        inventory.addItem(itemA);
        inventory.addItem(itemB);
        assertEquals(2, inventory.numberOfItems());

        var item = inventory.getItem("itemA");
        assertEquals(itemA, item);

        var iex = assertThrows(ItemNotFound.class, () -> {
            inventory.getItem("item");
        });
        assertEquals("Item not found: item", iex.getMessage());

        // For coverage purposes only
        try {
            inventory.getItem("item");
        } catch (ItemNotFound e) {
            assertEquals("Item not found: item", e.getMessage());
        }

    }

    @Test
    public void numberOfItemsTest() throws ItemAlreadyAdded {
        var inventory = new HashMapInventory();
        var itemA = new Item("itemA", 1.0, 1);
        var itemB = new Item("itemB", 1.0, 1);
        inventory.addItem(itemA);
        inventory.addItem(itemB);
        assertEquals(2, inventory.numberOfItems());
    }

}
