
//********************************************************************************
//   DO NOT EDIT ANYTHING BELOW THIS LINE
//********************************************************************************
import org.apache.commons.collections15.Factory;

import java.awt.Color;

/**
 * This abstract class provides all the structure needed for graph nodes and
 * edges (which are very similar). It provides identifiers, colors, text, and a
 * property map for these subcomponents.
 * 
 * @author Katherine (Raven) Russell
 */
public abstract class ThreeTenGraphComponent implements Comparable<ThreeTenGraphComponent> {
	/**
	 * The component id (for debugging purposes).
	 */
	private final int id;

	/**
	 * The text that should be displayed when visualizing the component.
	 */
	private String text;

	/**
	 * The color to display when visualizing the component.
	 */
	private Color color = Color.WHITE;

	/**
	 * Constructs a new component with the given id. This class is abstract, so this
	 * is only useful for descendent classes. Note that the identifier can't be
	 * changed after the component is created.
	 * 
	 * @param id the identifier for this component
	 */
	public ThreeTenGraphComponent(int id) {
		this.id = id;
	}

	/**
	 * Gets the id of this component.
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the current color of this component.
	 * 
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Changes the color of the component. Any GUI syststem needs to redraw after
	 * changing this (it isn't automatic).
	 * 
	 * @param color the new color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Gets the current text for diplay on this component.
	 * 
	 * @return the string representatio of this component
	 */
	public String getText() {
		return text;
	}

	/**
	 * Changes the text of the component. Any GUI syststem needs to redraw after
	 * changing this (it isn't automatic).
	 * 
	 * 
	 * @param text the new text to use
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * If the text is set, then this returns the text, otherwise this returns a hash
	 * tag followed by the component id.
	 * 
	 * @return the string representatio of this component
	 */
	@Override
	public String toString() {
		if (text != null)
			return text;
		else
			return "#" + id;
	}

	/**
	 * Components are equal if their ids are equal. This cannot be changed by child
	 * classes (child classes should use Comparators).
	 * 
	 * @param o the object to compare to
	 * @return whether or not the componets have the same id
	 */
	@Override
	public final boolean equals(Object o) {
		if (o instanceof ThreeTenGraphComponent) {
			return this.id == ((ThreeTenGraphComponent) o).id;
		}
		return false;
	}

	/**
	 * The hash code for graph components is just their id.
	 * 
	 * @return the hash code for this component
	 */
	@Override
	public final int hashCode() {
		return id;
	}

	/**
	 * Components are compared by their ids. This cannot be changed by child classes
	 * (child classes should use Comparators).
	 * 
	 * @param other the other compoent to be compared to
	 * @return a comparison of the component ids (in traditional compareTo style)
	 */
	@Override
	public final int compareTo(ThreeTenGraphComponent other) {
		return this.id - other.id;
	}
}
