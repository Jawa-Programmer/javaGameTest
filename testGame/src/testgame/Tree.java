/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;

/**
 *
 * @author Спок
 */
public class Tree {

    float x = 0, y = 0f, z = 0, width = 0.5f, heigth = 0.5f;

    public Tree(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void Draw() {
        glBegin(GL_TRIANGLES);

        glColor3f(0, 1, 0);

        glVertex3f(x - width / 2, y, z);
        glVertex3f(x + width / 2, y, z);
        glVertex3f(x, y + heigth, z);

        glColor3f(0, 0.9f, 0);
        glVertex3f(x, y, z + width / 2);
        glVertex3f(x, y, z - width / 2);
        glVertex3f(x, y + heigth, z);

        glEnd();
    }

}
