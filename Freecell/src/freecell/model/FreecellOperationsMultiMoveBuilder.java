package freecell.model;

public interface FreecellOperationsMultiMoveBuilder {
  FreecellOperationsMultiMoveBuilder cascades(int c);
  FreecellOperationsMultiMoveBuilder opens(int o);
  <Card>FreecellMultiMoveModel build1();

}
