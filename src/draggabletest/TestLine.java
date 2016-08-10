/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draggabletest;

import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

/**
 *
 * @author hyperman
 */
public class TestLine extends Line {
    
    TestLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY) {
      startXProperty().bind(startX);
      startYProperty().bind(startY);
      endXProperty().bind(endX);
      endYProperty().bind(endY);
      setStrokeWidth(4);
      setStroke(Color.GRAY.deriveColor(0, 1, 1, 0.5));
      //setStrokeLineCap(StrokeLineCap.BUTT);
      //getStrokeDashArray().setAll(10.0, 5.0);
      setMouseTransparent(true);
    }
    
    public void setXProp(DoubleProperty endX){
        endXProperty().bind(endX);
    }
    
    public void setYProp(DoubleProperty endY){
        endYProperty().bind(endY);
    }
    
}
