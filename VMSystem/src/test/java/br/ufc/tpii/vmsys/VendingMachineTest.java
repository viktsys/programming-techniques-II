package br.ufc.tpii.vmsys;

import br.ufc.tpii.vmsys.exceptions.InsufficientFunds;
import br.ufc.tpii.vmsys.exceptions.InvalidSelection;
import br.ufc.tpii.vmsys.exceptions.OutOfStock;
import br.ufc.tpii.vmsys.inventory.HashMapInventory;
import br.ufc.tpii.vmsys.inventory.Inventory;
import br.ufc.tpii.vmsys.inventory.Item;
import br.ufc.tpii.vmsys.inventory.exceptions.ItemAlreadyAdded;
import br.ufc.tpii.vmsys.inventory.exceptions.ItemNotFound;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class VendingMachineTest {

    private Inventory inventory;

    @BeforeEach
    public void setUp() throws ItemAlreadyAdded {
        this.inventory = new HashMapInventory();
        var itemA = new Item("itemA", 1.0, 2);
        var itemB = new Item("itemB", 1.0, 1);
        var itemC = new Item("itemC", 3.0, 1);
        
        this.inventory.addItem(itemA);
        this.inventory.addItem(itemB);
        this.inventory.addItem(itemC);
    }

    @Test
    public void vendingMachineConstructorTest() throws NoSuchFieldException, IllegalAccessException {
        var vm = new VendingMachine(this.inventory);
        var vmc = VendingMachine.class;

        // Get private inventory field from VendingMachine
        var inventoryField = vmc.getDeclaredField("inventory");
        inventoryField.setAccessible(true);
        var inventory = (Inventory) inventoryField.get(vm);
        inventoryField.setAccessible(false);

        // Get private coinsDeposited field from VendingMachine
        var coinsDepositedField = vmc.getDeclaredField("coinsDeposited");
        coinsDepositedField.setAccessible(true);
        var coinsDeposited = (double) coinsDepositedField.get(vm);
        coinsDepositedField.setAccessible(false);

        assertEquals(this.inventory, inventory);
        assertEquals(0.0, coinsDeposited);
    }

    @Test
    public void addCoinsPositiveInputTest() throws NoSuchFieldException, IllegalAccessException {
        var vm = new VendingMachine(this.inventory);
        var vmc = VendingMachine.class;

        // Get private coinsDeposited field from VendingMachine
        var coinsDepositedField = vmc.getDeclaredField("coinsDeposited");
        coinsDepositedField.setAccessible(true);

        var coinsDeposited = (double) coinsDepositedField.get(vm);
        assertEquals(0.0, coinsDeposited);

        vm.addCoins(1.0);
        coinsDeposited = (double) coinsDepositedField.get(vm);
        assertEquals(1.0, coinsDeposited);

        vm.addCoins(1.0);
        coinsDeposited = (double) coinsDepositedField.get(vm);
        assertEquals(2.0, coinsDeposited);
    }

    @Test
    public void addCoinsNegativeInputTest() throws NoSuchFieldException, IllegalAccessException {
        var vm = new VendingMachine(this.inventory);
        var vmc = VendingMachine.class;

        // Get private coinsDeposited field from VendingMachine
        var coinsDepositedField = vmc.getDeclaredField("coinsDeposited");
        coinsDepositedField.setAccessible(true);

        var coinsDeposited = (double) coinsDepositedField.get(vm);
        assertEquals(0.0, coinsDeposited);

        try {
            vm.addCoins(-1.0);
        } catch (IllegalArgumentException e) {
            assertEquals("Negative coins: -1.0", e.getMessage());
        }
    }

    @Test
    public void howManyCoinsLeftTest() {
        var vm = new VendingMachine(this.inventory);
        assertEquals(0.0, vm.howManyCoinsLeft());

        vm.addCoins(1.0);
        assertEquals(1.0, vm.howManyCoinsLeft());

        vm.addCoins(1.0);
        assertEquals(2.0, vm.howManyCoinsLeft());
    }

    @Test
    public void withdrawRemainingCoinsTest() {
        var vm = new VendingMachine(this.inventory);
        assertEquals(0.0, vm.withdrawRemainingCoins());

        vm.addCoins(1.0);
        assertEquals(1.0, vm.withdrawRemainingCoins());
        assertEquals(0.0, vm.howManyCoinsLeft());

        vm.addCoins(1.0);
        assertEquals(1.0, vm.withdrawRemainingCoins());
        assertEquals(0.0, vm.howManyCoinsLeft());
    }

    @Test
    public void buyItemTest() throws ItemNotFound, OutOfStock, InsufficientFunds, InvalidSelection {
        var vm = new VendingMachine(this.inventory);
        vm.addCoins(3.0);

        assertDoesNotThrow(() -> vm.vend("itemA"));
        assertEquals(2.0, vm.howManyCoinsLeft());

        assertDoesNotThrow(() -> vm.vend("itemB"));
        assertEquals(1.0, vm.howManyCoinsLeft());

        var iexB = assertThrows(OutOfStock.class, () -> vm.vend("itemB"));
        assertEquals(1.0, vm.howManyCoinsLeft());
        assertEquals(iexB.getMessage(), "Item out of stock: itemB");

        // For coverage purposes
        try {
            vm.vend("itemB");
        } catch (OutOfStock e) {
            assertEquals("Item out of stock: itemB", e.getMessage());
        }

        var iexC = assertThrows(InsufficientFunds.class, () -> vm.vend("itemC"));
        assertEquals(1.0, vm.howManyCoinsLeft());
        assertEquals(iexC.getMessage(), "Insufficient coins to by itemC: 1.0");

        // For coverage purposes
        try {
            vm.vend("itemC");
        } catch (InsufficientFunds e) {
            assertEquals("Insufficient coins to by itemC: 1.0", e.getMessage());
        }

        var iexD = assertThrows(InvalidSelection.class, () -> vm.vend("itemD"));
        assertEquals(1.0, vm.howManyCoinsLeft());
        assertEquals(iexD.getMessage(), "Invalid item selection: itemD");

        // For coverage purposes
        try {
            vm.vend("itemD");
        } catch (InvalidSelection e) {
            assertEquals("Invalid item selection: itemD", e.getMessage());
        }

    }
}
