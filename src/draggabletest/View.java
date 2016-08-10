/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draggabletest;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author hyperman
 */
public class View extends Region{
    boolean hasLine = false;
    ObservableList<Node> myChildren = getChildren();
    DoubleProperty x = new SimpleDoubleProperty();
    DoubleProperty y = new SimpleDoubleProperty();
    private TestLine currentLine;
    
    private final int SIZE_W = 600;
    private final int SIZE_H = 600;
    
    public View(){
        super.setPrefSize(SIZE_W, SIZE_H);
        setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        this.onMouseMovedProperty().set(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          x.set(mouseEvent.getX());
          y.set(mouseEvent.getY());
        }
        });
        
        // test
        
        
        //makeDragRect(myChildren,50,50);
        //DragBox start    = new DragBox(Color.PALEGREEN, 100d, 100d,this);
        //DragBox end      = new DragBox(Color.TOMATO,    300d,   200d,this);
        //myChildren.add(start);
        //myChildren.add(end);
        //Line line = new TestLine(start.getDX(), start.getDY(), end.getDX(), end.getDY());
        
        //myChildren.add(line);
        
        initLeft(myChildren);
        
        
    }
    
    private Node makeDragRect(double sx,double sy,ObservableList<Node> children,double x,double y,Color color){
        DragBox box    = new DragBox(color, 50d, 50d,this);
        box.relocate(sx, sy);
        box.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          if (mouseEvent.getButton() == MouseButton.SECONDARY) {
              children.remove(box);
            
          }
          
          if(mouseEvent.getButton() == MouseButton.MIDDLE){
              if(hasLine){
                endLine(box.getDX(),box.getDY());  
                hasLine=false; 
              }
              else{
                spawnLine(box.getDX(),box.getDY());
                hasLine=true;
              }
              
          }
        }
        });
        
        children.add(box);
        
        
        
        return box;
    }
    
    

    

    private void initLeft(ObservableList<Node> children) {
        
        Rectangle r1 = new Rectangle(50d,50d);  
        r1.setFill(Color.GREEN);
        r1.setStrokeWidth(2);
        r1.setStrokeType(StrokeType.OUTSIDE);
        r1.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2){
                spawnDragBox(0,0,50,50,Color.GREEN); 
                }
            }
        });
        
        r1.relocate(0, 50);
        myChildren.add(r1);
        
        Rectangle r2 = new Rectangle(50d,50d);  
        r2.setFill(Color.BLUE);
        r2.setStrokeWidth(2);
        r2.setStrokeType(StrokeType.OUTSIDE);
        
        r2.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2){
                spawnDragBox(0,0,50,50,Color.BLUE); 
                }
            }
        });
        
        r2.relocate(0, 100);
        myChildren.add(r2);
        
        Rectangle r3 = new Rectangle(50d,50d);  
        r3.setFill(Color.RED);
        r3.setStrokeWidth(2);
        r3.setStrokeType(StrokeType.OUTSIDE);
        
        r3.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2){
                spawnDragBox(0,0,50,50,Color.RED); 
                }
            }
        });
        
        r3.relocate(0, 150);
        myChildren.add(r3);
        
        

        
    
    }
    
    
    
    private static final class DragContext {
        public double mouseAnchorX;
        public double mouseAnchorY;
        public double initialTranslateX;
        public double initialTranslateY;
    }
    
    public ObservableList<Node> getMyChildren(){
        return myChildren;
    }
    
    public void spawnDragBox(double sx,double sy,double x,double y,Color color){
        makeDragRect(sx,sy,myChildren, x, y,color);
    }
    void spawnLine(DoubleProperty x,DoubleProperty y) {
        TestLine spawnL =  new TestLine(x, y, this.x, this.y);
        currentLine = spawnL;
        spawnL.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          if (mouseEvent.getButton() == MouseButton.SECONDARY) {
              myChildren.remove(spawnL);
              System.out.println("TESSSTTT");
            
          }
        }});
        myChildren.add(currentLine);
    }
    void endLine(DoubleProperty x,DoubleProperty y) {
        currentLine.setXProp(x);
        currentLine.setYProp(y);        
        
    }
    
}
