package math.element.settings;

import math.element.Element;
import math.tools.Position;

@FunctionalInterface
public interface IElement {
    void invoke(Element e, Position p);
}