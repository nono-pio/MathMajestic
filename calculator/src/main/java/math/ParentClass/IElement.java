package math.ParentClass;

import math.tools.Position;

@FunctionalInterface
public interface IElement {
    void invoke(Element e, Position p);
}