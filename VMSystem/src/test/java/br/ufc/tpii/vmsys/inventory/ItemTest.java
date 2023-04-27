package br.ufc.tpii.vmsys.inventory;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ItemTest {

    @Test
    public void itemConstructorTest() {
        /* Item A - Normal item */
        var itemA = new Item("A", 10.0f, 20);
        assertEquals("A", itemA.getName());
        assertEquals(10.0f, itemA.getPrice());
        assertEquals(20, itemA.getCount());

        /* Item B - NullName item */
        var itbex = assertThrows(IllegalArgumentException.class, () -> {
            Item itemB = new Item(null, 10.0f, 20);
        });
        assertEquals("Item name cannot be null", itbex.getMessage());

        /* Item C - NegativePrice item */
        var itcex = assertThrows(IllegalArgumentException.class, () -> {
            Item itemC = new Item("C", -10.0f, 20);
        });
        assertEquals("Item price cannot be negative", itcex.getMessage());

        /* Item D - NegativeCount item */
        var itedex = assertThrows(IllegalArgumentException.class, () -> {
            Item itemD = new Item("D", 10.0f, -20);
        });
        assertEquals("Item count cannot be negative", itedex.getMessage());
    }

    @Test
    public void itemGetNameTest() {
        var item = new Item("A", 10.0f, 20);
        assertEquals("A", item.getName());
    }

    @Test
    public void itemGetPriceTest() {
        var item = new Item("A", 10.0f, 20);
        assertEquals(10.0f, item.getPrice());
    }

    @Test
    public void itemSetPriceTest() {
        var item = new Item("A", 10.0f, 20);
        item.setPrice(20.0f);
        assertEquals(20.0f, item.getPrice());
        var iexa = assertThrows(IllegalArgumentException.class, () -> {
            item.setPrice(-20.0f);
        });
        assertEquals("Item price cannot be negative", iexa.getMessage());
    }

    @Test
    public void itemGetCountTest() {
        var item = new Item("A", 10.0f, 20);
        assertEquals(20, item.getCount());
    }

    @Test
    public void itemDecCountTest() {
        var item = new Item("A", 10.0f, 20);
        item.decCount();
        assertEquals(19, item.getCount());
    }

    @Test
    public void itemIncCountTest() {
        var item = new Item("A", 10.0f, 20);
        item.incCount();
        assertEquals(21, item.getCount());
    }

}
