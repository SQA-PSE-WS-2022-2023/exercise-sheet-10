package de.unistuttgart.iste.sqa.pse.sheet10.homework.exercise2;

/**
 * Represents an individual item in the warehouse system.
 */
public final class Item {

	// @ private instance invariant identification != null;
	private final ItemIdentification identification;

	/*@
	@ requires identification != null;
	@ ensures this.identification == identification;
	@*/
	/**
	 * Creates a new item with the given identification.
	 *
	 * @param identification Identification of the item.
	 * @throws IllegalArgumentException If the preconditions are not satisfied.
	 */
	public Item(final ItemIdentification identification) {
		if (identification == null) {
			throw new IllegalArgumentException("An item must have an identification that is not null.");
		}
		this.identification = identification;
	}
	
	// TODO methode löschen wenn nicht benötigt
	// TODO returns a newly created copy of itself
	public Item copy() {
		Item returner = new Item(this.identification);
		return returner;
	}

	/*@
	@ ensures \result == identification;
	@*/
	/**
	 * @return The identification object of this item.
	 */
	public /*@ pure @*/ ItemIdentification getIdentification() {
		return identification;
	}
}
