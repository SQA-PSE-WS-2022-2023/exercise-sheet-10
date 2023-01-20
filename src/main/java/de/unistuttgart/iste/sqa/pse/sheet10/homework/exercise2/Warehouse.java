package de.unistuttgart.iste.sqa.pse.sheet10.homework.exercise2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Represents a warehouse that can hold a fixed number of items.
 */
public final class Warehouse {

	// @ private instance invariant capacity > 0;
	private final int capacity;
	// @ private instance invariant numberOfItems >= 0;
	// @ private instance invariant numberOfItems <= capacity;
	private int numberOfItems;
	private ArrayList<Optional<Item>> storage;
	private Map<ItemIdentification, Integer> allogation;

	/*@
	  @ requires capacity > 0;
	  @ ensures this.capacity == capacity;
	  @*/
	/**
	 * Creates a new warehouse with the given capacity.
	 *
	 * @param capacity Capacity of the warehouse in items.
	 * @throws IllegalArgumentException If the preconditions are not satisfied.
	 */
	public Warehouse(final int capacity) {

		if (capacity <= 0) {
			throw new IllegalArgumentException("A warehouse must have a minimum capacity of 1.");
		}
		this.capacity = capacity;
		numberOfItems = 0;

		this.storage = new ArrayList<>(capacity);
		for (@SuppressWarnings("unused") Optional<Item> compartmentItem : storage) {
			compartmentItem = Optional.empty();
		}
		this.allogation = new HashMap<ItemIdentification, Integer>();
	}

	/*@
	  @ requires storage has a free space for storing the param
	  @ ensures lowest emtpy compartment of storage is param item
	  @*/
	public void addItem(final Item item) {

		numberOfItems++;
		for (int compartmentNumber = 0; compartmentNumber < capacity; compartmentNumber++) {
			if(storage.get(compartmentNumber).isEmpty()) {
				storage.set(compartmentNumber, Optional.of(item));
				allogation.put(item.getIdentification(), compartmentNumber);
				return;
			}
		}
	}

	/*@
	  @ requires true
	  @ ensures object in storage on index param compartmentNumber is Optional.empty()
	  @*/
	public void removeItem(final int compartmentNumber) {

		storage.set(compartmentNumber, Optional.empty());
		allogation.remove(storage.get(compartmentNumber).get().getIdentification());
		numberOfItems--;
	}

	public Optional<Item> getItem(final int compartmentNumber) {

		return Optional.of(storage.get(compartmentNumber).get().copy());
	}

	public Optional<Integer> getCompartmentNumberOf(final ItemIdentification identification) {

		return Optional.of(allogation.get(identification));
	}

	/*@
	@ ensures \result == capacity;
	@*/
	/**
	 * @return The capacity of this warehouse in items.
	 */
	public /*@ pure @*/ int getCapacity() {
		return capacity;
	}

	/*@
	@ ensures \result == numberOfItems;
	@*/
	/**
	 * @return The number of items in this warehouse.
	 */
	public /*@ pure @*/ int getNumberOfItems() {
		return numberOfItems;
	}
}
