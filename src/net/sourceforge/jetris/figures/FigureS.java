package net.sourceforge.jetris.figures;

import java.awt.Color;

import net.sourceforge.jetris.Figure;

public class FigureS extends Figure {
    
    private int[][] rotations; 
    
    private int curRotation;

    public FigureS() {
        super(new int[] {0,1,1,2}, 
              new int[] {1,0,1,0});
        
        rotations = new int[4][4];
        rotations[0] = new int[] {0,1,1,2};
        rotations[1] = new int[] {1,0,1,0};
        rotations[2] = new int[] {0,0,1,1};
        rotations[3] = new int[] {0,1,1,2};
        curRotation = 0;
    }

    public void rotationRight() {
        if(curRotation == 0) {
            arrX = rotations[2];
            arrY = rotations[3];
            curRotation = 1;
            
        } else {
            arrX = rotations[0];
            arrY = rotations[1];
            curRotation = 0;
        }
    }

    public void rotationLeft() {
        rotationRight();
    }

    public int getGridVal() {
        return S;
    }
    
	public Color getGolor() {
        return COL_S;
    }
}