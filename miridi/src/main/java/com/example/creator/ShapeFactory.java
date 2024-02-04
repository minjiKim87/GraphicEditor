package com.example.creator;

import com.example.creator.shapes.*;
import javafx.scene.shape.*;

public class ShapeFactory {
    private IShapeCreator circleCreator = new CircleCreator();
    private IShapeCreator ellipseCreator = new EllipseCreator();
    private IShapeCreator rectangleCreator = new RectangleCreator();
    private IShapeCreator squareCreator = new SquareCreator();
    private IShapeCreator triangleCreator = new TriangleCreator();
    private IShapeCreator straightCreator = new StraightCreator();
    private IShapeCreator curveCreator = new CurveCreator();


    public Shape createCircle(double startX, double startY, double endX, double endY) {
        return circleCreator.create(startX, startY, endX, endY);}

    public Shape createEllipse(double startX, double startY, double endX, double endY) {
        return ellipseCreator.create(startX, startY, endX, endY);}

    public Shape createRectangle(double startX, double startY, double endX, double endY) {
        return rectangleCreator.create(startX, startY, endX, endY);}

    public Shape createSquare(double startX, double startY, double endX, double endY) {
        return squareCreator.create(startX, startY, endX, endY);}

    public Shape createTriangle(double startX, double startY, double endX, double endY, boolean isRegular) {
        IShapeCreator triangleCreator = new TriangleCreator(isRegular);
        return triangleCreator.create(startX, startY, endX, endY);}

    public Shape createStraight(double startX, double startY, double endX, double endY){
        return straightCreator.create(startX, startY, endX, endY);}

    public Shape createCurve(double startX, double startY, double controlX, double controlY, double endX, double endY){
        return curveCreator.create(startX, startY, controlX, controlY, endX, endY);}


}
