package math.ParentsElements;

import math.ParentClass.Element;
import math.ParentClass.ParentElement;
import math.numbers.Number;

public class AritmeticSequence extends ParentElement{

    public Element sequence;
    
    public AritmeticSequence(Element sequence)
    {
        super( new Element[] {sequence.clone()} );
        this.sequence = sequence.clone();
    } 

    public Number toValue() { return sequence.toValue(); }

    @Override
    public String toString() { return sequence.toString(); }

    @Override
    public Element[] getSequences() { return new Element[] {sequence}; }

}
