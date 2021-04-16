package UI;

import java.awt.Component;

 class ComponentPlaceFactory {
	SuperView superView;
	int offsetX, offsetY;
	int componentX, componentY;
	int indentX, indentY;
	int cursurX, cursurY;
	public ComponentPlaceFactory(SuperView superView) {
		this.superView = superView;
		offsetX = 50; offsetY = 50;
		componentX = 100; componentY = 30;
		indentX = 30; indentY = 30;
		cursurX = offsetX; cursurY = offsetY;
	}
	public void placeNext(Component component) {
		placeComponent(component);
		cursurNext();
	}
	public void placeNext(Component component, int adjustComponentX) {
		componentX += adjustComponentX;
		placeNext(component);
		componentX -= adjustComponentX;
	}
	public void placeBelow(Component component) {
		placeComponent(component);
		cursurBelow();
	}
	public void placeBelow(Component component, int adjustComponentX) {
		componentX += adjustComponentX;
		placeBelow(component);
		componentX -= adjustComponentX;
	}
	public void placeComponent(Component component) {
		component.setBounds(cursurX, cursurY, componentX, componentY);
		superView.add(component);
	}
	public void cursurNext() {
		cursurX += componentX + indentX;
	}
	public void cursurBelow() {
		cursurX = offsetX;
		cursurY += componentY + indentY;
	}
}
