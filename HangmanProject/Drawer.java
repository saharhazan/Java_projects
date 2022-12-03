package sample;
/* Author: Sahar Hazan
 * Date: 16.12.2022
 * Maman 13 - Question 1
 */


import java.util.ArrayList;
import javafx.scene.shape.Line;

public class Drawer {
    ArrayList<Line> hangmanParts = new ArrayList<Line>();

    public void draw(){
        Line line1 = new Line(360,310,390,350);
        Line line2 = new Line(360,310,330,350);
        Line line3 = new Line(360,310,360,100);
        Line line4 = new Line(360,100,200,100);
        Line line5 = new Line(200,100,200,150);
        Line head = new Line(175,150,50,50);
        Line body = new Line(200,200,200,260);
        Line hands1 = new Line(170,230,200,210);
        Line hands2 = new Line(200,210,230,230);
        Line legs1 = new Line(170,280,200,260);
        Line legs2 = new Line(200,260,230,280);

        hangmanParts.add(line1);
        hangmanParts.add(line2);
        hangmanParts.add(line3);
        hangmanParts.add(line4);
        hangmanParts.add(line5);
        hangmanParts.add(head);
        hangmanParts.add(body);
        hangmanParts.add(hands1);
        hangmanParts.add(hands2);
        hangmanParts.add(legs1);
        hangmanParts.add(legs2);
    }
}
