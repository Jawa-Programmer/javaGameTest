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
public class BigTree extends Tree {

    public BigTree(float x, float y, float z) {
        super(x, y, z);
    }

    @Override
    public void Draw() {
        super.Draw(); //To change body of generated methods, choose Tools | Templates.

        glBegin(GL_TRIANGLES);

        glColor3f(0, 1, 0);

        glVertex3f(x - width / 2, y + heigth - 0.1f, z);
        glVertex3f(x + width / 2, y + heigth - 0.1f, z);
        glVertex3f(x, y + heigth + heigth - 0.1f, z);

        glColor3f(0, 0.9f, 0);
        glVertex3f(x, y + heigth - 0.1f, z + width / 2);
        glVertex3f(x, y + heigth - 0.1f, z - width / 2);
        glVertex3f(x, y + heigth + heigth - 0.1f, z);

        glEnd();
    }

}
