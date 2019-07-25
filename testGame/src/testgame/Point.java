/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;

import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;
import static testgame.TestGame.*;

/**
 *
 * @author Спок
 */
public class Point extends GameObject {

    int color;

    public Point(float x, float y, float z) {
        super(x, y, z);
        /*heigth=0.05f;
        width=0.05f;
        depth=0.05f;*/
    }

    @Override
    public void Draw() {
        if (color == 1) {
            GL11.glLineWidth(20);
        } else {
            GL11.glLineWidth(5);
        }
        glBegin(GL11.GL_LINES);
        glColor3f(color, color, color);
        glVertex3f(cX + rX, cY + rY, cZ + rZ);
        glVertex3f(x, y, z);
        glEnd();
    }

    public Point setColor(boolean c) {
        if (c) {
            color = 1;
        } else {
            color = 0;
        }
        return this;
    }

}
