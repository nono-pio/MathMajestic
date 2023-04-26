package latex;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

public class LaTex {
    
    TeXFormula formula;
    TeXIcon icon;
    public String math;
    
    public LaTex(){
        // ...
    }

    public LaTex(String math){    
        this.math = math;
    }
    
    public TeXIcon getIconLaTex(int size){        
        formula = new TeXFormula(this.math);
		this.icon = this.formula.createTeXIcon(TeXConstants.ALIGN_CENTER, size);
		return this.icon;                         
    }
    
    public TeXIcon remakeIconLaTex(String math, int valor, float width){
        this.math = math;            
		this.formula = new TeXFormula(this.math);
		this.icon = this.formula.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY)
		        .setSize(valor)
		        .setWidth(TeXConstants.UNIT_PIXEL, width, TeXConstants.ALIGN_CENTER)
		        .setIsMaxWidth(true)
		        .setInterLineSpacing(TeXConstants.UNIT_PIXEL, 20f).build();
		return this.icon;
    
    }
    
    
    
}
