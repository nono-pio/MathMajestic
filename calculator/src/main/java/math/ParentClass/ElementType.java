package math.ParentClass;

import math.numbers.Variable;
import math.numbers.Number;

import java.util.Arrays;
import java.util.List;

import math.elements.*;

/**
 * 
 * ElementType is an Enum that represent the nature of an Element, like Number, Variable, ...
 * 
 * @author Nolan Piccand
 * 
 */
public enum ElementType {
    Number,
    Variable,
    
    Form,
    
    Function,

    Addition,
    Product,
    Division,
    Power, 
    Log
}
