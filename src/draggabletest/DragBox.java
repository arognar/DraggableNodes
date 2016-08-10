/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draggabletest;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author hyperman
 */
public class DragBox extends Rectangle {
    boolean lineDocked = false;
    private View view;
    //DoubleProperty x = new SimpleDoubleProperty();
    //DoubleProperty y = new SimpleDoubleProperty();
    
    
    DragBox(Color color, Double x, Double y,View view) {
      
      super(x, y);
      this.view = view;
      setFill(color.deriveColor(1, 1, 1, 0.5));
      setStroke(color);
      setStrokeWidth(1);
      setStrokeType(StrokeType.INSIDE);

      //this.x.bind(xProperty());
      //this.y.bind(yProperty());
      enableDrag();
    }

    // make a node movable by dragging it around with the mouse.
    private void enableDrag() {
      final Delta dragDelta = new Delta();
      setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          // record a delta distance for the drag and drop operation.
          dragDelta.x = getX() - mouseEvent.getX();
          dragDelta.y = getY() - mouseEvent.getY();
          getScene().setCursor(Cursor.MOVE);
        }
      });
      setOnMouseReleased(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          getScene().setCursor(Cursor.HAND);
        }
      });
      setOnMouseDragged(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          double newX = mouseEvent.getX() + dragDelta.x;
          if (newX > 0 && newX < getScene().getWidth()) {
            setX(newX);
          }  
          double newY = mouseEvent.getY() + dragDelta.y;
          if (newY > 0 && newY < getScene().getHeight()) {
            setY(newY);
          }  
        }
      });
      setOnMouseEntered(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          if (!mouseEvent.isPrimaryButtonDown()) {
            getScene().setCursor(Cursor.HAND);
          }
        }
      });
      setOnMouseExited(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          if (!mouseEvent.isPrimaryButtonDown()) {
            getScene().setCursor(Cursor.DEFAULT);
          }
        }
      });
      setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          if (mouseEvent.getButton() == MouseButton.PRIMARY) {
              if(!lineDocked){
                  view.getMyChildren().remove(this);


              }
            
          }
        }
      });
      
      
    }
    
    public DoubleProperty getDX(){
        return xProperty();
    }
    
    public DoubleProperty getDY(){
        return yProperty();
    }

    // records relative x and y co-ordinates.
    private class Delta { double x, y; }
  
    
}
