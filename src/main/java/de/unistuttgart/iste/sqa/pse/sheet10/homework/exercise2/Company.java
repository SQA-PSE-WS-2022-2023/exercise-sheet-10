package de.unistuttgart.iste.sqa.pse.sheet10.homework.exercise2;

import java.util.Optional;
import java.util.Random;

/**
 * Represents the company from homework exercise 2.
 */
public final class Company {

	private final Warehouse penWarehouse;
	private final Warehouse triangleRulerWarehouse;
	private final Warehouse compassWarehouse;
	private final Buffer orderBuffer;
	// TODO: Add data structure for exercise 2i

	public Company() {
		orderBuffer = new Buffer();
		// TODO: Exercises 2e and 2i
		penWarehouse = new Warehouse(30);
		triangleRulerWarehouse = new Warehouse(20);
		compassWarehouse = new Warehouse(10);
	}

	public void handleIncomingItem(final Item item) {
		// TODO: Exercise 2e
		switch (item.getIdentification().getType()) {
		case PEN:
			penWarehouse.addItem(item);
			break;
		case TRIANGLE_RULER:
			triangleRulerWarehouse.addItem(item);
			break;
		case COMPASS:
			compassWarehouse.addItem(item);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + item.getIdentification().getType());
		}
	}

	public void processIncomingOrder(final ItemIdentification identification, final Customer customer) {
		// TODO: Exercises 2h and 2i
	}

	/*@
	@ ensures \result != null;
	@ ensures \result.getIdentification().getType() == ItemType.PRESENT;
	@*/

	/**
	 * Generates a marketing present item for exercise 2i.
	 *
	 * @return A marketing present item.
	 */
	private /*@ pure @*/ Item getPresent() {
		ItemType type = ItemType.PRESENT;
		int itemNumber = new Random().nextInt();
		ItemIdentification identification = new ItemIdentification(type, itemNumber);
		return new Item(identification);
	}

	/*@
	@ ensures \old(orderBuffer.isEmpty()) <==> \result.isEmpty();
	@*/

	/**
	 * Takes the next available item for packaging and returns it.
	 *
	 * @return Optional next available item for packaging, or an empty Optional if there is none.
	 */
	public Optional<Item> takeItemForPackaging() {
		if (orderBuffer.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(orderBuffer.releaseItem());
		}
	}
}
