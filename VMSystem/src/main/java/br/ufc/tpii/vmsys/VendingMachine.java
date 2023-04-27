package br.ufc.tpii.vmsys;

import br.ufc.tpii.vmsys.exceptions.InsufficientFunds;
import br.ufc.tpii.vmsys.exceptions.InvalidSelection;
import br.ufc.tpii.vmsys.exceptions.OutOfStock;
import br.ufc.tpii.vmsys.inventory.Inventory;
import br.ufc.tpii.vmsys.inventory.Item;
import br.ufc.tpii.vmsys.inventory.exceptions.ItemNotFound;

public class VendingMachine {

	private double coinsDeposited;

	private Inventory inventory;

	public VendingMachine(Inventory inventory) {
		if (inventory == null) {
			throw new IllegalArgumentException("Null inventory");
		}
		this.coinsDeposited = 0.0;
		this.inventory = inventory;
	}

	public void addCoins(double coins) throws IllegalArgumentException {
		/* ERRO: Não trata valores negativos */
		if (coins < 0) {
			throw new IllegalArgumentException("Negative coins: " + coins);
		} else {
			this.coinsDeposited += coins;
		}
	}
	
	public double withdrawRemainingCoins() {
		double remainingCoins = this.coinsDeposited;
		this.coinsDeposited = 0.0;
		return remainingCoins;
	}

	public double howManyCoinsLeft() {
		return this.coinsDeposited;
	}

	public void vend(String itemName) throws InvalidSelection, OutOfStock, InsufficientFunds {
		Item item;

		try {
			item = inventory.getItem(itemName);
		} catch (ItemNotFound inf) {
			throw new InvalidSelection("Invalid item selection: " + itemName);
		}

		/* ERRO: Não trata estoque negativo */
		/* item.getCount() >= 0 */
		if (item.getCount() == 0) {
			throw new OutOfStock("Item out of stock: " + itemName);
		}

		/* ERRO: Ordem incorreta do operador */
		/* item.getPrice() < this.coinsDeposited */
		if ( this.coinsDeposited < item.getPrice()) {
			throw new InsufficientFunds("Insufficient coins to by " + itemName + ": " + this.coinsDeposited);
		} else {
			/* Erro: Não se incrementa o valor de moedas depositadas */
			/* this.coinsDeposited += item.getPrice(); */
			this.coinsDeposited -= item.getPrice();
			item.decCount();
		}
	}
}