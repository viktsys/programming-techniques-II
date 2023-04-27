package br.ufc.tpii.vmsys.inventory;

public class Item {
	private String name;
	private double price = 0.0;
	private int count = 0;

	public Item(String name, double price, int count) {
		if (price < 0)
			throw new IllegalArgumentException("Item price cannot be negative");
		if (count < 0)
			throw new IllegalArgumentException("Item count cannot be negative");
		if (name == null)
			throw new IllegalArgumentException("Item name cannot be null");

		this.name = name;
		this.price = price;
		this.count = count;
	}

	public String getName() {
		return this.name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if (price < 0)
			throw new IllegalArgumentException("Item price cannot be negative");

		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void decCount() {
		/* Erro: this.count++ */
		this.count--;
	}

	public void incCount() {
		/* Erro: this.count-- */
		this.count++;
	}
}