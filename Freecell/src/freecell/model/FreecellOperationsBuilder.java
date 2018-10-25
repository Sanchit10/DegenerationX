package freecell.model;

public interface FreecellOperationsBuilder {
  FreecellOperationsBuilder cascades(int c);

  FreecellOperationsBuilder opens(int o);

  <Card> FreecellModel build();
}
